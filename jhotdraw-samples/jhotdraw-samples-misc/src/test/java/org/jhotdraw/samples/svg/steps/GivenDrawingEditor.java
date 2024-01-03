package org.jhotdraw.samples.svg.steps;

import com.tngtech.jgiven.Stage;

import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import com.tngtech.jgiven.annotation.ScenarioState;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.draw.tool.SelectionTool;

public class GivenDrawingEditor extends Stage<GivenDrawingEditor> {
    @ScenarioState
    Drawing drawing;

    @ProvidedScenarioState
    SelectionTool selectionTool;
    @ProvidedScenarioState
    DefaultDrawingView mockView;

    public SelectionTool getSelectionTool() {
        return selectionTool;
    }

    public DefaultDrawingView getMockView() {
        return mockView;
    }

    public GivenDrawingEditor aViewWithARectangle(RectangleFigure rectangleFigure) {
        DefaultDrawingEditor editor = new DefaultDrawingEditor();
        selectionTool = new SelectionTool();
        drawing = new DefaultDrawing();
        mockView = new DefaultDrawingView();
        drawing.add(rectangleFigure);
        mockView.setDrawing(drawing);
        editor.add(mockView);
        editor.setTool(selectionTool);
        return self();
    }
    public GivenDrawingEditor aRectangle(RectangleFigure rectangleFigure){
        drawing.add(rectangleFigure);
        return self();
    }
}
