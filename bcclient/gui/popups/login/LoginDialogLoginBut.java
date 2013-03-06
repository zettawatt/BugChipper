package bugchipper.gui.popups.login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import bugchipper.gui.eventhandlers.*;
import bugchipper.gui.*;
import bugchipper.database.*;
import bugchipper.*;

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

