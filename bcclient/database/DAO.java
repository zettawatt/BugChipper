package bcclient.database;

import bcclient.*;
import bcclient.database.objects.*;
import bcclient.database.predicates.*;
import java.util.*;
import com.db4o.*;
import com.db4o.query.*;
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

        // Update objects hierarchically. This has a performance penalty, but the
        // graph of objects is very shallow, so it doesn't really matter that much
        configuration.common().objectClass(ProjectObj.class).cascadeOnUpdate(true);
        configuration.common().objectClass(ComponentObj.class).cascadeOnUpdate(true);
        configuration.common().objectClass(CategoryObj.class).cascadeOnUpdate(true);
        configuration.common().objectClass(BugContainer.class).cascadeOnUpdate(true);
        
        //configuration.common().exceptionsOnNotStorable(false);
        //configuration.common().callConstructors(true);
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

    public boolean addProj (String inp_projname, String inp_ownername, Vector<String> inp_comps, Vector<String> inp_cats) {
        String projName = inp_projname;
        String ownerName = inp_ownername;
        Vector<String> compLines = inp_comps;
        Vector<String> catLines = inp_cats;

                // Create a new project object
        ProjectObj proj = new ProjectObj(projName, ownerName);
        
        // Go through the components text area line by line, create new component objects, and add them to the project
        // FIXME: need to prevent the creation of duplicate component objects by searching for existing ones
        for (String compLine : compLines) {
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

    public boolean addBug (String inp_title, String inp_owner, String inp_desc,
                           Vector<String> inp_projbugs, Vector<String> inp_combugs, Vector<String> inp_catbugs) {
        String title = inp_title;
        String owner = inp_owner;
        String desc  = inp_desc;
        Vector<String> projBugs = inp_projbugs;
        Vector<String> comBugs = inp_combugs;
        Vector<String> catBugs = inp_catbugs;
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
        } catch (Exception e) {
            mdtr.log.addData("Caught exception when adding new bug: "+e);
            return false;
        }

        // Add bug to all relevant components, categories, and projects
        if (comBugs.size() != 0) {
            mdtr.log.addData("Adding component level bugs:");
            List <ComponentObj> components = con.query(new ComAddBugsPredicate(comBugs));
        
            for (ComponentObj component : components) {
                mdtr.log.addData("Adding component bug: "+component.getName());
                component.addBug(bug);
                try {
                    con.store(component);
                    con.commit();
                    mdtr.log.addData("Adding bug to the component "+component.getName());
                } catch (Exception e) {
                    mdtr.log.addData("Caught exception when adding new bug to component "+component.getName()+": "+e);
                    return false;
                }
            }
        }
        
//        if (catBugs.size() != 0) {
//            List <CategoryObj> categorys = con.query(new Predicate<CategoryObj>() {
//                    public boolean match(CategoryObj cat) {
//                        for (String catBug : catBugs) {
//                            if (cat.getName() == catBug) {
//                                return true;
//                            }
//                        }
//                    }
//                });
//        
//            for (CategoryObj category : categorys) {
//                category.addBug(bug);
//            }
//        }
        
        if (projBugs.size() > 0) {
            mdtr.log.addData("Adding project level bugs:");
            List<ProjectObj> projects = con.query(new ProjAddBugsPredicate(projBugs));

            for (ProjectObj project : projects) {
                mdtr.log.addData("Adding project bug: "+project.getName());
                project.addBug(bug);
                try {
                    con.store(project);
                    con.commit();
                    mdtr.log.addData("Adding bug to the project "+project.getName());
                } catch (Exception e) {
                    mdtr.log.addData("Caught exception when adding new bug to project "+project.getName()+": "+e);
                    return false;
                }
            }
        }

        return true;
        
    }
}

