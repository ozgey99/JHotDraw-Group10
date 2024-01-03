package org.jhotdraw.samples.svg.steps;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.draw.*;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.tool.*;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class ThenSelectionAssertions extends Stage<ThenSelectionAssertions> {

    @ExpectedScenarioState
    SelectionTool selectionTool;
    @ExpectedScenarioState
    DefaultDrawingView mockView;

    public ThenSelectionAssertions toolIsSelectAreaTracker() {
        assertTrue(selectionTool.getHandleTracker() instanceof SelectAreaTracker);
        return self();
    }

    public ThenSelectionAssertions toolIsDragTracker() {
        assertTrue(selectionTool.getHandleTracker() instanceof DragTracker);
        return self();
    }

    public ThenSelectionAssertions toolIsHandleTracker() {
        assertTrue(selectionTool.getHandleTracker() instanceof HandleTracker);
        return self();
    }

    public ThenSelectionAssertions figureIsSelected(Figure figure) {
        assertTrue(mockView.getSelectedFigures().contains(figure));
        return self();
    }

    public ThenSelectionAssertions figureIsNotSelected(Figure figure) {
        assertFalse(mockView.getSelectedFigures().contains(figure));
        return self();
    }

    public ThenSelectionAssertions figureStartPointIsAt(Figure figure, Point2D.Double pos){
        assertEquals(figure.getStartPoint(), pos);
        return self();
    }

}
