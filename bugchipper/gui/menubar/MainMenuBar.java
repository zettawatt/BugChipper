package bugchipper.gui.menubar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class MainMenuBar extends JMenuBar {
    Mediator mdtr;

    public MainMenuBar (Mediator inp_mdtr) {
        mdtr = inp_mdtr;
        
        /**
         * Initialize Menus
         */
        JMenu fileMenu = new JMenu("File");
        JMenu editMenu = new JMenu("Edit");
        JMenu dbMenu   = new JMenu("Database");
        JMenu helpMenu = new JMenu("Help");

        /**
         * Initialize Menu Items
         */
        AddProjMenuItem fileAddProj   = new AddProjMenuItem("Add Project", mdtr);
        AddBugMenuItem fileAddBug     = new AddBugMenuItem("Add Bug", mdtr);
        OpenProjMenuItem fileOpenProj = new OpenProjMenuItem("Open Project", mdtr);
        QuitMenuItem fileQuit         = new QuitMenuItem("Quit", mdtr);
        PrefsMenuItem editPrefs       = new PrefsMenuItem("Preferences...", mdtr);
        LoginMenuItem dbLogin         = new LoginMenuItem("Login", mdtr);
        AdminMenuItem dbAdmin         = new AdminMenuItem("Database Management", mdtr);
        AboutMenuItem helpAbout       = new AboutMenuItem("About", mdtr);

        /**
         * Add event handlers to menu items
         */
        buttonHandler bh = new buttonHandler();

        fileAddProj.addActionListener(bh);
        fileAddBug.addActionListener(bh);
        fileOpenProj.addActionListener(bh);
        fileQuit.addActionListener(bh);
        editPrefs.addActionListener(bh);
        dbLogin.addActionListener(bh);
        dbAdmin.addActionListener(bh);
        helpAbout.addActionListener(bh);

        /**
         * Add the menu items to the menu
         */

        // File Menu
        fileMenu.add(fileAddProj);
        fileMenu.add(fileAddBug);
        fileMenu.addSeparator();
        fileMenu.add(fileOpenProj);
        fileMenu.addSeparator();
        fileMenu.add(fileQuit);

        // Edit Menu
        editMenu.add(editPrefs);

        // Database Menu
        dbMenu.add(dbLogin);
        dbMenu.addSeparator();
        dbMenu.add(dbAdmin);

        // Help Menu
        helpMenu.add(helpAbout);

        /**
         * Add the menus to the menubar
         */
        this.add(fileMenu);
        this.add(editMenu);
        this.add(dbMenu);
        this.add(Box.createHorizontalGlue()); // Horizontal filler
        this.add(helpMenu);
    }
}
