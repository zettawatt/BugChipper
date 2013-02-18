package bugchipper.database;

import java.sql.*;
import bugchipper.*;

public class DBConnect {
    Mediator mdtr;
    String dbURL, dbName, user, pass, driver;
    Connection con;

    public DBConnect (String inp_url, String inp_dbname, Mediator inp_mdtr) {
        con = null;
        dbURL = inp_url;
        dbName = inp_dbname;
        mdtr = inp_mdtr;
        driver = "com.mysql.jdbc.Driver";
    }

    public boolean connect (String inp_user, String inp_pass) {
        user = inp_user;
        pass = inp_pass;

        try {
            Class.forName(driver);
            DriverManager.setLoginTimeout(10);
            con = DriverManager.getConnection(dbURL + dbName, user, pass);
            mdtr.log.addData("Attempting to connect to database...");
            mdtr.log.addData("Successful login with username "+user);
        } catch (Exception e) {
            mdtr.log.addData("Login failed with username "+user);
            mdtr.log.addData("Login failure exception: "+e);
            return false;
        }
        return true;
    }

    public void disconnect () {
        try {
            con.close();
            mdtr.log.addData("Successfully logged out of database");
        } catch (Exception e) {
            mdtr.log.addData("Logout failed with exception: "+e);
        }
    }
}
