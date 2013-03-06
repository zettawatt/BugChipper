package bcclient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class BugChipper {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BugChipperApp app = new BugChipperApp();
                }
            });
    }
}
