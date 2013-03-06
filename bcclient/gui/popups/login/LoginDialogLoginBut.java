package bcclient.gui.popups.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bcclient.gui.eventhandlers.*;
import bcclient.gui.*;
import bcclient.database.*;
import bcclient.*;

public class LoginDialogLoginBut extends JButton implements CommandInterface {
    LoginDialog mdtr;

    public void processEvent() {
        mdtr.Login();
    }

    public LoginDialogLoginBut (String name, LoginDialog inp_mdtr) {
        super(name);
        mdtr = inp_mdtr;
    }
}

