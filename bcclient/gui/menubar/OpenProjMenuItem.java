package bcclient.gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bcclient.gui.eventhandlers.*;
import bcclient.gui.*;
import bcclient.*;

public class OpenProjMenuItem extends JMenuItem implements CommandInterface {
    Mediator mdtr;

    public void processEvent() {
        mdtr.OpenProj();
    }

    public OpenProjMenuItem (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
        mdtr.registerOpenProjMenuItem(this);
    }
}

