package bugchipper.gui;

import javax.swing.*;
import java.awt.*;
import bugchipper.*;

public class LoginStat extends JLabel {
    Mediator mdtr;
    ImageIcon loggedInIcon, notloggedInIcon;

    public LoginStat (Mediator inp_mdtr) {
        loggedInIcon = createImageIcon("./icons/16x16/loginstat-logged_in.png", "Logged In");
        notloggedInIcon = createImageIcon("./icons/16x16/loginstat-not_logged_in.png", "Not Logged In");
        mdtr = inp_mdtr;
        mdtr.registerLoginStat(this);
        this.setText("Not Logged In. Read only access.");
        this.setIcon(notloggedInIcon);
    }

    public void updateStat (boolean loggedIn) {
        if (loggedIn) {
            this.setText("Logged In. Full access.");
            this.setIcon(loggedInIcon);
        } else {
            this.setText("Not Logged In. Read only access.");
            this.setIcon(notloggedInIcon);
        }
    }

    protected static ImageIcon createImageIcon(String path,
                                               String description) {
        java.net.URL imgURL = LoginStat.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL, description);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
