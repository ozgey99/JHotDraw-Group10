package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.samples.svg.steps.*;
import org.junit.Test;
import com.tngtech.jgiven.junit.*;

import java.awt.geom.Point2D;

public class SelectionBDDTest extends ScenarioTest<GivenDrawingEditor, WhenSelectionTool, ThenSelectionAssertions> {

    @Test
    public void when_mouseEventOccursOutsideFigure_then_useSelectAreaTracker_and_figureIsNotSelected() {
        RectangleFigure rectangleFigure = new RectangleFigure();
        rectangleFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(200, 200));
        int mousePos = 10;
        given().aViewWithARectangle(rectangleFigure);
        when().mousePressedAt(mousePos, mousePos);
        then().toolIsSelectAreaTracker().and().figureIsNotSelected(rectangleFigure);
    }

    @Test
    public void when_mouseEventOccursInsideFigure_then_useDragTracker_and_figureIsSelected(){
        RectangleFigure rectangleFigure = new RectangleFigure();
        rectangleFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(200, 200));
        int mousePos = 50;
        given().aViewWithARectangle(rectangleFigure);
        when().mousePressedAt(mousePos, mousePos);
        then().toolIsDragTracker().and().figureIsSelected(rectangleFigure);
    }

    @Test
    public void when_mouseEventOccursOnEdgeOfFigure_then_useHandleTracker_when_dragged_then_sizeChanges(){
        RectangleFigure rectangleFigure = new RectangleFigure();
        rectangleFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(200, 200));
        int x = (int) rectangleFigure.getStartPoint().x;
        int y = (int) rectangleFigure.getStartPoint().y;
        given().aViewWithARectangle(rectangleFigure);
        when().mouseClickAt(x, y).and().mousePressedAt(x, y);
        then().toolIsHandleTracker();
        x+=12; y+=12;
        when().mouseDraggedTo(x, y).mouseReleasedAt(x, y);
        Point2D.Double newPoint = new Point2D.Double(x, y);
        then().figureStartPointIsAt(rectangleFigure, newPoint);
    }

    @Test
    public void when_mouseDragOccursAlongBothFigures_then_figuresAreSelected(){
        RectangleFigure behindFigure = new RectangleFigure();
        behindFigure.setBounds(new Point2D.Double(60, 60), new Point2D.Double(100, 100));
        RectangleFigure rectangleFigure = new RectangleFigure();
        rectangleFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(150, 150));
        int mousePos = 10;
        int newPos = 200;
        given().aViewWithARectangle(behindFigure).and().aRectangle(rectangleFigure);
        when().mousePressedAt(mousePos, mousePos).mouseDraggedTo(newPos, newPos).mouseReleasedAt(newPos, newPos);
        then().figureIsSelected(rectangleFigure).and().figureIsSelected(behindFigure);
    }
    @Test
    public void when_mousePressedWithAltOnFigureWithFigureBehind_then_behindFigureIsSelected(){
        RectangleFigure behindFigure = new RectangleFigure();
        behindFigure.setBounds(new Point2D.Double(60, 60), new Point2D.Double(100, 100));
        RectangleFigure rectangleFigure = new RectangleFigure();
        rectangleFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(150, 150));
        int mousePos = 70;
        given().aViewWithARectangle(rectangleFigure).and().aRectangle(behindFigure);
        when().mousePressedWithAlt(mousePos, mousePos);
        then().figureIsNotSelected(rectangleFigure).and().figureIsSelected(behindFigure);
    }
}
