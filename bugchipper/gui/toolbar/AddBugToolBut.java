package bugchipper.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class AddBugToolBut extends JButton implements CommandInterface {
    Mediator mdtr;
    ImageIcon addBug;

    public void processEvent() {
        mdtr.AddBug();
    }

    public AddBugToolBut (String tooltip, Mediator inp_mdtr) {
        addBug = createImageIcon("../icons/22x22/bug-buddy.png", "Add Bug Icon");
        this.setIcon(addBug);
        this.setToolTipText(tooltip);
        mdtr = inp_mdtr;
        mdtr.registerAddBugToolBut(this);
    }

    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = AddBugToolBut.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

