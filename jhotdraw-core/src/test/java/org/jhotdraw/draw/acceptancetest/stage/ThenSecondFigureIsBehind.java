package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;
import org.junit.jupiter.api.Assertions;

import java.util.ArrayList;
import java.util.List;

public class ThenSecondFigureIsBehind extends Stage<ThenSecondFigureIsBehind> {
    @ExpectedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ExpectedScenarioState
    ArrayList<Figure> testFigures;

    public ThenSecondFigureIsBehind second_figure_is_behind() {
        List<Figure> figures = quadTreeDrawing.getChildren();
        Figure testFigure2 = testFigures.stream()
                .filter(figure -> figure instanceof RectangleFigure)
                .findFirst()
                .orElse(null);

        Assertions.assertEquals(0, figures.indexOf(testFigure2));

        return self();
    }
}
