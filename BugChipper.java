import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import gui.*;

public class BugChipper {

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
                public void run() {
                    BugChipperApp app = new BugChipperApp();
                }
            });
    }
}
