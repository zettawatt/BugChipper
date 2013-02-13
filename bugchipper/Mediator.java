package bugchipper;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.menubar.*;
import bugchipper.gui.toolbar.*;
import bugchipper.gui.popups.*;
import bugchipper.gui.*;
import bugchipper.database.*;

public class Mediator {

    // Menu Items
    private AddProjMenuItem miAddProj;
    private AddBugMenuItem miAddBug;
    private OpenProjMenuItem miOpenProj;
    private QuitMenuItem miQuit;
    private PrefsMenuItem miPrefs;
    private LoginMenuItem miLogin;
    private AdminMenuItem miAdmin;
    private AboutMenuItem miAbout;

    // Toolbar Buttons
    private AddProjToolBut tbAddProj;
    private AddBugToolBut tbAddBug;

    // Database
    private DAO dao;
    private LoginStat ls;
    
    // Register menu items and toolbar buttons
    public void registerAddProjMenuItem (AddProjMenuItem inp_addprojmi) {miAddProj = inp_addprojmi;}
    public void registerAddBugMenuItem (AddBugMenuItem inp_addbugmi) {miAddBug = inp_addbugmi;}
    public void registerOpenProjMenuItem (OpenProjMenuItem inp_openprojmi) {miOpenProj = inp_openprojmi;}
    public void registerQuitMenuItem (QuitMenuItem inp_quitmi) {miQuit = inp_quitmi;}
    public void registerPrefsMenuItem (PrefsMenuItem inp_prefsmi) {miPrefs = inp_prefsmi;}
    public void registerLoginMenuItem (LoginMenuItem inp_loginmi) {miLogin = inp_loginmi;}
    public void registerAdminMenuItem (AdminMenuItem inp_adminmi) {miAdmin = inp_adminmi;}
    public void registerAboutMenuItem (AboutMenuItem inp_aboutmi) {miAbout = inp_aboutmi;}

    public void registerAddProjToolBut (AddProjToolBut inp_addprojtb) {tbAddProj = inp_addprojtb;}
    public void registerAddBugToolBut (AddBugToolBut inp_addbugtb) {tbAddBug = inp_addbugtb;}

    // Register database object
    public void registerDAO (DAO inp_dao) {dao = inp_dao;}
    public void registerLoginStat (LoginStat inp_ls) {ls = inp_ls;}

    public void AddProj() {
        System.out.println("Added Project!");
    }

    public void AddBug() {
        System.out.println("Added Bug!");
    }

    public void OpenProj() {
        System.out.println("Opened Project!");
    }

    public void Exit() {
        System.exit(0);
    }

    public void About() {
        AboutDialog about = new AboutDialog();
    }

    public void Prefs() {
        System.out.println("Editting Preferences!");
    }

    public void Login() {
        System.out.println("Logging into Database!");
        //LoginDialog ld = new LoginDialog();
        ls.updateStat(dao.dbLogin("user", "pass"));
    }

    public void Admin() {
        System.out.println("Administer Database!");
    }
}
