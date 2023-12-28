package org.jhotdraw.draw;

import org.jhotdraw.draw.action.ArrangeActionType;
import org.jhotdraw.draw.figure.Figure;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class QuadTreeDrawingTest {
    @Test
    void arrangeWorksCorrectlyForSendToBack() {
        // arrange
        MockQuadTreeDrawing quadTreeDrawing = new MockQuadTreeDrawing();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        quadTreeDrawing.setChildren(list);

        // act
        quadTreeDrawing.arrange(testFigure2, ArrangeActionType.SEND_TO_BACK);
        List<Figure> figures = quadTreeDrawing.getChildren();

        // assert
        Assertions.assertEquals(2, figures.size());
        Assertions.assertEquals(0, figures.indexOf(testFigure2));
        Assertions.assertEquals(1, figures.indexOf(testFigure1));
    }

    @Test
    void arrangeWorksCorrectlyForBringToFront() {
        // arrange
        MockQuadTreeDrawing quadTreeDrawing = new MockQuadTreeDrawing();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        quadTreeDrawing.setChildren(list);

        // act
        quadTreeDrawing.arrange(testFigure1, ArrangeActionType.BRING_TO_FRONT);
        List<Figure> figures = quadTreeDrawing.getChildren();

        // assert
        Assertions.assertEquals(2, figures.size());
        Assertions.assertEquals(0, figures.indexOf(testFigure2));
        Assertions.assertEquals(1, figures.indexOf(testFigure1));
    }

    static class MockQuadTreeDrawing extends QuadTreeDrawing {
        void setChildren(ArrayList<Figure> children) {
            super.children = children;
        }
    }
}