package bcclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bcclient.gui.*;
import bcclient.database.*;

public class BugChipperApp {
    {
        String dbURL = "localhost";
        int dbPort    = 1677;
        Mediator mdtr = new Mediator();
        MainGUI gui   = new MainGUI(mdtr);
        DAO dao       = new DAO(dbURL, dbPort, mdtr);
    }
}
    
    
