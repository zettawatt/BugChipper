package bugchipper.gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class QuitMenuItem extends JMenuItem implements CommandInterface {
    Mediator mdtr;
    ImageIcon exitIcon;

    public void processEvent() {
        mdtr.Exit();
    }

    public QuitMenuItem (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
        mdtr.registerQuitMenuItem(this);
        exitIcon = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/exit.png"));
        this.setIcon(exitIcon);
    }
}

