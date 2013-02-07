package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.menubar.*;

public class MainGUI {

    private Mediator mdtr = new Mediator();

    public void guiInit() {
    /**
     * Create frame for application window
     */
    JFrame frame = new JFrame ();
    
    /**
     * Setup frame properties
     */
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("BugChipper");
    frame.setSize(500,400);
    frame.setLocationRelativeTo(null);

    //JPanel contentPane = new JPanel(new BorderLayout());
    //contentPane.setOpaque(true);
    

    /**
     * Add the menubar
     */
    MainMenuBar mb = new MainMenuBar(mdtr);
    frame.setJMenuBar(mb);

    //frame.setContentPane(contentPane, BorderLayout.SOUTH);
    
    //frame.pack();
    frame.setVisible(true);
    }

}
