package bugchipper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.*;
import bugchipper.database.*;

public class BugChipperApp {
    {
        Mediator mdtr = new Mediator();
        MainGUI gui   = new MainGUI(mdtr);
        DAO dao       = new DAO(mdtr);
    }
}
    
    
