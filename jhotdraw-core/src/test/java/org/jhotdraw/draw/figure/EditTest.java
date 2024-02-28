package org.jhotdraw.draw.figure;


import javax.swing.undo.CannotRedoException;
import javax.swing.undo.CannotUndoException;
import javax.swing.undo.UndoableEdit;

class EditTest implements UndoableEdit {

    private final boolean isSignificant;
    private final boolean canUndo;
    private final boolean canRedo;

    public EditTest(boolean isSignificant, boolean canUndo, boolean canRedo) {
        super();
        this.isSignificant = isSignificant;
        this.canUndo = canUndo;
        this.canRedo = canRedo;
    }

    @Override
    public void undo() throws CannotUndoException {}

    @Override
    public boolean canUndo() {
        return this.canUndo;
    }

    @Override
    public void redo() throws CannotRedoException {}

    @Override
    public boolean canRedo() {
        return this.canRedo;
    }

    @Override
    public void die() {
    }

    @Override
    public boolean addEdit(UndoableEdit anEdit) {
        return false;
    }

    @Override
    public boolean replaceEdit(UndoableEdit anEdit) {
        return false;
    }

    @Override
    public boolean isSignificant() {
        return this.isSignificant;
    }

    @Override
    public String getPresentationName() {
        return "Presentation Name";
    }

    @Override
    public String getUndoPresentationName() {
        return "Undo";
    }

    @Override
    public String getRedoPresentationName() {
        return "Redo";
    }
}

