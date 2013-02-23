package bugchipper.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class RefreshAllToolBut extends JButton implements CommandInterface {
    Mediator mdtr;
    ImageIcon addBug;

    public void processEvent() {
        mdtr.RefreshAll();
    }

    public RefreshAllToolBut (String tooltip, Mediator inp_mdtr) {
        addBug = createImageIcon("../icons/16x16/refresh-all.png", "Refresh All Icon");
        this.setIcon(addBug);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerRefreshAllToolBut(this);
    }

    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = RefreshAllToolBut.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

