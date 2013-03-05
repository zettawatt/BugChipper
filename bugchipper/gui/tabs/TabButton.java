package bugchipper.gui.tabs;

import javax.swing.*;
import java.awt.*;
import bugchipper.gui.eventhandlers.*;

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
        close = createImageIcon("../icons/16x16/close-tab.png", "Close Tab");
        this.setIcon(close);
        pane = inp_pane;
        tab  = inp_tab;
        setPreferredSize(new Dimension(16,16));
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusable(false);
    }

    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = TabButton.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
