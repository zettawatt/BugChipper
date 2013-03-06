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
        addBug = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/refresh.png"));
        this.setIcon(addBug);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerRefreshToolBut(this);
    }
}

