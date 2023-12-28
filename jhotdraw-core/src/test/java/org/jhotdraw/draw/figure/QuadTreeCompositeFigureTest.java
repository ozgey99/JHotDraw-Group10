package org.jhotdraw.draw.figure;

import org.jhotdraw.draw.MockFigureImpl;
import org.jhotdraw.draw.action.ArrangeActionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class QuadTreeCompositeFigureTest {
    @Test
    void arrangeWorksCorrectlyForSendToBack() {
        // arrange
        QuadTreeCompositeFigureImpl abstractCompositeFigure = new QuadTreeCompositeFigureImpl();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        abstractCompositeFigure.setChildren(list);

        // act
        abstractCompositeFigure.arrange(testFigure2, ArrangeActionType.SEND_TO_BACK);
        List<Figure> figures = abstractCompositeFigure.getChildren();

        // assert
        Assertions.assertEquals(2, figures.size());
        Assertions.assertEquals(0, figures.indexOf(testFigure2));
        Assertions.assertEquals(1, figures.indexOf(testFigure1));
    }

    @Test
    void arrangeWorksCorrectlyForBringToFront() {
        // arrange
        QuadTreeCompositeFigureImpl abstractCompositeFigure = new QuadTreeCompositeFigureImpl();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        abstractCompositeFigure.setChildren(list);

        // act
        abstractCompositeFigure.arrange(testFigure1, ArrangeActionType.BRING_TO_FRONT);
        List<Figure> figures = abstractCompositeFigure.getChildren();

        // assert
        Assertions.assertEquals(2, figures.size());
        Assertions.assertEquals(0, figures.indexOf(testFigure2));
        Assertions.assertEquals(1, figures.indexOf(testFigure1));
    }

    static class QuadTreeCompositeFigureImpl extends QuadTreeCompositeFigure {
        void setChildren(ArrayList<Figure> children) {
            super.children = children;
        }
    }
}
