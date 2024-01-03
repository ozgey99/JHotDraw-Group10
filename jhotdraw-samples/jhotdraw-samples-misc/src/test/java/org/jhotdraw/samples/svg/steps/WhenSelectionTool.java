package org.jhotdraw.samples.svg.steps;

import com.tngtech.jgiven.Stage;
import com.tngtech.jgiven.annotation.*;
import org.jhotdraw.draw.DefaultDrawingView;
import org.jhotdraw.draw.DrawingView;
import org.jhotdraw.draw.tool.SelectionTool;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;

public class WhenSelectionTool extends Stage<WhenSelectionTool> {

    @ExpectedScenarioState
    SelectionTool selectionTool;
    @ExpectedScenarioState
    DefaultDrawingView mockView;

    private MouseEvent lastMouseEvent;

    public WhenSelectionTool mousePressedAt(int x, int y) {
        lastMouseEvent = mockMouseEvent(x, y, MouseEvent.MOUSE_PRESSED);
        selectionTool.mousePressed(lastMouseEvent);
        return self();
    }
    @NestedSteps
    public WhenSelectionTool mouseClickAt(int x, int y){
        mousePressedAt(x, y);
        mouseReleasedAt(x, y);
        return self();
    }
    public WhenSelectionTool mousePressedWithAlt(int x, int y){
        lastMouseEvent = new MouseEvent((Component) mockView, InputEvent.ALT_DOWN_MASK,
                System.currentTimeMillis(), 0, x, y, 1, false);
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
        return new MouseEvent((Component) mockView, event,
                System.currentTimeMillis(), 0, x, y, 1, false);
    }
}
