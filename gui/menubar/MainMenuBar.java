package gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.*;

public class MainMenuBar extends JMenuBar {
    Mediator mdtr;

    public MainMenuBar (Mediator inp_mdtr) {
        mdtr = inp_mdtr;
        
        /**
         * Initialize Menus
         */
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu helpMenu = new JMenu("Help");

        /**
         * Initialize Menu Items
         */
        QuitMenuItem fileQuit = new QuitMenuItem("Quit", mdtr);
        OpenProjMenuItem fileOpenProj = new OpenProjMenuItem("Open Project", mdtr);
        PrefsMenuItem editPrefs = new PrefsMenuItem("Preferences...", mdtr);
        AboutMenuItem helpAbout = new AboutMenuItem("About", mdtr);

        /**
         * Add event handlers to menu items
         */
        buttonHandler bh = new buttonHandler();

        fileQuit.addActionListener(bh);
        editPrefs.addActionListener(bh);
        fileOpenProj.addActionListener(bh);
        helpAbout.addActionListener(bh);

        /**
         * Add the menu items to the menu
         */

        // File Menu
        fileMenu.add(fileOpenProj);
        fileMenu.addSeparator();
        fileMenu.add(fileQuit);

        // Edit Menu
        editMenu.add(editPrefs);

        // Help Menu
        helpMenu.add(helpAbout);

        /**
         * Add the menus to the menubar
         */
        this.add(fileMenu);
        this.add(editMenu);
        this.add(Box.createHorizontalGlue()); // Horizontal filler
        this.add(helpMenu);
    }
}
