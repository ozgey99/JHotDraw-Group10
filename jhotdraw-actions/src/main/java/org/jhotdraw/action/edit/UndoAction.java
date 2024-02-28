/*
 * @(#)UndoAction.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.action.edit;

import java.awt.event.*;
import java.beans.*;
import javax.swing.*;
import org.jhotdraw.action.AbstractViewAction;
import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.util.ResourceBundleUtil;


/**
 * Undoes the last user action.
 * <p>
 * This action requires that the View returns a project
 * specific undo action when invoking getActionMap("redo") on a View.
 * <p>
 * This action is called when the user selects the Undo item in the Edit
 * menu. The menu item is automatically created by the application.
 * <p>
 * If you want this behavior in your application, you have to create an action
 * with this ID and put it in your {@code ApplicationModel} in method
 * {@link org.jhotdraw.app.ApplicationModel#initApplication}.
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class UndoAction extends AbstractUndoRedoAction {

    public static final String ID = "edit.undo";
    private static final long serialVersionUID = 1L;
    private final ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.action.Labels");

    public UndoAction(Application app, View view) {
        super(app, view);
        labels.configureAction(this, ID);
    }

    @Override
    protected Action getRealUndoRedoAction() {
        return (getActiveView() == null) ? null : getActiveView().getActionMap().get(ID);
    }
}