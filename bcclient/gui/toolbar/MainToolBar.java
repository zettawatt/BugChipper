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
        RefreshToolBut refreshBut       = new RefreshToolBut("Refresh Current Tab", mdtr);
        RefreshAllToolBut refreshAllBut = new RefreshAllToolBut("Refresh All Tabs", mdtr);
        AddBugToolBut addBugBut         = new AddBugToolBut("Add a New Bug", mdtr);
        AddProjToolBut addProjBut       = new AddProjToolBut("Add a New Project", mdtr);
        ViewLogToolBut viewLogBut       = new ViewLogToolBut("View Log", mdtr);
        AdminDBToolBut adminDBBut       = new AdminDBToolBut("Database Management", mdtr);

        /**
         * Add event handlers to toolbar buttons
         */
        buttonHandler bh = new buttonHandler();

        refreshBut.addActionListener(bh);
        refreshAllBut.addActionListener(bh);
        addBugBut.addActionListener(bh);
        addProjBut.addActionListener(bh);
        viewLogBut.addActionListener(bh);
        adminDBBut.addActionListener(bh);

        this.setFloatable(true);
        this.add(addBugBut);
        this.add(addProjBut);
        this.add(refreshBut);
        this.add(refreshAllBut);
        this.add(viewLogBut);
        this.add(adminDBBut);
    }
}
