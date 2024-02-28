package org.jhotdraw.action.edit;

import org.jhotdraw.action.AbstractViewAction;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public abstract class AbstractUndoRedoAction extends AbstractViewAction {

    private static String ID;
    private final PropertyChangeListener redoActionPropertyListener = new PropertyChangeListener() {
        @Override
        public void propertyChange(PropertyChangeEvent evt) {
            String name = evt.getPropertyName();
            if (name != null && name.equals(AbstractAction.NAME)) {
                putValue(AbstractAction.NAME, evt.getNewValue());
            } else if ("enabled".equals(name)) {
                updateEnabledState();
            }
        }
    };

    public AbstractUndoRedoAction(Application app, View view) {
        super(app, view);
    }

    protected void updateEnabledState() {
        boolean isEnabled = false;
        Action realAction = getRealUndoRedoAction();
        if (realAction != null && realAction != this) {
            isEnabled = realAction.isEnabled();
        }
        setEnabled(isEnabled);
    }

    @Override
    protected void updateView(View oldValue, View newValue) {
        super.updateView(oldValue, newValue);
        if (newValue != null
                && newValue.getActionMap().get(ID) != null
                && newValue.getActionMap().get(ID) != this) {
            putValue(AbstractAction.NAME, newValue.getActionMap().get(ID).
                    getValue(AbstractAction.NAME));
            updateEnabledState();
        }
    }

    @Override
    protected void installViewListeners(View p) {
        super.installViewListeners(p);
        Action undoActionInView = p.getActionMap().get(ID);
        if (undoActionInView != null && undoActionInView != this) {
            undoActionInView.addPropertyChangeListener(redoActionPropertyListener);
        }
    }

    @Override
    protected void uninstallViewListeners(View p) {
        super.uninstallViewListeners(p);
        Action undoActionInView = p.getActionMap().get(ID);
        if (undoActionInView != null && undoActionInView != this) {
            undoActionInView.removePropertyChangeListener(redoActionPropertyListener);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Action realUndoAction = getRealUndoRedoAction();
        if (realUndoAction != null && realUndoAction != this) {
            realUndoAction.actionPerformed(e);
        }
    }

    protected abstract Action getRealUndoRedoAction();
}
