package bugchipper.gui;

import javax.swing.*;
import java.awt.*;
import bugchipper.*;

public class LoginStat extends JLabel {
    Mediator mdtr;
    ImageIcon loggedInIcon, notloggedInIcon;

    public LoginStat (Mediator inp_mdtr) {
        loggedInIcon = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/loginstat-logged_in.png"));
        notloggedInIcon = new ImageIcon(BugChipperApp.class.getResource("gui/icons/16x16/loginstat-not_logged_in.png"));
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
}
