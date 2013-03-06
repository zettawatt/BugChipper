package bcclient.gui.popups.addproj;

import javax.swing.*;
import bcclient.gui.eventhandlers.*;

public class ApplyBut extends JButton implements CommandInterface {
    AddProjDialog mdtr;

    public void processEvent() {
        mdtr.Apply();
    }

    public ApplyBut (String name, AddProjDialog inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}

