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

}
