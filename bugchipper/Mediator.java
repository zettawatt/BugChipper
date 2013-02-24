package bugchipper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.menubar.*;
import bugchipper.gui.toolbar.*;
import bugchipper.gui.popups.*;
import bugchipper.gui.popups.addproj.*;
import bugchipper.gui.popups.login.*;
import bugchipper.gui.*;
import bugchipper.database.*;

public class Mediator {

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

    // Database
    private DAO dao;
    private LoginStat ls;
    private LoginDialogLoginBut loginbut;

    // Log
    public BugChipLog log;

    // Constructor
    public Mediator() {
        log = new BugChipLog(this);
    }
    
    // Register menu items and toolbar buttons
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

    // Register database object
    public void registerDAO (DAO inp_dao) {dao = inp_dao;}
    public void registerLoginStat (LoginStat inp_ls) {ls = inp_ls;}

    // Register log
    public void registerBugChipLog (BugChipLog inp_log) {log = inp_log;}

    public void AddProj() {
        new AddProjDialog(this, dao);
    }

    public void AddBug() {
        log.addData("Added Bug!");
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
    }

    public void RefreshAll() {
        log.addData("Refreshing All Tabs!");
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
}
