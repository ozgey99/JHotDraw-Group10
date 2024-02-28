package org.jhotdraw.draw.figure;

import com.tngtech.jgiven.junit.ScenarioTest;
import org.jhotdraw.undo.UndoRedoManager;
import org.junit.Test;

import static org.junit.Assert.*;

public class UndoRedoManagerTest extends ScenarioTest<NoAction, Action, AfterAction> {

    @Test
    public void testUndoRedoLimit() {
        UndoRedoManager instance = new UndoRedoManager();
        assertEquals(100, instance.getLimit());
    }
    @Test
    public void testCanUndo() {
        given().nothingToUndo();
        when().an_undoable_action_is_made();
        then().the_action_can_be_undone();
    }

    @Test
    public void testCanRedo() {
        given().nothingtoRedo();
        when().a_redoable_action_is_made();
        then().the_action_can_be_redone();
    }
}


}
