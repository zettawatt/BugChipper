package bugchipper.gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class PrefsMenuItem extends JMenuItem implements CommandInterface {
    Mediator mdtr;

    public void processEvent() {
        mdtr.Prefs();
    }

    public PrefsMenuItem (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
        mdtr.registerPrefsMenuItem(this);
    }
}

