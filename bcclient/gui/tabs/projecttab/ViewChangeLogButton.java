package bcclient.gui.tabs.projecttab;

import javax.swing.*;
import bcclient.gui.eventhandlers.*;

public class ViewChangeLogButton extends JButton implements CommandInterface {
    
    ProjectTabPanel mdtr;

    public void processEvent() {
        mdtr.viewLog();
    }

    public ViewChangeLogButton (String name, ProjectTabPanel inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}
