package bugchipper.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class AddBugToolBut extends JButton implements CommandInterface {
    Mediator mdtr;
    ImageIcon addBug;

    public void processEvent() {
        mdtr.AddBugPrompt();
    }

    public AddBugToolBut (String tooltip, Mediator inp_mdtr) {
        addBug = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/bug-add.png"));
        this.setIcon(addBug);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerAddBugToolBut(this);
    }
}

