package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.draw.handle.*;
import org.jhotdraw.draw.tool.*;
import org.jhotdraw.draw.*;
import org.junit.*;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class SelectionTest {

    /*
    * When a mouse event occurs on a place where no figure is located, the SelectAreaTracker is used.
    * When its in a Figure Handle, then the HandleTracker is used.
    * Finally when its in the middle of a figure, the DragTracker is used.
    * */
    static DrawingView mockView;
    static SelectionTool selectionTool = new SelectionTool();
    static RectangleFigure rectangleFigure;
    static RectangleFigure behindRectangleFigure;

    public static MouseEvent mockMouseEvent(int x, int y) {
        return new MouseEvent((Component) mockView, MouseEvent.MOUSE_PRESSED,
                System.currentTimeMillis(), 0,
                x, y, 1,
                false);
    }

    @BeforeClass
    public static void setUpClass(){
        DrawingEditor editor = new DefaultDrawingEditor();

        mockView = new DefaultDrawingView();

        rectangleFigure = new RectangleFigure();
        rectangleFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(200, 200));

        behindRectangleFigure = new RectangleFigure();
        behindRectangleFigure.setBounds(new Point2D.Double(100, 100), new Point2D.Double(150, 150));

        Drawing drawing = new DefaultDrawing();
        drawing.add(behindRectangleFigure);
        drawing.add(rectangleFigure);
        mockView.setDrawing(drawing);

        editor.add(mockView);
        editor.setTool(selectionTool);
    }

    @Before
    public void setUp(){
        //Resets mouse position and also unselects figures.
        selectionTool.mousePressed(mockMouseEvent(20, 20));
        selectionTool.mouseReleased(mockMouseEvent(20, 20));
    }


    @Test
    public void testGetNewTracker_SelectArea() {
        selectionTool.mousePressed(mockMouseEvent(10,10));

        Tool resultTracker = selectionTool.getHandleTracker();
        assertTrue(resultTracker instanceof SelectAreaTracker);
    }

    @Test
    public void testGetNewTracker_DragTracker() {
        selectionTool.mousePressed(mockMouseEvent(
                (int)rectangleFigure.getStartPoint().getX() + 10,
                (int)rectangleFigure.getStartPoint().getY() + 10));

        Tool resultTracker = selectionTool.getHandleTracker();
        assertTrue(resultTracker instanceof DragTracker);
    }
    @Test
    public void testGetNewTracker_HandleTracker() {
        selectionTool.mousePressed(mockMouseEvent(
                (int)rectangleFigure.getStartPoint().getX(),
                (int)rectangleFigure.getStartPoint().getY()));
        selectionTool.mouseReleased(mockMouseEvent(
                (int)rectangleFigure.getStartPoint().getX(),
                (int)rectangleFigure.getStartPoint().getY()));
        selectionTool.mousePressed(mockMouseEvent(
                (int)rectangleFigure.getStartPoint().getX(),
                (int)rectangleFigure.getStartPoint().getY()));

        Tool resultTracker = selectionTool.getHandleTracker();
        assertTrue(resultTracker instanceof HandleTracker);
    }

    @Test
    public void testFigureSelection(){
        assertFalse(mockView.getSelectedFigures().contains(rectangleFigure));
        assertFalse(mockView.getSelectedFigures().contains(behindRectangleFigure));

        selectionTool.mousePressed(mockMouseEvent(125,125));

        assertTrue(mockView.getSelectedFigures().contains(rectangleFigure));
        assertFalse(mockView.getSelectedFigures().contains(behindRectangleFigure));
    }
    @Test
    public void testDragSelection(){
        selectionTool.mousePressed(mockMouseEvent(20,20));

        assertFalse(mockView.getSelectedFigures().contains(rectangleFigure));
        assertFalse(mockView.getSelectedFigures().contains(behindRectangleFigure));

        selectionTool.mouseDragged(mockMouseEvent(250,250));
        selectionTool.mouseReleased(mockMouseEvent(250,250));

        assertTrue(mockView.getSelectedFigures().contains(rectangleFigure));
        assertTrue(mockView.getSelectedFigures().contains(behindRectangleFigure));
    }
    @Test
    public void testBehindSelect(){
        assertFalse(mockView.getSelectedFigures().contains(rectangleFigure));
        assertFalse(mockView.getSelectedFigures().contains(behindRectangleFigure));

        MouseEvent ms = new MouseEvent((Component) mockView, MouseEvent.MOUSE_PRESSED,
                System.currentTimeMillis(), InputEvent.ALT_DOWN_MASK,
                125, 125, 1,
                false);
        selectionTool.mousePressed(ms);


        assertFalse(mockView.getSelectedFigures().contains(rectangleFigure));
        assertTrue(mockView.getSelectedFigures().contains(behindRectangleFigure));
    }
    @Test
    public void testEmptyDragSelection(){
        selectionTool.mousePressed(mockMouseEvent(10,10));

        assertFalse(mockView.getSelectedFigures().contains(rectangleFigure));
        assertFalse(mockView.getSelectedFigures().contains(behindRectangleFigure));

        selectionTool.mouseDragged(mockMouseEvent(20,20));
        selectionTool.mouseReleased(mockMouseEvent(20,20));

        assertFalse(mockView.getSelectedFigures().contains(rectangleFigure));
        assertFalse(mockView.getSelectedFigures().contains(behindRectangleFigure));
    }

    @Test
    public void testFigureTransform(){
        int x = (int) rectangleFigure.getStartPoint().getX();
        int y = (int) rectangleFigure.getStartPoint().getY();

        selectionTool.mousePressed(mockMouseEvent(x,y));

        selectionTool.mouseDragged(mockMouseEvent(x+5,y+5));
        selectionTool.mouseReleased(mockMouseEvent(x+5,y+5));

        assertEquals(rectangleFigure.getStartPoint(), new Point2D.Double(x + 5, y + 5));
    }
}
