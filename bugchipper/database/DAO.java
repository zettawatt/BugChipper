package bugchipper.database;

import java.sql.*;
import bugchipper.*;

public class DAO {
    Mediator mdtr;
    DBConnect con;
    String dbURL, dbName;

    public DAO (String inp_url, String inp_dbname, Mediator inp_mdtr) {
        dbURL = inp_url;
        dbName = inp_dbname;
        mdtr = inp_mdtr;
        mdtr.registerDAO(this);
        con = new DBConnect(dbURL, dbName, mdtr);
    }

    public boolean dbLogin (String user, String pass) {
        return con.connect(user,pass);
    }

    public void dbLogout () {
        con.disconnect();
    }
}

