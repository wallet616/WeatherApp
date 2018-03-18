package piotr.bartela;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class PanelMoveEventHandler extends MouseAdapter implements MouseListener, MouseMotionListener {

    private boolean is_pressed;

    private int delta_x;
    private int delta_y;

    public PanelMoveEventHandler() {
        this.is_pressed = false;
        delta_x = 0;
        delta_y = 0;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        is_pressed = true;
        delta_x = e.getXOnScreen() - Controller.frame.getLocationOnScreen().x;
        delta_y = e.getYOnScreen() - Controller.frame.getLocationOnScreen().y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        is_pressed = false;
        delta_x = 0;
        delta_y = 0;
        Controller.settings.save();
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (is_pressed) {
            Controller.settings.window_pos_x = e.getXOnScreen() - delta_x;
            Controller.settings.window_pos_y = e.getYOnScreen() - delta_y;

            Controller.frame.setLocation(
                    Controller.settings.window_pos_x,
                    Controller.settings.window_pos_y);
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }
}
