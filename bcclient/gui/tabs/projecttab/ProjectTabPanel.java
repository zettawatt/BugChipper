package bcclient.gui.tabs.projecttab;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import bcclient.*;
import bcclient.database.*;
import bcclient.database.objects.*;
import bcclient.gui.*;
import bcclient.gui.tables.*;
import bcclient.gui.tabs.*;
import bcclient.gui.eventhandlers.*;

public class ProjectTabPanel extends JPanel {
    
    DAO dao;
    Mediator mdtr;
    String projName;
    JLabel projNameIndLabel, projNameLabel, ownerNameIndLabel, ownerNameLabel,
        dateIndLabel, dateLabel, numIssuesIndLabel, numIssuesLabel,
        issuesTableLabel, tableInstructLabel, compTableLabel, catTableLabel,
        viewLogLabel, editProjLabel;
    EditProjButton editProjBtn;
    ViewChangeLogButton viewLogBtn;
    MainTable issueTable, compTable, catTable;
    JScrollPane mainScroller, issueScroller, compScroller, catScroller;
    ProjectObj projObj;

    public ProjectTabPanel (String inp_proj, Mediator inp_mdtr, DAO inp_dao) {
        super(new GridBagLayout());
        
        projName = inp_proj;
        dao = inp_dao;
        mdtr = inp_mdtr;

        this.setOpaque(true);
        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);

        // Pull info from the database
        projObj = dao.getProj(projName);
        String numIssues = Integer.toString(projObj.getNumBugs());
        String modTime = projObj.getModTime();
        String projOwner = projObj.getOwner();

        // Setup Tables

        // Column names
        Vector<String> issueCols = new Vector<String>();
        issueCols.add("Status");
        issueCols.add("ID #");
        issueCols.add("Bug Title");
        issueCols.add("Owner");

        Vector<String> compCols = new Vector<String>();
        compCols.add("Component Name");
        compCols.add("Number of Issues");
        
        Vector<String> catCols = new Vector<String>();
        catCols.add("Key");
        catCols.add("Value");

        // Table data
        Vector<Vector<String>> issueDat = new Vector<Vector<String>>();
        Vector<Vector<String>> compDat = new Vector<Vector<String>>();
        Vector<Vector<String>> catDat = new Vector<Vector<String>>();

        Vector<BugContainer> issues = projObj.getBugs();
        for (BugContainer bugCon : issues) {
            Vector<String> issueRow = new Vector<String>();
            if (bugCon.isClosed()) {
                issueRow.add("closed");
            } else if (bugCon.wasViewed()) {
                issueRow.add("viewed");
            } else {
                issueRow.add("open");
            }

            issueRow.add(Integer.toString(bugCon.bug.getID()));
            issueRow.add(bugCon.bug.getTitle());
            issueRow.add(bugCon.bug.getOwner());

            //Add row to array
            issueDat.add(issueRow);
        }

        Vector<ComponentObj> comps = projObj.getComponents();
        for (ComponentObj comp : comps) {
            Vector<String> compRow = new Vector<String>();
            
            compRow.add(comp.getName());
            compRow.add(Integer.toString(comp.getNumBugs()));

            //Add row to array
            compDat.add(compRow);
        }

        Vector<CategoryObj> cats = projObj.getCategories();
        for (CategoryObj cat : cats) {
            Vector<String> catRow = new Vector<String>();
            
            catRow.add(cat.getKey());
            catRow.add(cat.getVal());

            //Add row to array
            catDat.add(catRow);
        }

        // Table instantiation
        issueTable = new MainTable(issueCols, issueDat, mdtr);
        compTable  = new MainTable(compCols, compDat, mdtr);
        catTable   = new MainTable(catCols, catDat, mdtr);

        mouseHandler mh = new mouseHandler();
        issueTable.addMouseListener(mh);
        compTable.addMouseListener(mh);
        catTable.addMouseListener(mh);

        // Add graphical elements
        projNameIndLabel = new JLabel("Project Name:");
        projNameLabel = new JLabel(projName);
        ownerNameIndLabel = new JLabel("Owner Name:");
        ownerNameLabel = new JLabel(projOwner);
        dateIndLabel = new JLabel("Date last modified:");
        dateLabel = new JLabel(modTime);
        numIssuesIndLabel = new JLabel("Number of open issues:");
        numIssuesLabel = new JLabel(numIssues);
        editProjLabel = new JLabel("Edit the project");
        editProjBtn = new EditProjButton("Edit",this);
        viewLogLabel = new JLabel("View project changelog");
        viewLogBtn = new ViewChangeLogButton("View", this);
        tableInstructLabel = new JLabel("Double click table elements to view details");
        issuesTableLabel = new JLabel("Issues:");
        issueScroller = new JScrollPane(issueTable);
        issueScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        compTableLabel = new JLabel("Componenents:");
        compScroller = new JScrollPane(compTable);
        compScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        catTableLabel = new JLabel("Categories:");
        catScroller = new JScrollPane(catTable);
        catScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        buttonHandler bh = new buttonHandler();
        editProjBtn.addActionListener(bh);
        viewLogBtn.addActionListener(bh);
        
        buildLayout();
    }

    void buildLayout() {
        
        GridBagConstraints c = new GridBagConstraints();

        //Row 0
        int i = 0;
        int j = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.ipadx = 5;
        c.weightx = 0;
        c.weighty = 0;
        c.gridx = i;
        c.gridy = j;
        this.add(projNameIndLabel, c);
        
        i++;
        c.gridx = i;
        this.add(projNameLabel, c);

        i++;
        i++;

        c.gridx = i;
        this.add(viewLogLabel, c);
        
        i++;
        c.gridx = i;
        this.add(viewLogBtn, c);

        //Row 1
        i = 0;
        j++;
        c.gridx = i;
        c.gridy = j;

        this.add(ownerNameIndLabel, c);

        i++;
        c.gridx = i;
        this.add(ownerNameLabel,c);

        i++;
        i++;

        c.gridx = i;
        this.add(editProjLabel,c);

        i++;
        c.gridx = i;
        this.add(editProjBtn,c);

        //Row 2
        i = 0;
        j++;
        c.gridx = i;
        c.gridy = j;
        
        this.add(dateIndLabel,c);

        i++;
        c.gridx = i;
        this.add(dateLabel,c);

        //Row 3
        i=0;
        j++;
        c.gridy = j;
        c.gridx = i;
        this.add(numIssuesIndLabel,c);

        i++;
        c.gridx = i;
        this.add(numIssuesLabel,c);

        //Row 4
        j++;

        //Row 5
        i=0;
        j++;
        c.gridx = i;
        c.gridy = j;
        c.gridwidth = 3;
        c.ipady = 40;

        this.add(tableInstructLabel,c);

        //Row 6
        c.gridwidth = 1;
        j++;
        c.gridy = j;
        c.gridx = i;
        c.ipady = 0;

        //Row 7
        j++;
        c.gridy = j;

        this.add(issuesTableLabel,c);

        //Row 8
        j++;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = j;
        c.ipady = 150;
        c.gridwidth = 5;
        
        this.add(issueScroller,c);

        //Row 9
        j++;
        c.weightx = 0;
        c.weighty = 0;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridy = j;
        c.ipady = 0;
        c.gridwidth = 1;

        this.add(compTableLabel,c);

        c.gridx = 3;
        
        this.add(catTableLabel,c);

        //Row 10
        j++;
        c.weightx = 0.5;
        c.weighty = 0.5;
        c.gridx = 0;
        c.fill = GridBagConstraints.BOTH;
        c.gridy = j;
        c.ipady = 150;
        c.gridwidth = 3;
        
        this.add(compScroller,c);

        c.gridx = 3;
        c.gridwidth = 2;

        this.add(catScroller,c);

        //this.pack();
        this.setVisible(true);
    }

    // Button action methods
    public void editProj() {
        //FIXME: need to create a way to edit the project object attributes
    }

    public void viewLog() {
        //FIXME: need to create a running log for a project that has owner, modification time, actions, etc.
    }
}
