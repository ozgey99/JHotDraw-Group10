package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.figure.Figure;

public class GivenTwoFigures extends Stage<GivenTwoFigures> {
    @ProvidedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ProvidedScenarioState
    Figure testFigure1;

    @ProvidedScenarioState
    Figure testFigure2;

    public GivenTwoFigures two_figures() {
        DefaultDrawingView defaultDrawingView = new DefaultDrawingView();
        quadTreeDrawing = new QuadTreeDrawing();

        defaultDrawingView.setDrawing(quadTreeDrawing);
        defaultDrawingView.getDrawing().add(testFigure1);
        defaultDrawingView.getDrawing().add(testFigure2);

        return self();
    }
}
