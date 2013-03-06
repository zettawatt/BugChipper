package bcclient.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bcclient.gui.eventhandlers.*;
import bcclient.gui.*;
import bcclient.*;

public class AddProjToolBut extends JButton implements CommandInterface {
    Mediator mdtr;
    ImageIcon addProj;

    public void processEvent() {
        mdtr.AddProjPrompt();
    }

    public AddProjToolBut (String tooltip, Mediator inp_mdtr) {
        addProj = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/project-add.png"));
        this.setIcon(addProj);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerAddProjToolBut(this);
    }
}

