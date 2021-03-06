package bcclient.gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bcclient.gui.eventhandlers.*;
import bcclient.gui.*;
import bcclient.*;

public class RefreshMenuItem extends JMenuItem implements CommandInterface {
    Mediator mdtr;

    public void processEvent() {
        mdtr.Refresh();
    }

    public RefreshMenuItem (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
        mdtr.registerRefreshMenuItem(this);
    }
}

