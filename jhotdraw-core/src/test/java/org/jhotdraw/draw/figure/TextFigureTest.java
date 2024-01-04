package org.jhotdraw.draw.figure;

import org.testng.annotations.Test;

public class TextFigureTest {

    private TextFigure textFigure;

    @org.junit.Before
    public void setup() throws Exception {
        textFigure = new TextFigure();
    }
    @org.junit.After
    public void tearDown() throws Exception {
        textFigure.invalidate();
    }

    @Test
    public void drawText() {

    }
}
