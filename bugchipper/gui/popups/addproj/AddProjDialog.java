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
    JTextField projNameField;
    JFileChooser projFileChooser;
    JLabel filePathLabel, projNameLabel, ownerNameLabel, componentsLabel, categoriesLabel;
    JPanel projFilePanel, projEntriesPanel, projSubmitPanel;
    AddProjBut addProjBut;
    CancelBut cancelBut;
    FindProjBut findBut;
    ApplyBut applyBut;

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
        projNameField = new JTextField(30);
        findBut = new FindProjBut("Find",this);
        applyBut = new ApplyBut("Apply", this);

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
        projFilePanel.add(projNameField);
        projFilePanel.add(findBut);
        projFilePanel.add(applyBut);

        projEntriesPanel = new JPanel(new GridBagLayout());
        projEntriesPanel.setOpaque(true);

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
