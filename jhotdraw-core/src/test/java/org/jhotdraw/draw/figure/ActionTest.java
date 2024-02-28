package org.jhotdraw.draw.figure;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ExpectedScenarioState;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.undo.UndoRedoManager;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Action extends Stage<Action> {

    @ExpectedScenarioState
    protected UndoRedoManager undoRedoManager;

    @ProvidedScenarioState
    protected EditTest editTest = new EditTest(true, true, true);

    public void an_undoable_action_is_made() {
        undoRedoManager.addEdit(new EditTest(false, true, false));
        assertFalse(undoRedoManager.canUndo());
        assertFalse(undoRedoManager.canUndoOrRedo());
        undoRedoManager.addEdit(new EditTest(true, true, false));
        assertTrue(undoRedoManager.canUndo());
        assertTrue(undoRedoManager.canUndoOrRedo());
    }

    public void a_redoable_action_is_made() {
        undoRedoManager.addEdit(new EditTest(false, true, false));
        assertFalse(undoRedoManager.canRedo());
        assertFalse(undoRedoManager.canUndoOrRedo());
        undoRedoManager.addEdit(editTest);
        assertTrue(undoRedoManager.canUndo());
        assertTrue(undoRedoManager.canUndoOrRedo());
        undoRedoManager.undo();
        assertTrue(undoRedoManager.canRedo());
        assertTrue(undoRedoManager.canUndoOrRedo());
    }
}
