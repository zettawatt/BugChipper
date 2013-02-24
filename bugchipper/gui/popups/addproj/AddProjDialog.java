package bugchipper.gui.popups.addproj;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.io.*;
import java.util.*;
import bugchipper.*;
import bugchipper.database.*;
import bugchipper.gui.eventhandlers.*;

/**
 * Display the AddProj Dialog box
 */
public class AddProjDialog extends JDialog {
    Mediator mdtr;
    DAO dao;
    JTextField projPathField, projNameField, ownerNameField;
    JTextArea componentsArea, categoriesArea;
    JFileChooser projFileChooser;
    JLabel filePathLabel, projNameLabel, ownerNameLabel, componentsLabel, categoriesLabel, noteLabel;
    JPanel projFilePanel, projEntriesPanel, projSubmitPanel;
    AddProjBut addProjBut;
    CancelBut cancelBut;
    FindProjBut findBut;
    ApplyBut applyBut;
    JScrollPane componentScroller, categoryScroller;

    public AddProjDialog(Mediator inp_mdtr, DAO inp_dao) {
        mdtr = inp_mdtr;
        dao = inp_dao;
        this.setLayout( new BorderLayout());
        this.setTitle("Add Project to Database");
        this.setPreferredSize(new Dimension(700,400));
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);

        filePathLabel = new JLabel("Project file path:");
        projPathField = new JTextField(30);
        findBut = new FindProjBut("Find",this);
        applyBut = new ApplyBut("Apply", this);

        projNameLabel = new JLabel("Name of Project:");
        projNameField = new JTextField(20);
        ownerNameLabel = new JLabel("Name of Project Owner:");
        ownerNameField = new JTextField(20);
        noteLabel = new JLabel("NOTE: place only ONE component and category per line");
        componentsLabel = new JLabel("Components:");
        componentsArea = new JTextArea();
        componentScroller = new JScrollPane(componentsArea);
        componentScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        categoriesLabel = new JLabel("Categories:");
        categoriesArea = new JTextArea();
        categoryScroller = new JScrollPane(categoriesArea);
        categoryScroller.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        addProjBut = new AddProjBut("Add Project",this);
        cancelBut = new CancelBut("Cancel",this);

        buttonHandler bh = new buttonHandler();
        addProjBut.addActionListener(bh);
        cancelBut.addActionListener(bh);
        findBut.addActionListener(bh);
        applyBut.addActionListener(bh);

        buildLayout();
    }

    void AddProj() {
        // add project to database
        this.dispose();
    }

    void Cancel() {
        this.dispose();
    }

    void Apply() {
        // Apply the contents of the project file to the fields
    }

    void Find() {
        projFileChooser = new JFileChooser();
        int returnVal = projFileChooser.showOpenDialog(AddProjDialog.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            File projFile = projFileChooser.getSelectedFile();
            projNameField.setText(projFile.getAbsolutePath());
        }
    }

    void buildLayout() {

        projFilePanel = new JPanel(new FlowLayout());
        projFilePanel.setOpaque(true);
        projFilePanel.add(filePathLabel);
        projFilePanel.add(projPathField);
        projFilePanel.add(findBut);
        projFilePanel.add(applyBut);

        projEntriesPanel = new JPanel(new GridBagLayout());
        //projEntriesPanel.setPreferredSize(new Dimension(600,400));
        projEntriesPanel.setOpaque(true);
        GridBagConstraints entriesCon = new GridBagConstraints();
        //entriesCon.fill = GridBagConstraints.HORIZONTAL;
        entriesCon.gridx = 0;
        entriesCon.gridy = 0;
        entriesCon.ipady = 0;
        projEntriesPanel.add(projNameLabel, entriesCon);
        entriesCon.fill = GridBagConstraints.HORIZONTAL;
        entriesCon.gridx = 2;
        entriesCon.gridy = 0;
        entriesCon.ipady = 0;
        entriesCon.gridwidth = 2;
        projEntriesPanel.add(projNameField, entriesCon);
        //entriesCon.fill = GridBagConstraints.HORIZONTAL;
        entriesCon.gridx = 0;
        entriesCon.gridy = 1;
        entriesCon.ipady = 0;
        projEntriesPanel.add(ownerNameLabel, entriesCon);
        entriesCon.fill = GridBagConstraints.HORIZONTAL;
        entriesCon.gridx = 2;
        entriesCon.gridy = 1;
        entriesCon.ipady = 0;
        entriesCon.gridwidth = 2;
        projEntriesPanel.add(ownerNameField, entriesCon);

        entriesCon.fill = GridBagConstraints.HORIZONTAL;
        entriesCon.gridx = 0;
        entriesCon.gridy = 2;
        entriesCon.ipady = 30;
        entriesCon.gridwidth = 6;
        projEntriesPanel.add(noteLabel, entriesCon);

        //entriesCon.fill = GridBagConstraints.HORIZONTAL;
        entriesCon.gridx = 0;
        entriesCon.gridy = 3;
        entriesCon.ipady = 0;
        projEntriesPanel.add(componentsLabel, entriesCon);
        //entriesCon.fill = GridBagConstraints.HORIZONTAL;
        entriesCon.gridx = 3;
        entriesCon.gridy = 3;
        entriesCon.ipady = 0;
        projEntriesPanel.add(categoriesLabel, entriesCon);
        entriesCon.fill = GridBagConstraints.BOTH;
        entriesCon.weightx = 0.5;
        entriesCon.weighty = 0.5;
        entriesCon.gridwidth = 3;
        entriesCon.gridx = 0;
        entriesCon.gridy = 4;
        projEntriesPanel.add(componentScroller, entriesCon);
        entriesCon.fill = GridBagConstraints.BOTH;
        entriesCon.weightx = 0.5;
        entriesCon.weighty = 0.5;
        entriesCon.gridwidth = 3;
        entriesCon.gridx = 3;
        entriesCon.gridy = 4;
        projEntriesPanel.add(categoryScroller, entriesCon);

        projSubmitPanel = new JPanel(new FlowLayout());
        projSubmitPanel.setOpaque(true);
        projSubmitPanel.add(addProjBut);
        projSubmitPanel.add(cancelBut);
        
        this.add(projFilePanel, BorderLayout.PAGE_START);
        this.add(projEntriesPanel, BorderLayout.CENTER);
        this.add(projSubmitPanel, BorderLayout.PAGE_END);

        this.pack();
        this.setVisible(true);
    }
}
