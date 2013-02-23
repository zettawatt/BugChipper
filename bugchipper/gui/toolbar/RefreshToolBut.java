package bugchipper.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class RefreshToolBut extends JButton implements CommandInterface {
    Mediator mdtr;
    ImageIcon addBug;

    public void processEvent() {
        mdtr.Refresh();
    }

    public RefreshToolBut (String tooltip, Mediator inp_mdtr) {
        addBug = createImageIcon("../icons/16x16/refresh.png", "Refresh Icon");
        this.setIcon(addBug);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerRefreshToolBut(this);
    }

    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = RefreshToolBut.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

