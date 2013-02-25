package bugchipper.gui.popups.addproj;

import javax.swing.*;
import javax.swing.filechooser.*;
import java.awt.*;
import java.io.*;
import java.nio.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import bugchipper.*;
import bugchipper.database.*;
import bugchipper.database.objects.*;
import bugchipper.gui.eventhandlers.*;

/**
 * Display the AddProj Dialog box
 */
public class AddProjDialog extends JDialog {
    Mediator mdtr;
    DAO dao;
    File projFile;
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

    final static Charset ENCODING = StandardCharsets.UTF_8;

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
        if ((projNameField.getText().length() == 0) || (ownerNameField.getText().length() == 0)) {
            JOptionPane.showMessageDialog(this,
                                          "Must have the project name and owner name fields completed",
                                          "Add Project Failure",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        
        // Create a new project object
        ProjectObj proj = new ProjectObj(projNameField.getText(), ownerNameField.getText());
        
        // Go through the components text area line by line, create new component objects, and add them to the project
        String[] compLines = componentsArea.getText().split("\\n");
        for (String compLine : compLines) {
            compLine = compLine.replaceAll("\\s","");
            ComponentObj comp = new ComponentObj(compLine);
            proj.addComp(comp);
        }
        // Go through the categories text area line by line, create new category objects, and add them to the project
        String[] catLines = categoriesArea.getText().split("\\n");
        for (String catLine : catLines) {
            catLine = catLine.replaceAll("\\s","");
            String catKey  = catLine.replaceAll("^(.*)=(.*)$","$1");
            String catVal  = catLine.replaceAll("^(.*)=(.*)$","$2");
            CategoryObj cat = new CategoryObj(catKey, catVal);
            proj.addCat(cat);
        }

        try {
            dao.con.store(proj);
            mdtr.log.addData("Adding new project to the database");
            mdtr.log.addData("Project name: "+proj.getName());
            mdtr.log.addData("Owner name  : "+proj.getOwner());
        } catch (Exception e) {
            mdtr.log.addData("Caught exception when adding new project: "+e);
            JOptionPane.showMessageDialog(this,
                                          "Failed to write new project to the datbase",
                                          "Database write failure",
                                          JOptionPane.ERROR_MESSAGE);
            return;
        }
        this.dispose();
    }

    void Cancel() {
        this.dispose();
    }

    /**
     * Apply the contents of the input project file into the fields in the add project dialog window.
     */
    void Apply() {
        Path path = Paths.get(projPathField.getText());
        try (Scanner scanner = new Scanner(path, ENCODING.name())) {
                String line;
                boolean isProject, isOwner, isComponents, isCategories;
                isProject = false;
                isOwner = false;
                isComponents = false;
                isCategories = false;

                mdtr.log.addData("Read in project file "+projPathField.getText());
                while (scanner.hasNextLine()) {
                    line = scanner.nextLine();
                    line = line.replaceAll("\\s","");
                    if (line.matches("\\[project\\]")) {
                        isProject = true;
                        isOwner = false;
                        isComponents = false;
                        isCategories = false;
                    } else if (line.matches("\\[owner\\]")) {
                        isProject = false;
                        isOwner = true;
                        isComponents = false;
                        isCategories = false;
                    } else if (line.matches("\\[components\\]")) {
                        isProject = false;
                        isOwner = false;
                        isComponents = true;
                        isCategories = false;
                    } else if (line.matches("\\[categories\\]")) {
                        isProject = false;
                        isOwner = false;
                        isComponents = false;
                        isCategories = true;
                    } else if (line.length() > 0) {
                        if (isProject) {
                            if (line.matches("^name=(.*)")) {
                                line = line.replaceAll("^name=(.*)", "$1");
                                mdtr.log.addData("Project Name: "+line);
                                projNameField.setText(line);
                            }
                        } else if (isOwner) {
                            if (line.matches("^name=(.*)")) {
                                line = line.replaceAll("^name=(.*)", "$1");
                                mdtr.log.addData("Owner Name: "+line);
                                ownerNameField.setText(line);
                            }
                        } else if (isComponents) {
                            line = line.replaceAll("^(.*)$", "$1\n");
                            mdtr.log.addData("Add Component: "+line);
                            componentsArea.append(line);
                        } else if (isCategories) {
                            line = line.replaceAll("^(.*)$", "$1\n");
                            mdtr.log.addData("Add Category: "+line);
                            categoriesArea.append(line);
                        }
                    }
                }
            } catch (Exception e) {
            mdtr.log.addData("Failed to read file "+projPathField.getText()+": "+e);
        }
    }

    void Find() {
        projFileChooser = new JFileChooser();
        int returnVal = projFileChooser.showOpenDialog(AddProjDialog.this);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            projFile = projFileChooser.getSelectedFile();
            projPathField.setText(projFile.getAbsolutePath());
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
