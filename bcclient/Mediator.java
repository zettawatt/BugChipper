package bcclient;

import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import bcclient.gui.eventhandlers.*;
import bcclient.gui.menubar.*;
import bcclient.gui.toolbar.*;
import bcclient.gui.tables.*;
import bcclient.gui.tabs.projecttab.*;
import bcclient.gui.popups.*;
import bcclient.gui.popups.addbug.*;
import bcclient.gui.popups.addproj.*;
import bcclient.gui.popups.login.*;
import bcclient.gui.*;
import bcclient.database.*;
import bcclient.database.objects.*;
import com.db4o.*;

public class Mediator {

    // Main GUI
    private MainGUI maingui;
    public MainTable table;

    // Menu Items
    private AddProjMenuItem miAddProj;
    private AddBugMenuItem miAddBug;
    private OpenProjMenuItem miOpenProj;
    private QuitMenuItem miQuit;
    private PrefsMenuItem miPrefs;
    private ViewLogMenuItem miViewLog;
    private RefreshMenuItem miRefresh;
    private RefreshAllMenuItem miRefreshAll;
    private LoginMenuItem miLogin;
    private LogoutMenuItem miLogout;
    private AdminMenuItem miAdmin;
    private AboutMenuItem miAbout;

    // Toolbar Buttons
    private AddProjToolBut tbAddProj;
    private AddBugToolBut tbAddBug;
    private RefreshToolBut tbRefresh;
    private RefreshAllToolBut tbRefreshAll;
    private ViewLogToolBut tbViewLog;
    private AdminDBToolBut tbAdmin;

    // Database
    private DAO dao;
    private LoginStat ls;
    private LoginDialogLoginBut loginbut;

    // Log
    public BugChipLog log;

    // Other variables
    public String lastProjFile = null;

    // Constructor
    public Mediator() {
        log = new BugChipLog(this);
    }
    
    // Register GUI items
    public void registerMainGUI(MainGUI inp_maingui) {maingui = inp_maingui;}
    public void registerAddProjMenuItem (AddProjMenuItem inp_addprojmi) {miAddProj = inp_addprojmi;}
    public void registerAddBugMenuItem (AddBugMenuItem inp_addbugmi) {miAddBug = inp_addbugmi;}
    public void registerOpenProjMenuItem (OpenProjMenuItem inp_openprojmi) {miOpenProj = inp_openprojmi;}
    public void registerQuitMenuItem (QuitMenuItem inp_quitmi) {miQuit = inp_quitmi;}
    public void registerPrefsMenuItem (PrefsMenuItem inp_prefsmi) {miPrefs = inp_prefsmi;}
    public void registerRefreshMenuItem (RefreshMenuItem inp_refreshmi) {miRefresh = inp_refreshmi;}
    public void registerRefreshAllMenuItem (RefreshAllMenuItem inp_refreshallmi) {miRefreshAll = inp_refreshallmi;}
    public void registerViewLogMenuItem (ViewLogMenuItem inp_viewlogmi) {miViewLog = inp_viewlogmi;}
    public void registerLoginMenuItem (LoginMenuItem inp_loginmi) {miLogin = inp_loginmi;}
    public void registerLogoutMenuItem (LogoutMenuItem inp_logoutmi) {miLogout = inp_logoutmi;}
    public void registerAdminMenuItem (AdminMenuItem inp_adminmi) {miAdmin = inp_adminmi;}
    public void registerAboutMenuItem (AboutMenuItem inp_aboutmi) {miAbout = inp_aboutmi;}

    public void registerAddProjToolBut (AddProjToolBut inp_addprojtb) {tbAddProj = inp_addprojtb;}
    public void registerAddBugToolBut (AddBugToolBut inp_addbugtb) {tbAddBug = inp_addbugtb;}
    public void registerRefreshToolBut (RefreshToolBut inp_refreshtb) {tbRefresh = inp_refreshtb;}
    public void registerRefreshAllToolBut (RefreshAllToolBut inp_refreshalltb) {tbRefreshAll = inp_refreshalltb;}
    public void registerAdminDBToolBut (AdminDBToolBut inp_admintb) {tbAdmin = inp_admintb;}
    public void registerViewLogToolBut (ViewLogToolBut inp_viewlogtb) {tbViewLog = inp_viewlogtb;}

    // Register database object
    public void registerDAO (DAO inp_dao) {dao = inp_dao;}
    public void registerLoginStat (LoginStat inp_ls) {ls = inp_ls;}

    // Register log
    public void registerBugChipLog (BugChipLog inp_log) {log = inp_log;}

    public void AddProjPrompt() {
        new AddProjDialog(this, dao);
    }

    public void AddProj() {
        table = rebuildAllProjTable();
        maingui.scrollPane.setViewportView(table);
        maingui.scrollPane.revalidate();
    }

    public void AddBugPrompt() {
        new AddBugDialog(this, dao);
    }

    public void AddBug() {
        table = rebuildAllProjTable();
        maingui.scrollPane.setViewportView(table);
        maingui.scrollPane.revalidate();
    }

    public void OpenProj() {
        log.addData("Opened Project!");
    }

    public void Exit() {
        System.exit(0);
    }

    public void About() {
        new AboutDialog();
    }

    public void Prefs() {
        log.addData("Editting Preferences!");
    }

    public void Refresh() {
        log.addData("Refreshing Current Tab!");
        table = rebuildAllProjTable();
        maingui.scrollPane.setViewportView(table);
        maingui.scrollPane.revalidate();
    }

    public void RefreshAll() {
        log.addData("Refreshing All Tabs!");
        table = rebuildAllProjTable();
        maingui.scrollPane.setViewportView(table);
        maingui.scrollPane.revalidate();
    }

    public void openTab(String inp_colname, String inp_value) {
        String columnName = inp_colname;
        String cellValue  = inp_value;
        if (columnName == "Project") {
            ProjectTabPanel projTab = new ProjectTabPanel(cellValue, this, dao);
            maingui.mainTab.add(cellValue, projTab);
            int i = maingui.mainTab.getTabCount();
            maingui.initTab(i-1);
        } else if (columnName == "Component Name") {
            //FIXME: create a component tab
        } else if (columnName == "ID #") {
            //FIXME: create a bug container tab
        }
    }

    public void ViewLog() {
        log.addData("View Log!");
        LogDialog viewLog = new LogDialog(log);
    }

    public void LoginPrompt() {
        new LoginDialog(this, dao);
    }

    public void Login() {
        log.addData("Logging into Database!");
        table = rebuildAllProjTable();
        maingui.scrollPane.setViewportView(table);
        maingui.scrollPane.revalidate();
        ls.updateStat(true);
        miLogin.setEnabled(false);
        miLogout.setEnabled(true);
        miAdmin.setEnabled(true);
    }

    public void Logout() {
        log.addData("Logging out of Database!");
        dao.dbLogout();
        ls.updateStat(false);
        miLogin.setEnabled(true);
        miLogout.setEnabled(false);
        miAdmin.setEnabled(false);
    }

    public void Admin() {
        log.addData("Administer Database!");
    }

    public void setLastProjFile (String inp_projfile) {
        lastProjFile = inp_projfile;
    }

    public MainTable rebuildAllProjTable () {
        Vector<String> columnNames = new Vector<String>();
        columnNames.add("Project");
        columnNames.add("Open Issues");
        columnNames.add("Date Modified");
        columnNames.add("Project Owner");
        Vector< Vector<String>> data = new Vector<Vector<String>>();
        java.util.List<ProjectObj> projects = dao.con.query(ProjectObj.class);
        for (ProjectObj proj : projects) {
            Vector<String> d = new Vector<String>();
            d.add(proj.getName());
            d.add(Integer.toString(proj.getNumBugs()));
            d.add(proj.getModTime());
            d.add(proj.getOwner());
            data.add(d);
        }
        MainTableModel model = new MainTableModel(data, columnNames);
        MainTable newTable = new MainTable(columnNames, data, this);
        mouseHandler mh = new mouseHandler();
        newTable.addMouseListener(mh);
        newTable.setModel(model);
        return newTable;
    }

    public Vector<String> getLines(JTextArea inp_area) {
        JTextArea area;
        String[] lines;
        area = inp_area;
        Vector<String> lineVector;

        lineVector = new Vector<String>();
        
        lines = area.getText().split("\\n");
        
        for (String line : lines) {
            line = line.replace("\n","").replace("\r","");
            line = line.trim();
            if (!line.equals("")) {
                lineVector.add(line);
            }
        }

        return lineVector;
    }
}
