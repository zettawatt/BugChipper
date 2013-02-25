package bugchipper.database;

import bugchipper.*;
import com.db4o.*;
import com.db4o.cs.*;
import com.db4o.cs.ssl.*;
import com.db4o.cs.config.*;

public class DAO {
    Mediator mdtr;
    public ObjectContainer con;
    String dbURL;
    int dbPort;

    public DAO (String inp_url, int inp_dbport, Mediator inp_mdtr) {
        dbURL = inp_url;
        dbPort = inp_dbport;
        mdtr = inp_mdtr;
        mdtr.registerDAO(this);
    }

    public boolean dbLogin (String user, String pass) {
        mdtr.log.addData("Attempting to connect to database...");
        // Add SSL encryption for network traffic
        // FIXME: Need to add SSL certificates to java for this to work
        ClientConfiguration configuration = Db4oClientServer.newClientConfiguration();
        //configuration.common().add(new SSLSupport());
        con = null;
        try {
            con = Db4oClientServer.openClient(configuration, dbURL, dbPort, user, pass);
            mdtr.log.addData("Successful login with username "+user);
        } catch (Exception e) {
            mdtr.log.addData("Login failed with username "+user);
            mdtr.log.addData("Login failure exception: "+e);
            return false;
        }
        return true;
    }

    public void dbLogout () {
        try {
            con.close();
            mdtr.log.addData("Successfully logged out of database");
        } catch (Exception e) {
            mdtr.log.addData("Logout failed with exception: "+e);
        }
    }
}

