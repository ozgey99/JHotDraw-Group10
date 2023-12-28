package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.action.ArrangeActionType;
import org.jhotdraw.draw.figure.Figure;

public class WhenSecondFigureIsSentToBack extends Stage<WhenSecondFigureIsSentToBack> {
    @ExpectedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ExpectedScenarioState
    Figure testFigure2;

    public WhenSecondFigureIsSentToBack second_figure_is_sent_to_back() {
        quadTreeDrawing.arrange(testFigure2, ArrangeActionType.SEND_TO_BACK);

        return self();
    }
}
