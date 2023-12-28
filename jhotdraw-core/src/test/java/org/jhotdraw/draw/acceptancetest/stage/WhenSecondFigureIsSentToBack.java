package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.action.ArrangeActionType;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.figure.RectangleFigure;

import java.util.ArrayList;

public class WhenSecondFigureIsSentToBack extends Stage<WhenSecondFigureIsSentToBack> {
    @ExpectedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ExpectedScenarioState
    ArrayList<Figure> testFigures;

    public WhenSecondFigureIsSentToBack second_figure_is_sent_to_back() {
        Figure testFigure2 = testFigures.stream()
                .filter(figure -> figure instanceof RectangleFigure)
                .findFirst()
                .orElse(null);
        quadTreeDrawing.arrange(testFigure2, ArrangeActionType.SEND_TO_BACK);

        return self();
    }
}
