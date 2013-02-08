package gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.*;

public class MainToolBar extends JToolBar {
    Mediator mdtr;

    public MainToolBar (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;

        /**
         * Initialize Toolbar Buttons
         */
        AddBugToolBut addBugBut   = new AddBugToolBut("Add Bug", mdtr);
        AddProjToolBut addProjBut = new AddProjToolBut("Add Project", mdtr);

        this.setFloatable(true);
        this.add(addBugBut);
        this.add(addProjBut);
    }
}
