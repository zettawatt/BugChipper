package bugchipper.gui.tables;

import javax.swing.*;
import javax.swing.table.*;
import java.util.*;

public class MainTableModel extends DefaultTableModel {

    public MainTableModel (Vector<Vector<String>> data, Vector<String> columnNames) {
        super(data, columnNames);
    }
    
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
