package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.menubar.*;

public class Mediator {
    
    private AddProjMenuItem miAddProj;
    private AddBugMenuItem miAddBug;
    private OpenProjMenuItem miOpenProj;
    private QuitMenuItem miQuit;
    private PrefsMenuItem miPrefs;
    private AboutMenuItem miAbout;

    public void registerAddProjMenuItem (AddProjMenuItem inp_addprojmi) {miAddProj = inp_addprojmi;}
    public void registerAddBugMenuItem (AddBugMenuItem inp_addbugmi) {miAddBug = inp_addbugmi;}
    public void registerOpenProjMenuItem (OpenProjMenuItem inp_openprojmi) {miOpenProj = inp_openprojmi;}
    public void registerQuitMenuItem (QuitMenuItem inp_quitmi) {miQuit = inp_quitmi;}
    public void registerPrefsMenuItem (PrefsMenuItem inp_prefsmi) {miPrefs = inp_prefsmi;}
    public void registerAboutMenuItem (AboutMenuItem inp_aboutmi) {miAbout = inp_aboutmi;}

    public void AddProj() {

    }

    public void AddBug() {

    }

    public void OpenProj() {

    }

    public void Exit() {
        System.exit(0);
    }

    public void About() {
        AboutDialog about = new AboutDialog();
    }

    public void Prefs() {

    }
}
