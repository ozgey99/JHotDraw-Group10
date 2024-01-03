package org.jhotdraw.samples.svg.steps;

import com.tngtech.jgiven.Stage;

import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.jhotdraw.draw.tool.SelectionTool;

public class GivenDrawingEditor extends Stage<GivenDrawingEditor> {

    private DefaultDrawingEditor editor;
    Drawing drawing;

    public GivenDrawingEditor aView(DrawingView view) {
        editor = new DefaultDrawingEditor();
        drawing = new DefaultDrawing();

        view.setDrawing(drawing);
        editor.add(view);

        return self();
    }
    public GivenDrawingEditor aRectangleFigure(RectangleFigure rectangleFigure){
        drawing.add(rectangleFigure);

        return self();
    }
    public GivenDrawingEditor aSelectionTool(SelectionTool selectionTool){
        editor.setTool(selectionTool);

        return self();
    }
}
