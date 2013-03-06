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
        JMenu viewMenu = new JMenu("View");
        JMenu dbMenu   = new JMenu("Database");
        JMenu helpMenu = new JMenu("Help");

        /**
         * Initialize Menu Items
         */
        AddProjMenuItem fileAddProj       = new AddProjMenuItem("Add Project", mdtr);
        AddBugMenuItem fileAddBug         = new AddBugMenuItem("Add Bug", mdtr);
        OpenProjMenuItem fileOpenProj     = new OpenProjMenuItem("Open Project", mdtr);
        QuitMenuItem fileQuit             = new QuitMenuItem("Quit", mdtr);
        PrefsMenuItem editPrefs           = new PrefsMenuItem("Preferences...", mdtr);
        RefreshMenuItem viewRefresh       = new RefreshMenuItem("Refresh", mdtr);
        RefreshAllMenuItem viewRefreshAll = new RefreshAllMenuItem("Refresh All", mdtr);
        ViewLogMenuItem viewViewLog       = new ViewLogMenuItem("View Log", mdtr);
        LoginMenuItem dbLogin             = new LoginMenuItem("Login", mdtr);
        LogoutMenuItem dbLogout           = new LogoutMenuItem("Logout", mdtr);
        AdminMenuItem dbAdmin             = new AdminMenuItem("Database Management", mdtr);
        AboutMenuItem helpAbout           = new AboutMenuItem("About", mdtr);

        /**
         * Add event handlers to menu items
         */
        buttonHandler bh = new buttonHandler();

        fileAddProj.addActionListener(bh);
        fileAddBug.addActionListener(bh);
        fileOpenProj.addActionListener(bh);
        fileQuit.addActionListener(bh);
        editPrefs.addActionListener(bh);
        viewRefresh.addActionListener(bh);
        viewRefreshAll.addActionListener(bh);
        viewViewLog.addActionListener(bh);
        dbLogin.addActionListener(bh);
        dbLogout.addActionListener(bh);
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

        // View Menu
        viewMenu.add(viewRefresh);
        viewMenu.add(viewRefreshAll);
        viewMenu.addSeparator();
        viewMenu.add(viewViewLog);

        // Database Menu
        dbMenu.add(dbLogin);
        dbMenu.add(dbLogout);
        dbMenu.addSeparator();
        dbMenu.add(dbAdmin);

        // Help Menu
        helpMenu.add(helpAbout);

        /**
         * Add the menus to the menubar
         */
        this.add(fileMenu);
        this.add(editMenu);
        this.add(viewMenu);
        this.add(dbMenu);
        this.add(Box.createHorizontalGlue()); // Horizontal filler
        this.add(helpMenu);
    }
}
