package gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.*;

public class MainMenuBar {

    private Mediator mdtr = new Mediator();

    public JMenuBar createMenuBar() {
        
        /**
         * Initialize Menubar and Menus
         */
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        /**
         * Initialize Menu Items
         */
        QuitMenuItem fileQuit = new QuitMenuItem("Quit", mdtr);
        OpenProjMenuItem fileOpenProj = new OpenProjMenuItem("Open Project", mdtr);
        AboutMenuItem helpAbout = new AboutMenuItem("About", mdtr);

        /**
         * Add event handlers to menu items
         */
        buttonHandler bh = new buttonHandler();

        fileQuit.addActionListener(bh);
        fileOpenProj.addActionListener(bh);
        helpAbout.addActionListener(bh);

        /**
         * Add the menu items to the menu
         */

        // File Menu
        fileMenu.add(fileOpenProj);
        fileMenu.addSeparator();
        fileMenu.add(fileQuit);

        // Help Menu
        helpMenu.add(helpAbout);

        /**
         * Add the menus to the menubar
         */
        menuBar.add(fileMenu);
        menuBar.add(Box.createHorizontalGlue()); // Horizontal filler
        menuBar.add(helpMenu);
        
        return menuBar;
    }

}
