package org.jhotdraw.draw.action;

import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;

import java.util.Collection;

public abstract class AbstractArrangeAction extends AbstractSelectedAction {
    /**
     * Creates an action which acts on the selected figures on the current view
     * of the specified editor.
     *
     * @param editor
     */
    public AbstractArrangeAction(DrawingEditor editor) {
        super(editor);
    }

    public abstract void arrange(DrawingView view, Collection<Figure> figures);
}
