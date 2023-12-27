package org.jhotdraw.draw;

import org.jhotdraw.draw.action.ArrangeActionType;
import org.jhotdraw.draw.connector.Connector;
import org.jhotdraw.draw.event.FigureListener;
import org.jhotdraw.draw.figure.ConnectionFigure;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.handle.Handle;
import org.jhotdraw.draw.tool.Tool;
import org.jhotdraw.geom.Dimension2DDouble;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

class QuadTreeDrawingTest {
    @Test
    void arrangeWorksCorrectlyForSendToBack() {
        // arrange
        MockQuadTreeDrawing quadTreeDrawing = new MockQuadTreeDrawing();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        quadTreeDrawing.setChildren(list);

        // act
        quadTreeDrawing.arrange(testFigure2, ArrangeActionType.SEND_TO_BACK);
        List<Figure> capturedList = quadTreeDrawing.getChildren();

        // assert
        Assertions.assertEquals(2, capturedList.size());
        Assertions.assertEquals(0, capturedList.indexOf(testFigure2));
        Assertions.assertEquals(1, capturedList.indexOf(testFigure1));
    }

    @Test
    void arrangeWorksCorrectlyForBringToFront() {
        // arrange
        MockQuadTreeDrawing quadTreeDrawing = new MockQuadTreeDrawing();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        quadTreeDrawing.setChildren(list);

        // act
        quadTreeDrawing.arrange(testFigure1, ArrangeActionType.BRING_TO_FRONT);
        List<Figure> capturedList = quadTreeDrawing.getChildren();

        // assert
        Assertions.assertEquals(2, capturedList.size());
        Assertions.assertEquals(0, capturedList.indexOf(testFigure2));
        Assertions.assertEquals(1, capturedList.indexOf(testFigure1));
    }

    static class MockQuadTreeDrawing extends QuadTreeDrawing {
        void setChildren(ArrayList<Figure> children) {
            super.children = children;
        }
    }

    static class MockFigureImpl implements Figure {
        public String name;

        public MockFigureImpl(String name) {
            this.name = name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        @Override
        public void draw(Graphics2D g) {

        }

        @Override
        public int getLayer() {
            return 0;
        }

        @Override
        public boolean isVisible() {
            return false;
        }

        @Override
        public void setBounds(Point2D.Double start, Point2D.Double end) {

        }

        @Override
        public Point2D.Double getStartPoint() {
            return null;
        }

        @Override
        public Point2D.Double getEndPoint() {
            return null;
        }

        @Override
        public Rectangle2D.Double getBounds() {
            return null;
        }

        @Override
        public Rectangle2D.Double getDrawingArea() {
            return null;
        }

        @Override
        public Rectangle2D.Double getDrawingArea(double factor) {
            return null;
        }

        @Override
        public Dimension2DDouble getPreferredSize() {
            return null;
        }

        @Override
        public boolean contains(Point2D.Double p) {
            return false;
        }

        @Override
        public Object getTransformRestoreData() {
            return null;
        }

        @Override
        public void restoreTransformTo(Object restoreData) {

        }

        @Override
        public void transform(AffineTransform tx) {

        }

        @Override
        public <T> void set(AttributeKey<T> key, T value) {

        }

        @Override
        public <T> T get(AttributeKey<T> key) {
            return null;
        }

        @Override
        public Map<AttributeKey<?>, Object> getAttributes() {
            return null;
        }

        @Override
        public Object getAttributesRestoreData() {
            return null;
        }

        @Override
        public void restoreAttributesTo(Object restoreData) {

        }

        @Override
        public boolean isSelectable() {
            return false;
        }

        @Override
        public boolean isRemovable() {
            return false;
        }

        @Override
        public boolean isTransformable() {
            return false;
        }

        @Override
        public Collection<Handle> createHandles(int detailLevel) {
            return null;
        }

        @Override
        public Cursor getCursor(Point2D.Double p) {
            return null;
        }

        @Override
        public Collection<Action> getActions(Point2D.Double p) {
            return null;
        }

        @Override
        public Tool getTool(Point2D.Double p) {
            return null;
        }

        @Override
        public String getToolTipText(Point2D.Double p) {
            return null;
        }

        @Override
        public boolean isConnectable() {
            return false;
        }

        @Override
        public Connector findConnector(Point2D.Double p, ConnectionFigure prototype) {
            return null;
        }

        @Override
        public Connector findCompatibleConnector(Connector c, boolean isStartConnector) {
            return null;
        }

        @Override
        public Collection<Connector> getConnectors(ConnectionFigure prototype) {
            return null;
        }

        @Override
        public boolean includes(Figure figure) {
            return false;
        }

        @Override
        public Figure findFigureInside(Point2D.Double p) {
            return null;
        }

        @Override
        public Collection<Figure> getDecomposition() {
            return null;
        }

        @Override
        public Figure clone() {
            return null;
        }

        @Override
        public void remap(Map<Figure, Figure> oldToNew, boolean disconnectIfNotInMap) {

        }

        @Override
        public void addNotify(Drawing d) {

        }

        @Override
        public void removeNotify(Drawing d) {

        }

        @Override
        public void willChange() {

        }

        @Override
        public void changed() {

        }

        @Override
        public void requestRemove() {

        }

        @Override
        public boolean handleDrop(Point2D.Double p, Collection<Figure> droppedFigures, DrawingView view) {
            return false;
        }

        @Override
        public boolean handleMouseClick(Point2D.Double p, MouseEvent evt, DrawingView view) {
            return false;
        }

        @Override
        public void addFigureListener(FigureListener l) {

        }

        @Override
        public void removeFigureListener(FigureListener l) {

        }

        @Override
        public void addPropertyChangeListener(PropertyChangeListener listener) {

        }

        @Override
        public void removePropertyChangeListener(PropertyChangeListener listener) {

        }
    }
}