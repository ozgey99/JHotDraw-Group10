package org.jhotdraw.draw.acceptancetest;

import com.tngtech.jgiven.junit5.ScenarioTest;
import org.jhotdraw.draw.acceptancetest.stage.GivenTwoFigures;
import org.jhotdraw.draw.acceptancetest.stage.ThenFirstFigureIsInFront;
import org.jhotdraw.draw.acceptancetest.stage.WhenFirstFigureIsBroughtToFront;
import org.junit.jupiter.api.Test;

public class BringToFrontTest extends ScenarioTest<GivenTwoFigures,
        WhenFirstFigureIsBroughtToFront, ThenFirstFigureIsInFront> {
    @Test
    public void bringToFront() {
        given().two_figures();
        when().first_figure_is_brought_to_front();
        then().first_figure_is_in_front();
    }
}
