package bugchipper.gui.eventhandlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class mouseHandler implements MouseListener {
    public void mousePressed (MouseEvent e) {
        if (e.getClickCount() == 2) {
            CommandInterface comObj = (CommandInterface) e.getSource();
            comObj.processEvent();
        }
    }

    public void mouseClicked (MouseEvent e) {
    }

    public void mouseExited (MouseEvent e) {
    }

    public void mouseEntered (MouseEvent e) {
    }

    public void mouseReleased (MouseEvent e) {
    }

    public mouseHandler() {
    }
}
