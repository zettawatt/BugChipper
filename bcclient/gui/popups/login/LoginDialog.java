package bcclient.gui.popups.login;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import bcclient.*;
import bcclient.database.*;
import bcclient.gui.eventhandlers.*;

/**
 * Display the a MySQL Login Dialog box
 */
public class LoginDialog extends JDialog {
    Mediator mdtr;
    DAO dao;
    JPasswordField passwordField;
    JTextField usernameField;
    JLabel enterUser, enterPass;
    JPanel contentPane, buttonPane, textPane;

    public LoginDialog(Mediator inp_mdtr, DAO inp_dao) {
        mdtr = inp_mdtr;
        dao = inp_dao;
        this.setLayout( new BorderLayout());
        this.setTitle("Login to Database");
        this.setSize(250,150);
        this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);

        UIManager.put("Button.defaultButtonFollowsFocus", Boolean.TRUE);

        buttonHandler bh = new buttonHandler();
        LoginDialogLoginBut loginBut = new LoginDialogLoginBut("Login",this);
        loginBut.addActionListener(bh);

        usernameField = new JTextField(10);
        passwordField = new JPasswordField(10);
        enterUser = new JLabel("Enter username:");
        enterPass = new JLabel("Enter password:");

        contentPane = new JPanel(new FlowLayout());
        contentPane.setOpaque(true);

        textPane = new JPanel(new GridLayout(2,2));
        textPane.setOpaque(true);

        buttonPane = new JPanel(new FlowLayout());
        buttonPane.setOpaque(true);

        textPane.add(enterUser);
        textPane.add(usernameField);
        textPane.add(enterPass);
        textPane.add(passwordField);
        buttonPane.add(loginBut);

        contentPane.add(textPane);
        contentPane.add(buttonPane);
        
        this.setContentPane(contentPane);
        this.pack();
        this.setVisible(true);
    }

    public void Login() {
        String user = usernameField.getText();
        char[] passArray = passwordField.getPassword();
        String pass = new String(passArray);
        if (dao.dbLogin(user, pass)) {
            mdtr.Login();
            // As a security precaution, clear the password field after use
            Arrays.fill(passArray,'0');
            pass = "";
            this.dispose();
        } else {
            JOptionPane.showMessageDialog(this,
                                          "Incorrect username or password. Try again.",
                                          "Login Failure",
                                          JOptionPane.ERROR_MESSAGE);
        }
    }
}
