package org.jhotdraw.samples.svg;

import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.draw.tool.SelectionTool;
import org.jhotdraw.samples.svg.steps.*;
import org.junit.Test;
import com.tngtech.jgiven.junit5.*;

import java.awt.geom.Point2D;

public class SelectionBDDTest extends ScenarioTest<GivenDrawingEditor, WhenSelectionTool, ThenSelectionAssertions> {



    @Test
    public void when_mouseEventOccursOutsideFigure_then_useSelectAreaTracker() {
        SelectionTool selectionTool = new SelectionTool();
        DefaultDrawingView mockView = new DefaultDrawingView();
        RectangleFigure rectangleFigure = new RectangleFigure();
        rectangleFigure.setBounds(new Point2D.Double(40, 40), new Point2D.Double(200, 200));
        System.out.println(selectionTool);
        System.out.println(mockView);
        System.out.println(rectangleFigure);


        given().
                aView(mockView).
                and().aRectangleFigure(rectangleFigure).
                and().aSelectionTool(selectionTool);
        when().toolIs(selectionTool, mockView).and().mousePressedAt(10, 10);
        then().toolIs(selectionTool).and().toolIsSelectAreaTracker();
    }

}
