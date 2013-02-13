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
        exitIcon = createImageIcon("../icons/16x16/exit.png", "Logged In");
        this.setIcon(exitIcon);
    }

        protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = QuitMenuItem.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}

