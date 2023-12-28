package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.action.ArrangeActionType;
import org.jhotdraw.draw.figure.EllipseFigure;
import org.jhotdraw.draw.figure.Figure;

import java.util.ArrayList;

public class WhenFirstFigureIsBroughtToFront extends Stage<WhenFirstFigureIsBroughtToFront> {
    @ExpectedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ExpectedScenarioState
    ArrayList<Figure> testFigures;

    public WhenFirstFigureIsBroughtToFront first_figure_is_brought_to_front() {
        Figure testFigure1 = testFigures.stream()
                .filter(figure -> figure instanceof EllipseFigure)
                .findFirst()
                .orElse(null);
        quadTreeDrawing.arrange(testFigure1, ArrangeActionType.BRING_TO_FRONT);

        return self();
    }
}
