package bugchipper.gui.popups.addbug;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import bugchipper.*;
import bugchipper.database.*;
import bugchipper.database.objects.*;
import bugchipper.gui.eventhandlers.*;

/**
 * Display the AddBug Dialog box
 */
public class AddBugDialog extends JDialog {
    Mediator mdtr;
    DAO dao;
    AddBugBut addBugBut;
    CancelBut cancelBut;
    JLabel ownerLabel, titleLabel, descLabel, noteLabel, projectLabel, componentLabel, categoryLabel;
    JScrollPane mainScroller, descScroller, projectScroller, componentScroller, categoryScroller;
    JTextArea descArea, projectArea, componentArea, categoryArea;
    JTextField ownerField, titleField;
    JPanel mainPanel, buttonPanel;

    public AddBugDialog(Mediator inp_mdtr, DAO inp_dao) {
        mdtr = inp_mdtr;
        dao = inp_dao;
        this.setLayout( new BorderLayout());
        this.setTitle("Add Bug to Database");
        this.setPreferredSize(new Dimension(800,400));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);

        ownerLabel     = new JLabel("Enter the owner name:");
        ownerField     = new JTextField();
        titleLabel     = new JLabel("Enter a shot one line description of the bug:");
        titleField     = new JTextField(50);
        descLabel      = new JLabel("Enter more detailed information if relevant:");
        descArea       = new JTextArea();
        descArea.setLineWrap(true);
        descArea.setWrapStyleWord(true);
        descScroller   = new JScrollPane(descArea);
        descScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        noteLabel      = new JLabel("List each component, category, & project this bug applies to, one per line:");
        projectLabel   = new JLabel("Projects:");
        projectArea    = new JTextArea();
        projectScroller = new JScrollPane(projectArea);
        projectScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        componentLabel = new JLabel("Components:");
        componentArea  = new JTextArea();
        componentScroller = new JScrollPane(componentArea);
        componentScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        categoryLabel  = new JLabel("Categories:");
        categoryArea   = new JTextArea();
        categoryScroller = new JScrollPane(categoryArea);
        categoryScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        addBugBut      = new AddBugBut("Add Bug", this);
        cancelBut      = new CancelBut("Cancel", this);

        buttonHandler bh = new buttonHandler();
        addBugBut.addActionListener(bh);
        cancelBut.addActionListener(bh);

        buildLayout();
    }

    void AddBug() {
        String newOwnerName, newTitle, newDesc;
        newOwnerName = ownerField.getText();
        newTitle = titleField.getText();
        newDesc = descArea.getText();
        if (newTitle.length() == 0) {
            JOptionPane.showMessageDialog(this,
                                          "Must have the description field completed",
                                          "Add Bug Failure",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }

        boolean addedBug = dao.addBug(newTitle, newOwnerName, newDesc);
        if (!addedBug) {
            JOptionPane.showMessageDialog(this,
                                          "Failed to write new project to the datbase",
                                          "Database write failure",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        mdtr.AddBug();
        this.dispose();
    }

    void Cancel() {
        this.dispose();
    }

    void buildLayout() {

        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setOpaque(true);
        //mainPanel.setPreferredSize(new Dimension(750,200));
        //mainScroller = new JScrollPane(mainPanel);
        GridBagConstraints c = new GridBagConstraints();
        
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        mainPanel.add(ownerLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 1;
        mainPanel.add(ownerField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        mainPanel.add(titleLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        mainPanel.add(titleField, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 3;
        mainPanel.add(descLabel, c);

        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 3;
        c.ipady = 100;
        mainPanel.add(descScroller, c);

        c.gridx = 0;
        c.gridy = 6;
        c.ipady = 0;
        mainPanel.add(noteLabel, c);

        c.fill = GridBagConstraints.NONE;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 1;
        mainPanel.add(projectLabel, c);

        c.gridx = 1;
        c.gridy = 7;
        mainPanel.add(componentLabel, c);

        c.gridx = 2;
        c.gridy = 7;
        mainPanel.add(categoryLabel, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 8;
        c.ipady = 150;
        c.weightx = 0.25;
        c.weighty = 0.5;
        mainPanel.add(projectScroller, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 8;
        c.ipady = 150;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainPanel.add(componentScroller, c);

        c.fill = GridBagConstraints.BOTH;
        c.gridx = 2;
        c.gridy = 8;
        c.ipady = 150;
        c.weightx = 0.5;
        c.weighty = 0.5;
        mainPanel.add(categoryScroller, c);

        buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(addBugBut);
        buttonPanel.add(cancelBut);

        this.add(mainPanel, BorderLayout.CENTER);
        this.add(buttonPanel, BorderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
    }
}
