package bcclient.gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bcclient.gui.eventhandlers.*;
import bcclient.gui.*;
import bcclient.*;

public class AddProjMenuItem extends JMenuItem implements CommandInterface {
    Mediator mdtr;

    public void processEvent() {
        mdtr.AddProjPrompt();
    }

    public AddProjMenuItem (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
        mdtr.registerAddProjMenuItem(this);
    }
}

