package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.menubar.*;
import gui.toolbar.*;
import gui.popups.*;

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
    }

    public void Admin() {
        System.out.println("Administer Database!");
    }
}
