package bugchipper.gui;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.menubar.*;
import bugchipper.gui.toolbar.*;
import bugchipper.gui.tables.*;
import bugchipper.gui.tabs.*;
import bugchipper.*;

public class MainGUI {
    Mediator mdtr;
    public JPanel contentPane;
    public JScrollPane scrollPane;
    public MainTable table;
    public JTabbedPane mainTab;

    public MainGUI (Mediator inp_mdtr) {
        mdtr = inp_mdtr;
        mdtr.registerMainGUI(this);
        /**
         * Create frame for application window
         */
        JFrame frame = new JFrame ();
        //FIXME: Need to figure out how to set the frame icon
        //ImageIcon bugchipperIcon = new ImageIcon("./icons/22x22/bug-buddy.png");
        //frame.setIconImage(bugchipperIcon.getImage());
    
    /**
     * Setup frame properties
     */
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("BugChipper");
    frame.setLocationRelativeTo(null);

    // For any button that is highlighted, the Enter key will activate it
    UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);

    contentPane = new JPanel(new BorderLayout());
    contentPane.setOpaque(true);

    /**
     * Initialize Column Header
     */
    Vector<String> columnNames = new Vector<String>();
    columnNames.add("Project");
    columnNames.add("Open Issues");
    columnNames.add("Date Modified");
    columnNames.add("Project Lead");

    Vector<Vector<String>> data = new Vector<Vector<String>>();

    table = new MainTable(columnNames, data, mdtr);
    mouseHandler mh = new mouseHandler();
    table.addMouseListener(mh);
    scrollPane = new JScrollPane(table);

    mainTab = new JTabbedPane();
    mainTab.setTabLayoutPolicy(JTabbedPane.WRAP_TAB_LAYOUT);
    mainTab.add("All Proj", scrollPane);
    initTab(0);

    /**
     * Add the menubar, toolbar, and contentpane
     */
    LoginStat ls = new LoginStat(mdtr);

    MainMenuBar mb = new MainMenuBar(mdtr);
    frame.setJMenuBar(mb);

    MainToolBar tb = new MainToolBar("BugChipper Toolbar", mdtr);
    contentPane.add(tb, BorderLayout.PAGE_START);
    contentPane.add(mainTab, BorderLayout.CENTER);
    contentPane.add(ls, BorderLayout.PAGE_END);
    contentPane.setPreferredSize(new Dimension(800, 500));
    

    frame.setContentPane(contentPane);
    
    frame.pack();
    frame.setVisible(true);
    }

    public void initTab (int i) {
        mainTab.setTabComponentAt (i, new TabPane(mainTab));
    }
}
