package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.EllipseFigure;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;

import java.util.ArrayList;

public class GivenTwoFigures extends Stage<GivenTwoFigures> {
    @ProvidedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ProvidedScenarioState
    ArrayList<Figure> testFigures;

    public GivenTwoFigures two_figures() {
        testFigures = new ArrayList<>();
        quadTreeDrawing = new QuadTreeDrawing();

        Figure testFigure1 = new EllipseFigure(0, 0, 10, 10);
        Figure testFigure2 = new RectangleFigure(5, 5, 5, 5);

        testFigures.add(testFigure1);
        testFigures.add(testFigure2);

        quadTreeDrawing.add(testFigure1);
        quadTreeDrawing.add(testFigure2);

        return self();
    }
}
