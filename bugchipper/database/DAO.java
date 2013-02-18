package bugchipper.database;

import java.sql.*;
import bugchipper.*;

public class DAO {
    Mediator mdtr;
    //dbConnect con;
    String dbURL, dbName;

    public DAO (String inp_url, String inp_dbname, Mediator inp_mdtr) {
        dbURL = inp_url;
        dbName = inp_dbname;
        mdtr = inp_mdtr;
        mdtr.registerDAO(this);
        //con = new dbConnect(dbURL, dbName);
    }

    public boolean dbLogin (String user, String pass) {
        return true;
    }

    public void dbLogout () {
        
    }
}

