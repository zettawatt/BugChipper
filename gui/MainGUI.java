package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainGUI {

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
    MainMenuBar mb = new MainMenuBar();
    frame.setJMenuBar(mb.createMenuBar());

    //frame.setContentPane(contentPane, BorderLayout.SOUTH);
    
    //frame.pack();
    frame.setVisible(true);
    }

}
