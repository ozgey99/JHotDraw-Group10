package org.jhotdraw.draw.figure;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import org.jhotdraw.undo.UndoRedoManager;

import static org.junit.Assert.assertEquals;

public class AfterAction extends Stage<AfterAction> {

    @ExpectedScenarioState
    protected UndoRedoManager undoRedoManager;

    @ExpectedScenarioState
    protected EditTest edit;

    public void the_action_can_be_undone() {
        assertEquals(undoRedoManager.getUndoPresentationName(), edit.getUndoPresentationName());
    }

    public void the_action_can_be_redone() {
        assertEquals(undoRedoManager.getRedoPresentationName(), edit.getRedoPresentationName());
    }
}
