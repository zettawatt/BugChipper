package bugchipper.database;

import java.sql.*;
import bugchipper.*;

public class DAO {
    Mediator mdtr;

    public DAO (Mediator inp_mdtr) {
        mdtr = inp_mdtr;
        mdtr.registerDAO(this);
    }

    public boolean dbLogin (String user, String pswd) {
        System.out.println("User: " + user + " Password: " + pswd);
        return true;
    }
}

