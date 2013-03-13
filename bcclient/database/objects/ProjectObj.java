package bcclient.database.objects;

import java.util.*;
import java.text.*;

public class ProjectObj {
    String projName, ownerName, time;
    ComponentObj comp;
    CategoryObj cat;
    BugObj bug;
    BugContainer bugCon;
    Date date;
    int numBugs = 0;
    SimpleDateFormat ft;
    Vector<ComponentObj> components;
    Vector<CategoryObj> categories;
    Vector<BugContainer> bugs;

    public ProjectObj () {}
    
    public ProjectObj (String inp_projname, String inp_ownername) {
        projName = inp_projname;
        ownerName = inp_ownername;
        date = new Date();
        ft = new SimpleDateFormat("MM/dd/yy");
        time = ft.format(date);
        components = new Vector<ComponentObj>();
        categories = new Vector<CategoryObj>();
        bugs = new Vector<BugContainer>();
    }

    public void addComp (ComponentObj inp_comp) {
        comp = inp_comp;
        components.add(comp);
    }

    public boolean hasComp (String inp_comp) {
        String testComp = inp_comp;
        //System.out.println(testComp);
        for (ComponentObj comp : components) {
            if (comp.getName().equals(testComp)) {
                return true;
            }
        }
        return false;
    }

    public void addCat (CategoryObj inp_cat) {
        cat = inp_cat;
        categories.add(cat);
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugCon = new BugContainer(bug);
        bugs.add(bugCon);
    }

    public boolean hasBug (BugObj inp_bug) {
        BugObj testBug = inp_bug;
        for (BugContainer bugContainer : bugs) {
            if(bugContainer.bug.getID() == testBug.getID()) {
                return true;
            }
        }
        return false;
    }

    public String getName () {
        return projName;
    }

    public int getNumBugs () {
        numBugs = 0;
        for (BugContainer inp_bugcon : bugs) {
            bugCon = inp_bugcon;
            if(!bugCon.isClosed()) {
                numBugs++;
            }
        }
        return numBugs;
    }

    public String getModTime () {
        return time;
    }

    public String getOwner () {
        return ownerName;
    }

    public Vector<BugContainer> getBugs() {
        return bugs;
    }
}
