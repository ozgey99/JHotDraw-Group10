package org.jhotdraw.samples.svg.steps;

import com.tngtech.jgiven.Stage;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.tool.SelectionTool;

import java.awt.*;
import java.awt.event.MouseEvent;

public class WhenSelectionTool extends Stage<WhenSelectionTool> {

    private SelectionTool selectionTool;
    private DrawingView view;
    private MouseEvent lastMouseEvent;

    public WhenSelectionTool toolIs(SelectionTool tool, DrawingView view) {
        this.selectionTool = tool;
        return self();
    }

    public WhenSelectionTool mousePressedAt(int x, int y) {
        lastMouseEvent = mockMouseEvent(x, y, MouseEvent.MOUSE_PRESSED);
        selectionTool.mousePressed(lastMouseEvent);
        return self();
    }

    public WhenSelectionTool mouseReleasedAt(int x, int y) {
        lastMouseEvent = mockMouseEvent(x, y, MouseEvent.MOUSE_RELEASED);
        selectionTool.mouseReleased(lastMouseEvent);
        return self();
    }

    public WhenSelectionTool mouseDraggedTo(int x, int y) {
        lastMouseEvent = mockMouseEvent(x, y, MouseEvent.MOUSE_DRAGGED);
        selectionTool.mouseDragged(lastMouseEvent);
        return self();
    }

    // Helper method to create mock MouseEvents
    private MouseEvent mockMouseEvent(int x, int y, int event) {
        return new MouseEvent((Component) view, event,
                System.currentTimeMillis(), 0, x, y, 1, false);
    }
}
