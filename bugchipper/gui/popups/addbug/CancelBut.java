package bugchipper.gui.popups.addbug;

import javax.swing.*;
import bugchipper.gui.eventhandlers.*;

public class CancelBut extends JButton implements CommandInterface {
    AddBugDialog mdtr;

    public void processEvent() {
        mdtr.Cancel();
    }

    public CancelBut (String name, AddBugDialog inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}

