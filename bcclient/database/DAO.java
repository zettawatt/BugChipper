package bugchipper.database;

import bugchipper.*;
import bugchipper.database.objects.*;
import java.util.*;
import com.db4o.*;
import com.db4o.cs.*;
import com.db4o.cs.ssl.*;
import com.db4o.cs.config.*;

public class DAO {
    Mediator mdtr;
    public ObjectContainer con;
    String dbURL;
    int dbPort, id;

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

    public boolean addProj (String inp_projname, String inp_ownername, String[] inp_comps, String[] inp_cats) {
        String projName = inp_projname;
        String ownerName = inp_ownername;
        String[] compLines = inp_comps;
        String[] catLines = inp_cats;

                // Create a new project object
        ProjectObj proj = new ProjectObj(projName, ownerName);
        
        // Go through the components text area line by line, create new component objects, and add them to the project
        for (String compLine : compLines) {
            compLine = compLine.replaceAll("\\s","");
            ComponentObj comp = new ComponentObj(compLine);
            proj.addComp(comp);
        }
        // Go through the categories text area line by line, create new category objects, and add them to the project
        for (String catLine : catLines) {
            catLine = catLine.replaceAll("\\s","");
            String catKey  = catLine.replaceAll("^(.*)=(.*)$","$1");
            String catVal  = catLine.replaceAll("^(.*)=(.*)$","$2");
            CategoryObj cat = new CategoryObj(catKey, catVal);
            proj.addCat(cat);
        }

        try {
            con.store(proj);
            con.commit();
            mdtr.log.addData("Adding new project to the database");
            mdtr.log.addData("Project name: "+proj.getName());
            mdtr.log.addData("Mod Time    : "+proj.getModTime());
            mdtr.log.addData("Owner name  : "+proj.getOwner());
            return true;
        } catch (Exception e) {
            mdtr.log.addData("Caught exception when adding new project: "+e);
            return false;
        }
    }

    public boolean addBug (String inp_title, String inp_owner, String inp_desc) {
        String title = inp_title;
        String owner = inp_owner;
        String desc  = inp_desc;
        BugIDCounter bugID = null;

        // Create unique ID number from the last count
        List<BugIDCounter> bugIDs = con.query(BugIDCounter.class);
        if (bugIDs.size() == 0) {
            bugID = new BugIDCounter();
            try {
                con.store(bugID);
                con.commit();
                mdtr.log.addData("new bugID object :"+Integer.toString(bugID.getCurrentIDCount()));
            } catch (Exception e) {
                mdtr.log.addData("Caught exception when adding new bug: "+e);
                return false;
            }
        } else {
            for (BugIDCounter inp_bugID : bugIDs) {
                bugID = inp_bugID;
            }
        }
        bugID.incCurrentIDCount();
        try {
            con.store(bugID);
            con.commit();
        } catch (Exception e) {
            mdtr.log.addData("Caught exception when adding new bug: "+e);
            return false;
        }
        id = bugID.getCurrentIDCount();

        // Create a new bug object
        BugObj bug = new BugObj(title, desc, owner, id);

        try {
            con.store(bug);
            con.commit();
            mdtr.log.addData("Adding new bug to the database");
            mdtr.log.addData("Bug Title       : "+bug.getTitle());
            mdtr.log.addData("Bug Description : "+bug.getDesc());
            mdtr.log.addData("Owner name      : "+bug.getOwner());
            mdtr.log.addData("Bug ID number   : "+Integer.toString(bug.getID()));
            return true;
        } catch (Exception e) {
            mdtr.log.addData("Caught exception when adding new bug: "+e);
            return false;
        }
    }
}

