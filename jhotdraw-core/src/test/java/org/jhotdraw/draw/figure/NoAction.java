package org.jhotdraw.draw.figure;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.ProvidedScenarioState;
import org.jhotdraw.undo.UndoRedoManager;

import static org.junit.Assert.assertFalse;

public class NoAction extends Stage<NoAction> {

    @ProvidedScenarioState
    protected UndoRedoManager undoRedoManager;

    public void nothingToUndo() {
        undoRedoManager = new UndoRedoManager();
        assertFalse(undoRedoManager.canUndo());
        assertFalse(undoRedoManager.canUndoOrRedo());
    }

    public void nothingtoRedo() {
        undoRedoManager = new UndoRedoManager();
        assertFalse(undoRedoManager.canRedo());
        assertFalse(undoRedoManager.canUndoOrRedo());
    }
}
