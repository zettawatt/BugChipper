package gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.*;

public class AddProjToolBut extends JButton implements CommandInterface {
    Mediator mdtr;

    public void processEvent() {
        mdtr.AddProj();
    }

    public AddProjToolBut (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
        mdtr.registerAddProjToolBut(this);
    }
}

