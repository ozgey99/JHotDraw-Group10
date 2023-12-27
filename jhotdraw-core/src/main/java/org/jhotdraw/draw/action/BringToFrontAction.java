/*
 * @(#)BringToFrontAction.java
 *
 * Copyright (c) 2003-2008 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.draw.action;

import org.jhotdraw.draw.figure.Figure;
import java.util.*;
import javax.swing.undo.*;
import org.jhotdraw.draw.*;
import org.jhotdraw.util.ResourceBundleUtil;

/**
 * ToFrontAction.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class BringToFrontAction extends AbstractArrangeAction {

    private static final long serialVersionUID = 1L;
    public static final String ID = "edit.bringToFront";

    /**
     * Creates a new instance.
     */
    public BringToFrontAction(DrawingEditor editor) {
        super(editor);
        ResourceBundleUtil labels
                = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
        labels.configureAction(this, ID);
        updateEnabledState();
    }

    @Override
    // @FeatureEntryPoint("BringToFrontAction")
    public void actionPerformed(java.awt.event.ActionEvent e) {
        final DrawingView view = getView();
        final LinkedList<Figure> figures = new LinkedList<>(view.getSelectedFigures());
        arrange(view, figures);
        fireUndoableEditHappened(new AbstractUndoableEdit() {
            private static final long serialVersionUID = 1L;

            @Override
            public String getPresentationName() {
                ResourceBundleUtil labels
                        = ResourceBundleUtil.getBundle("org.jhotdraw.draw.Labels");
                return labels.getTextProperty(ID);
            }

            @Override
            public void redo() throws CannotRedoException {
                super.redo();
                arrange(view, figures);
            }

            @Override
            public void undo() throws CannotUndoException {
                super.undo();
                SendToBackAction sendToBackAction = new SendToBackAction(getEditor());
                sendToBackAction.arrange(view, figures);
            }
        });
    }

    @Override
    public void arrange(DrawingView view, Collection<Figure> figures) {
        Drawing drawing = view.getDrawing();
        for (Figure figure : drawing.sort(figures)) {
            drawing.arrange(figure, ArrangeActionType.BRING_TO_FRONT);
        }
    }
}
