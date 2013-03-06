package bcclient.gui.popups.addproj;

import javax.swing.*;
import bcclient.gui.eventhandlers.*;

public class FindProjBut extends JButton implements CommandInterface {
    AddProjDialog mdtr;

    public void processEvent() {
        mdtr.Find();
    }

    public FindProjBut (String name, AddProjDialog inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}

