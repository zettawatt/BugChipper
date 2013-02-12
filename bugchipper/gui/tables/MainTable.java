package bugchipper.gui.tables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class MainTable extends JTable {
    Mediator mdtr;

    public MainTable (String [] columnNames, Object [][] data, Mediator inp_mdtr) {
        super(data, columnNames);
        mdtr = inp_mdtr;

        /**
         * Setup Table Properties
         */
        this.setAutoCreateRowSorter(true);
        this.setFillsViewportHeight(true);
    }
}
            
