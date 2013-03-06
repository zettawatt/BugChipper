package bcclient.gui.popups.addbug;

import javax.swing.*;
import bcclient.gui.eventhandlers.*;

public class AddBugBut extends JButton implements CommandInterface {
    AddBugDialog mdtr;

    public void processEvent() {
        mdtr.AddBug();
    }

    public AddBugBut (String name, AddBugDialog inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}

