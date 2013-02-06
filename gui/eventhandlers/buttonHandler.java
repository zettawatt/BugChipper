package gui.eventhandlers;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

// Handles all events from GUI components
public class buttonHandler implements ActionListener {
    public void actionPerformed (ActionEvent e) {
        
        /**
         * Exit the GUI if the exit command is initiated
         */
        //if (e.ActionCommand().equals(GUIMediator.EXIT)) {
        //    System.exit(1);
        //}

        /**
         * Handle events from GUI objects
         */
        CommandInterface comObj = (CommandInterface) e.getSource();
        comObj.processEvent();
    }

    public buttonHandler() {
    }
}
