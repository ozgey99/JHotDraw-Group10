package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.figure.EllipseFigure;
import org.jhotdraw.draw.figure.Figure;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ThenFirstFigureIsInFront extends Stage<ThenFirstFigureIsInFront> {
    @ExpectedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ExpectedScenarioState
    ArrayList<Figure> testFigures;

    public ThenFirstFigureIsInFront first_figure_is_in_front() {
        List<Figure> figures = quadTreeDrawing.getChildren();
        Figure testFigure1 = testFigures.stream()
                .filter(figure -> figure instanceof EllipseFigure)
                .findFirst()
                .orElse(null);

        Assertions.assertEquals(1, figures.indexOf(testFigure1));

        return self();
    }
}
