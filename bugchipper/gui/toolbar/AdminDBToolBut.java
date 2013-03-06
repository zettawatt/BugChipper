package bugchipper.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class AdminDBToolBut extends JButton implements CommandInterface {
    Mediator mdtr;
    ImageIcon addBug;

    public void processEvent() {
        mdtr.Admin();
    }

    public AdminDBToolBut (String tooltip, Mediator inp_mdtr) {
        addBug = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/database-management.png"));
        this.setIcon(addBug);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerAdminDBToolBut(this);
    }
}

