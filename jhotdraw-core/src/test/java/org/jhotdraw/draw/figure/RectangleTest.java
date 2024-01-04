package org.jhotdraw.draw.figure;

import static org.testng.Assert.assertTrue;
import org.testng.annotations.Test;

public class RectangleTest {
    
    /**
     * Test of clone method, of class Main.
     */
    @Test
    public void testClone() {
        RectangleFigure r = new RectangleFigure();
        RectangleFigure clone = r.clone();
        assertTrue(r.rectangle.equals(clone.rectangle));
        System.out.println("testClone passed");
    }
}
