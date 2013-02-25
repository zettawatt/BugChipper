package bugchipper.gui.tables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class MainTable extends JTable {
    Mediator mdtr;

    public MainTable (Vector<String> columnNames, Vector<Vector<String>> data, Mediator inp_mdtr) {
        super(data, columnNames);
        mdtr = inp_mdtr;

        /**
         * Setup Table Properties
         */
        this.setAutoCreateRowSorter(true);
        this.setFillsViewportHeight(true);
    }
}
            
