package bcclient.gui.tabs.projecttab;

import javax.swing.*;
import bcclient.gui.eventhandlers.*;

public class EditProjButton extends JButton implements CommandInterface {
    
    ProjectTabPanel mdtr;

    public void processEvent() {
        mdtr.editProj();
    }

    public EditProjButton (String name, ProjectTabPanel inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}
