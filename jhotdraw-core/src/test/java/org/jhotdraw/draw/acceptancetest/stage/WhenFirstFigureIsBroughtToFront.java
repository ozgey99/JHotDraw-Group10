package org.jhotdraw.draw.acceptancetest.stage;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.QuadTreeDrawing;
import org.jhotdraw.draw.action.ArrangeActionType;
import org.jhotdraw.draw.figure.Figure;

public class WhenFirstFigureIsBroughtToFront extends Stage<WhenFirstFigureIsBroughtToFront> {
    @ExpectedScenarioState
    QuadTreeDrawing quadTreeDrawing;

    @ExpectedScenarioState
    Figure testFigure1;

    public WhenFirstFigureIsBroughtToFront first_figure_is_brought_to_front() {
        quadTreeDrawing.arrange(testFigure1, ArrangeActionType.BRING_TO_FRONT);

        return self();
    }
}
