package bcclient.gui.eventhandlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Handles all events from GUI components
public class buttonHandler implements ActionListener {
    public void actionPerformed (ActionEvent e) {

        /**
         * Handle events from GUI objects
         */
        CommandInterface comObj = (CommandInterface) e.getSource();
        comObj.processEvent();
    }

    public buttonHandler() {
    }
}
