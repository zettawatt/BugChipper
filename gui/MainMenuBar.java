package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MainMenuBar {

    public JMenuBar createMenuBar() {

        /**
         * Setup menubar and menus
         */
        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        JMenu helpMenu = new JMenu("Help");

        /**
         * Initialize File Menu
         */
        JMenuItem fileQuit = new JMenuItem("Quit");
        fileMenu.add(fileQuit);

        /**
         * Initialize Help Menu
         */
        JMenuItem helpAbout = new JMenuItem("About");
        helpMenu.add(helpAbout);

        /**
         * Add the menus to the menubar
         */
        menuBar.add(fileMenu);
        menuBar.add(Box.createHorizontalGlue());
        menuBar.add(helpMenu);
        
        return menuBar;
    }
}
