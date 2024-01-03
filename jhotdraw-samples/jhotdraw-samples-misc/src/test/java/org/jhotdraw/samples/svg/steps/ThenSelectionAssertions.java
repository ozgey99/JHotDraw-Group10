package org.jhotdraw.samples.svg.steps;

import com.tngtech.jgiven.Stage;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.tool.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class ThenSelectionAssertions extends Stage<ThenSelectionAssertions> {

    private Tool resultTracker;

    public ThenSelectionAssertions toolIs(SelectionTool selectionTool){
        resultTracker = selectionTool.getHandleTracker();
        return self();
    }

    public ThenSelectionAssertions toolIsSelectAreaTracker() {
        assertTrue(resultTracker instanceof SelectAreaTracker);
        return self();
    }

    public ThenSelectionAssertions toolIsDragTracker() {
        assertTrue(resultTracker instanceof DragTracker);
        return self();
    }

    public ThenSelectionAssertions toolIsHandleTracker() {
        assertTrue(resultTracker instanceof HandleTracker);
        return self();
    }

    public ThenSelectionAssertions figureIsSelected(DrawingView view, Figure figure) {
        assertTrue(view.getSelectedFigures().contains(figure));
        return self();
    }

    public ThenSelectionAssertions figureIsNotSelected(DrawingView view, Figure figure) {
        assertFalse(view.getSelectedFigures().contains(figure));
        return self();
    }

}
