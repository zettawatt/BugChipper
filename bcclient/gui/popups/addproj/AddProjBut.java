package bugchipper.gui.popups.addproj;

import javax.swing.*;
import bugchipper.gui.eventhandlers.*;

public class AddProjBut extends JButton implements CommandInterface {
    AddProjDialog mdtr;

    public void processEvent() {
        mdtr.AddProj();
    }

    public AddProjBut (String name, AddProjDialog inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}

