/*
 * @(#)AbstractAttributedFigure.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.figure;

import java.awt.*;
import java.awt.geom.*;
import java.io.*;
import java.util.*;
import org.jhotdraw.draw.AttributeKey;
import org.jhotdraw.draw.AttributeKeys;
import static org.jhotdraw.draw.AttributeKeys.*;
import org.jhotdraw.geom.Dimension2DDouble;
import org.jhotdraw.geom.Geom;
import org.jhotdraw.xml.DOMInput;
import org.jhotdraw.xml.DOMOutput;
import org.jhotdraw.xml.DOMStorable;

/**
 * This abstract class can be extended to implement a {@link Figure} which has
 * its own attribute set.
 *
 * @author Werner Randelshofer
 * @version $Id: AbstractAttributedFigure.java 778 2012-04-13 15:37:19Z rawcoder
 * $
 */
public abstract class AbstractAttributedFigure extends AbstractFigure implements DOMStorable {

    private static final long serialVersionUID = 1L;
    /**
     * Holds the attributes of the figure.
     */
    private HashMap<AttributeKey<?>, Object> attributes = new HashMap<>();
    /**
     * Forbidden attributes can't be put by the put() operation. They can only
     * be changed by put().
     */
    private HashSet<AttributeKey<?>> forbiddenAttributes;

    /**
     * Creates a new instance.
     */
    public AbstractAttributedFigure() {
    }

    public void setAttributeEnabled(AttributeKey<?> key, boolean b) {
        if (forbiddenAttributes == null) {
            forbiddenAttributes = new HashSet<>();
        }
        if (b) {
            forbiddenAttributes.remove(key);
        } else {
            forbiddenAttributes.add(key);
        }
    }

    public boolean isAttributeEnabled(AttributeKey<?> key) {
        return forbiddenAttributes == null || !forbiddenAttributes.contains(key);
    }

    @SuppressWarnings("unchecked")
    public void setAttributes(Map<AttributeKey<?>, Object> map) {
        for (Map.Entry<AttributeKey<?>, Object> entry : map.entrySet()) {
            set((AttributeKey<Object>) entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Map<AttributeKey<?>, Object> getAttributes() {
        return (Map<AttributeKey<?>, Object>) new HashMap<>(attributes);
    }

    @Override
    public Object getAttributesRestoreData() {
        return getAttributes();
    }

    @Override
    public void restoreAttributesTo(Object restoreData) {
        attributes.clear();
        @SuppressWarnings("unchecked")
        HashMap<AttributeKey<?>, Object> restoreDataHashMap = (HashMap<AttributeKey<?>, Object>) restoreData;
        setAttributes(restoreDataHashMap);
    }

    /**
     * Sets an attribute of the figure. AttributeKey name and semantics are
     * defined by the class implementing the figure interface.
     */
    @Override
    public <T> void set(AttributeKey<T> key, T newValue) {
        if (forbiddenAttributes == null
                || !forbiddenAttributes.contains(key)) {
            @SuppressWarnings("unchecked")
            T oldValue = key.put(attributes, newValue);
            fireAttributeChanged(key, oldValue, newValue);
        }
    }

    /**
     * Gets an attribute from the figure.
     */
    @Override
    public <T> T get(AttributeKey<T> key) {
        return key.get(attributes);
    }

    @Override
    public void draw(Graphics2D g) {
        if (get(FILL_COLOR) != null) {
            g.setColor(get(FILL_COLOR));
            drawFill(g);
        }
        if (get(STROKE_COLOR) != null && get(STROKE_WIDTH) >= 0d) {
            g.setStroke(AttributeKeys.getStroke(this, AttributeKeys.getScaleFactorFromGraphics(g)));
            g.setColor(get(STROKE_COLOR));
            drawStroke(g);
        }
        if (get(TEXT_COLOR) != null) {
            if (get(TEXT_SHADOW_COLOR) != null
                    && get(TEXT_SHADOW_OFFSET) != null) {
                Dimension2DDouble d = get(TEXT_SHADOW_OFFSET);
                g.translate(d.width, d.height);
                g.setColor(get(TEXT_SHADOW_COLOR));
                drawText(g);
                g.translate(-d.width, -d.height);
            }
            g.setColor(get(TEXT_COLOR));
            drawText(g);
        }
    }

    public double getStrokeMiterLimitFactor() {
        Number value = (Number) get(AttributeKeys.STROKE_MITER_LIMIT);
        return (value != null) ? value.doubleValue() : 10f;
    }

    @Override
    public Rectangle2D.Double getDrawingArea() {
        return getDrawingArea(1.0);
    }

    @Override
    public Rectangle2D.Double getDrawingArea(double factor) {
        double strokeTotalWidth = AttributeKeys.getStrokeTotalWidth(this, factor);
        double width = strokeTotalWidth / 2d;
        if (get(STROKE_JOIN) == BasicStroke.JOIN_MITER) {
            width *= get(STROKE_MITER_LIMIT);
        } else if (get(STROKE_CAP) != BasicStroke.CAP_BUTT) {
            width += strokeTotalWidth * 2;
        }
        width++;
        Rectangle2D.Double r = getBounds();
        Geom.grow(r, width, width);
        return r;
    }

    /**
     * This method is called by method draw() to draw the fill area of the
     * figure. AbstractAttributedFigure configures the Graphics2D object with
     * the FILL_COLOR attribute before calling this method. If the FILL_COLOR
     * attribute is null, this method is not called.
     */
    protected abstract void drawFill(java.awt.Graphics2D g);

    /**
     * This method is called by method draw() to draw the lines of the figure .
     * AttributedFigure configures the Graphics2D object with the STROKE_COLOR
     * attribute before calling this method. If the STROKE_COLOR attribute is
     * null, this method is not called.
     */
    protected abstract void drawStroke(java.awt.Graphics2D g);

    /**
     * This method is called by method draw() to draw the text of the figure .
     * AbstractAttributedFigure configures the Graphics2D object with the
     * TEXT_COLOR attribute before calling this method. If the TEXT_COLOR
     * attribute is null, this method is not called.
     */
    protected void drawText(java.awt.Graphics2D g) {
    }

    @Override
    public AbstractAttributedFigure clone() {
        AbstractAttributedFigure that = (AbstractAttributedFigure) super.clone();
        that.attributes = new HashMap<>(this.attributes);
        if (this.forbiddenAttributes != null) {
            that.forbiddenAttributes = new HashSet<>(this.forbiddenAttributes);
        }
        return that;
    }

    protected void writeAttributes(DOMOutput out) throws IOException {
        Figure prototype = (Figure) out.getPrototype();
        boolean isElementOpen = false;
        for (Map.Entry<AttributeKey<?>, Object> entry : attributes.entrySet()) {
            AttributeKey<?> key = entry.getKey();
            if (forbiddenAttributes == null
                    || !forbiddenAttributes.contains(key)) {
                @SuppressWarnings("unchecked")
                Object prototypeValue = prototype.get(key);
                @SuppressWarnings("unchecked")
                Object attributeValue = get(key);
                if (prototypeValue != attributeValue) {
                    if (!isElementOpen) {
                        out.openElement("a");
                        isElementOpen = true;
                    }
                    out.openElement(key.getKey());
                    out.writeObject(entry.getValue());
                    out.closeElement();
                }
            }
        }
        if (isElementOpen) {
            out.closeElement();
        }
    }

    @SuppressWarnings("unchecked")
    protected void readAttributes(DOMInput in) throws IOException {
        if (in.getElementCount("a") > 0) {
            in.openElement("a");
            for (int i = 0, n = in.getElementCount(); i < n; i++) {
                in.openElement(i);
                String name = in.getTagName();
                Object value = in.readObject();
                AttributeKey<?> key = getAttributeKey(name);
                if (assignementAllowed(key, value)) {
                    set((AttributeKey<Object>) key, value);
                }
                in.closeElement();
            }
            in.closeElement();
        }
    }

    protected boolean assignementAllowed(AttributeKey<?> key, Object value){
        return (key != null && key.isAssignable(value)) && (forbiddenAttributes == null || !forbiddenAttributes.contains(key));

    }

    protected AttributeKey<?> getAttributeKey(String name) {
        return AttributeKeys.SUPPORTED_ATTRIBUTES_MAP.get(name);
    }

    /**
     * Applies all attributes of this figure to that figure.
     */
    @SuppressWarnings("unchecked")
    protected void applyAttributesTo(Figure that) {
        for (Map.Entry<AttributeKey<?>, Object> entry : attributes.entrySet()) {
            that.set((AttributeKey<Object>) entry.getKey(), entry.getValue());
        }
    }

    @Override
    public void write(DOMOutput out) throws IOException {
        Rectangle2D.Double r = getBounds();
        out.addAttribute("x", r.x);
        out.addAttribute("y", r.y);
        out.addAttribute("w", r.width);
        out.addAttribute("h", r.height);
        writeAttributes(out);
    }

    @Override
    public void read(DOMInput in) throws IOException {
        double x = in.getAttribute("x", 0d);
        double y = in.getAttribute("y", 0d);
        double w = in.getAttribute("w", 0d);
        double h = in.getAttribute("h", 0d);
        setBounds(new Point2D.Double(x, y), new Point2D.Double(x + w, y + h));
        readAttributes(in);
    }

    public <T> void removeAttribute(AttributeKey<T> key) {
        if (hasAttribute(key)) {
            T oldValue = get(key);
            attributes.remove(key);
            fireAttributeChanged(key, oldValue, key.getDefaultValue());
        }
    }

    public boolean hasAttribute(AttributeKey<?> key) {
        return attributes.containsKey(key);
    }
}
