/*
 * @(#)RedoAction.java
 *
 * Copyright (c) 1996-2010 The authors and contributors of JHotDraw.
 * You may not use, copy or modify this file, except in compliance with the
 * accompanying license terms.
 */
package org.jhotdraw.action.edit;

import org.jhotdraw.api.app.Application;
import org.jhotdraw.api.app.View;
import org.jhotdraw.util.ResourceBundleUtil;
import javax.swing.*;

/**
 * Redoes the last user action on the active view.
 * <p>
 * This action requires that the View returns a project
 * specific redo action when invoking getActionMap("redo") on a View.
 * <p>
 * This action is called when the user selects the Redo item in the Edit
 * menu. The menu item is automatically created by the application.
 * <p>
 * If you want this behavior in your application, you have to create an action
 * with this ID and put it in your {@code ApplicationModel} in method
 * {@link org.jhotdraw.app.ApplicationModel#initApplication}.
 *
 *
 * @author Werner Randelshofer
 * @version $Id$
 */
public class RedoAction extends AbstractUndoRedoAction {

    public static final String ID = "edit.redo";
    private static final long serialVersionUID = 1L;
    private final ResourceBundleUtil labels = ResourceBundleUtil.getBundle("org.jhotdraw.action.Labels");

    public RedoAction(Application app, View view) {
        super(app, view);
        labels.configureAction(this, ID);
    }

    @Override
    protected Action getRealUndoRedoAction() {
        return (getActiveView() == null) ? null : getActiveView().getActionMap().get(ID);
    }
}