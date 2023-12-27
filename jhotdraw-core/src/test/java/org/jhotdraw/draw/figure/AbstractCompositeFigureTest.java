package org.jhotdraw.draw.figure;

import org.jhotdraw.draw.MockFigureImpl;
import org.jhotdraw.draw.action.ArrangeActionType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class AbstractCompositeFigureTest {
    @Test
    void arrangeWorksCorrectlyForSendToBack() {
        // arrange
        AbstractCompositeFigureImpl abstractCompositeFigure = new AbstractCompositeFigureImpl();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        abstractCompositeFigure.setChildren(list);

        // act
        abstractCompositeFigure.arrange(testFigure2, ArrangeActionType.SEND_TO_BACK);
        List<Figure> capturedList = abstractCompositeFigure.getChildren();

        // assert
        Assertions.assertEquals(2, capturedList.size());
        Assertions.assertEquals(0, capturedList.indexOf(testFigure2));
        Assertions.assertEquals(1, capturedList.indexOf(testFigure1));
    }

    @Test
    void arrangeWorksCorrectlyForBringToFront() {
        // arrange
        AbstractCompositeFigureImpl abstractCompositeFigure = new AbstractCompositeFigureImpl();
        MockFigureImpl testFigure1 = new MockFigureImpl("testFigure1");
        MockFigureImpl testFigure2 = new MockFigureImpl("testFigure2");

        ArrayList<Figure> list = new ArrayList<>();
        list.add(testFigure1);
        list.add(testFigure2);
        abstractCompositeFigure.setChildren(list);

        // act
        abstractCompositeFigure.arrange(testFigure1, ArrangeActionType.BRING_TO_FRONT);
        List<Figure> capturedList = abstractCompositeFigure.getChildren();

        // assert
        Assertions.assertEquals(2, capturedList.size());
        Assertions.assertEquals(0, capturedList.indexOf(testFigure2));
        Assertions.assertEquals(1, capturedList.indexOf(testFigure1));
    }

    static class AbstractCompositeFigureImpl extends AbstractCompositeFigure {
        void setChildren(ArrayList<Figure> children) {
            super.children = children;
        }
    }
}
