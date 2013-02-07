package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.eventhandlers.*;
import gui.menubar.*;

public class Mediator {
    
    private AboutMenuItem miAbout;
    private QuitMenuItem miQuit;
    private OpenProjMenuItem miOpenProj;

    public void registerQuitMenuItem (QuitMenuItem inp_quitmi) {
        miQuit = inp_quitmi;
    }

    public void registerOpenProjMenuItem (OpenProjMenuItem inp_openprojmi) {
        miOpenProj = inp_openprojmi;
    }

    public void registerAboutMenuItem (AboutMenuItem inp_aboutmi) {
        miAbout = inp_aboutmi;
    }

    public void Exit() {
        System.exit(0);
    }

    public void About() {
        AboutDialog about = new AboutDialog();
    }

    public void OpenProj() {

    }
}
