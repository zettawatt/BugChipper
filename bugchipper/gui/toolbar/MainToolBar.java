package bugchipper.gui.toolbar;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class MainToolBar extends JToolBar {
    Mediator mdtr;

    public MainToolBar (String name, Mediator inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;

        /**
         * Initialize Toolbar Buttons
         */
        AddBugToolBut addBugBut   = new AddBugToolBut("Add a New Bug", mdtr);
        AddProjToolBut addProjBut = new AddProjToolBut("Add a New Project", mdtr);

        /**
         * Add event handlers to toolbar buttons
         */
        buttonHandler bh = new buttonHandler();

        addBugBut.addActionListener(bh);
        addProjBut.addActionListener(bh);

        this.setFloatable(true);
        this.add(addBugBut);
        this.add(addProjBut);
    }
}