package bcclient.gui.popups;

import javax.swing.*;
import java.awt.*;

/**
 * Display the About Dialog from the Help Menu
 */
public class AboutDialog extends JDialog {

    private JLabel logo, prog, author, email, catchphrase;
    private ImageIcon icon;

    {
        icon = createImageIcon("../icons/bugchipper-logo-large.png", "Bugchipper logo");

        this.setLayout( new FlowLayout());
        this.setTitle("About BugChipper");
        this.setSize(250,400);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        logo = new JLabel(icon);
        prog = new JLabel("BugChipper 1.0");
        author = new JLabel("Written by Chuck McClish");
        email = new JLabel("charles.mcclish@microchip.com");
        catchphrase = new JLabel("Its like a woodchipper for bugs!");
        this.add(logo);
        this.add(prog);
        this.add(author);
        this.add(email);
        this.add(catchphrase);

        //this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = AboutDialog.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
