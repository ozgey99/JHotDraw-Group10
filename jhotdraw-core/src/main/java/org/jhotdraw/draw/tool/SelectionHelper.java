package org.jhotdraw.draw.tool;

import org.jhotdraw.draw.Drawing;
import org.jhotdraw.draw.DrawingEditor;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.figure.Figure;
import org.jhotdraw.draw.handle.Handle;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class SelectionHelper {

    static Figure searchForFigure(DrawingView view, Point anchorPoint, boolean isSelectBehindEnabled){
        // Note: The search sequence used here, must be
        // consistent with the search sequence used by the
        // DefaultHandleTracker, the DefaultSelectAreaTracker and DelegationSelectionTool.
        // If possible, continue to work with the current selection
        Drawing drawing = view.getDrawing();
        Point2D.Double p = view.viewToDrawing(anchorPoint);
        Figure figure = null;
        if (isSelectBehindEnabled) {
            for (Figure f : view.getSelectedFigures()) {
                if (f.contains(p)) {
                    figure = f;
                    break;
                }
            }
        }
        // If the point is not contained in the current selection,
        // search for a figure in the drawing.
        if (figure == null) {
            figure = view.findFigure(anchorPoint);
            while (figure != null && !figure.isSelectable()) {
                figure = drawing.findFigureBehind(p, figure);
            }
        }
        return figure;
    }

}
