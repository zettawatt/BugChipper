package bugchipper.gui.tabs;

import javax.swing.*;
import java.awt.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.*;

public class TabButton extends JButton implements CommandInterface {

    JTabbedPane pane;
    TabPane tab;
    ImageIcon close;

    public void processEvent() {
        int i = pane.indexOfTabComponent(tab);
        if (i != -1) {
            pane.remove(i);
        }
    }

    public TabButton(JTabbedPane inp_pane, TabPane inp_tab) {
        close = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/close-tab.png"));
        this.setIcon(close);
        pane = inp_pane;
        tab  = inp_tab;
        setPreferredSize(new Dimension(16,16));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusable(false);
    }
}
