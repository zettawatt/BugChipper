package bugchipper.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class AddProjToolBut extends JButton implements CommandInterface {
    Mediator mdtr;
    ImageIcon addProj;

    public void processEvent() {
        mdtr.AddProj();
    }

    public AddProjToolBut (String tooltip, Mediator inp_mdtr) {
        addProj = createImageIcon("../icons/22x22/folder-new-7.png", "Add Project Icon");
        this.setIcon(addProj);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerAddProjToolBut(this);
    }

    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = AddProjToolBut.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

