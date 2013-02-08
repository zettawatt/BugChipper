package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.menubar.*;
import gui.toolbar.*;

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
    frame.setLocationRelativeTo(null);

    JPanel contentPane = new JPanel(new BorderLayout());
    contentPane.setOpaque(true);
    JScrollPane scrollPane = new JScrollPane();

    /**
     * Add the menubar, toolbar, and contentpane
     */
    MainMenuBar mb = new MainMenuBar(mdtr);
    frame.setJMenuBar(mb);

    MainToolBar tb = new MainToolBar("BugChipper Toolbar", mdtr);
    contentPane.add(tb, BorderLayout.PAGE_START);
    contentPane.add(scrollPane, BorderLayout.CENTER);
    contentPane.setPreferredSize(new Dimension(500, 300));
    

    frame.setContentPane(contentPane);
    
    frame.pack();
    frame.setVisible(true);
    }

}
