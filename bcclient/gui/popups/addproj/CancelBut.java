package bcclient.gui.popups.addproj;

import javax.swing.*;
import bcclient.gui.eventhandlers.*;

public class CancelBut extends JButton implements CommandInterface {
    AddProjDialog mdtr;

    public void processEvent() {
        mdtr.Cancel();
    }

    public CancelBut (String name, AddProjDialog inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}

