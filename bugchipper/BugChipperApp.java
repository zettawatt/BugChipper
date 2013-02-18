package bugchipper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.*;
import bugchipper.database.*;

public class BugChipperApp {
    {
        String dbURL = "jdbc:mysql://localhost:3306/";
        String dbName = "bugchipper";
        Mediator mdtr = new Mediator();
        MainGUI gui   = new MainGUI(mdtr);
        DAO dao       = new DAO(dbURL, dbName, mdtr);
    }
}
    
    
