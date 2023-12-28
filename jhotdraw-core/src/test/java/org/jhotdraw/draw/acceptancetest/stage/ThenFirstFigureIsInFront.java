package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.figure.Figure;
import org.junit.jupiter.api.Assertions;

import java.util.List;

public class ThenFirstFigureIsInFront extends Stage<ThenFirstFigureIsInFront> {
    @ExpectedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ExpectedScenarioState
    Figure testFigure1;

    public ThenFirstFigureIsInFront first_figure_is_in_front() {
        List<Figure> figures = quadTreeDrawing.getChildren();
        Assertions.assertEquals(1, figures.indexOf(testFigure1));

        return self();
    }
}
