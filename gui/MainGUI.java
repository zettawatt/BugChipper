package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.menubar.*;
import gui.toolbar.*;
import gui.tables.*;

public class MainGUI {

    private Mediator mdtr = new Mediator();

    public void guiInit() {
    /**
     * Create frame for application window
     */
        JFrame frame = new JFrame ();
        //FIXME: Need to figure out how to set the frame icon
        //ImageIcon bugchipperIcon = new ImageIcon("./icons/22x22/bug-buddy.png");
        //frame.setIconImage(bugchipperIcon.getImage());
    
    /**
     * Setup frame properties
     */
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("BugChipper");
    frame.setLocationRelativeTo(null);

    JPanel contentPane = new JPanel(new BorderLayout());
    contentPane.setOpaque(true);

    /**
     * Initialize Column Header
     */
    String [] columnNames = {"Project",
                            "Open Issues",
                            "Date Modified",
                            "Process",
                            "Project Lead"};

    /**
     * Setup Table Data
     */
    Object [][] data = {
        {"Project1", "6", "01/13/13", "65nm", "John Doe"},
        {"Project2", "3", "02/06/13", "65nm", "John Doe"}
    };


    MainTable table = new MainTable(columnNames, data, mdtr);
    JScrollPane scrollPane = new JScrollPane(table);

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
