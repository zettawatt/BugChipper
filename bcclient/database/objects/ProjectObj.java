package bcclient.database.objects;

import java.util.*;
import java.text.*;

public class ProjectObj {
    String projName, ownerName, time;
    ComponentObj comp;
    CategoryObj cat;
    BugObj bug;
    Date date;
    int numBugs;
    SimpleDateFormat ft;
    Vector<ComponentObj> components;
    Vector<CategoryObj> categories;
    Vector<BugObj> bugs;
    
    public ProjectObj (String inp_projname, String inp_ownername) {
        projName = inp_projname;
        ownerName = inp_ownername;
        date = new Date();
        ft = new SimpleDateFormat("MM/dd/yy");
        time = ft.format(date);
        components = new Vector<ComponentObj>();
        categories = new Vector<CategoryObj>();
        bugs = new Vector<BugObj>();
        numBugs = bugs.size();
    }

    public void addComp (ComponentObj inp_comp) {
        comp = inp_comp;
        components.add(comp);
        numBugs += comp.getNumBugs();
    }

    public void addCat (CategoryObj inp_cat) {
        cat = inp_cat;
        categories.add(cat);
        numBugs += cat.getNumBugs();
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugs.add(bug);
        numBugs++;
    }

    public String getName () {
        return projName;
    }

    public int getNumBugs () {
        return numBugs;
    }

    public String getModTime () {
        return time;
    }

    public String getOwner () {
        return ownerName;
    }

    public Vector<BugObj> getBugs() {
        return bugs;
    }
}
