package org.jhotdraw.draw.acceptancetest;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.jhotdraw.draw.acceptancetest.stage.GivenTwoFigures;
import org.jhotdraw.draw.acceptancetest.stage.ThenSecondFigureIsBehind;
import org.jhotdraw.draw.acceptancetest.stage.WhenSecondFigureIsSentToBack;
import org.junit.jupiter.api.Test;

public class SendToBackTest extends ScenarioTest<GivenTwoFigures,
        WhenSecondFigureIsSentToBack, ThenSecondFigureIsBehind> {
    @Test
    public void sendToBack() {
        given().two_figures();
        when().second_figure_is_sent_to_back();
        then().second_figure_is_behind();
    }
}
