package bugchipper.gui.tables;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.*;

public class MainTable extends JTable implements CommandInterface {
    Mediator mdtr;

    public void processEvent() {
        int row = this.getSelectedRow();
        int column = this.getSelectedColumn();
        String columnName = this.getColumnName(column);
        Object cellValue = this.getValueAt(row,column);
        String cellName  = cellValue.toString();
        //mdtr.log.addData("selected cell!: "+Integer.toString(row)+", "+Integer.toString(column));
        mdtr.openTab(columnName, cellName);
    }

    public MainTable (Vector<String> columnNames, Vector<Vector<String>> data, Mediator inp_mdtr) {
        super(data, columnNames);
        mdtr = inp_mdtr;

        /**
         * Setup Table Properties
         */
        this.setAutoCreateRowSorter(true);
    }
}
            
