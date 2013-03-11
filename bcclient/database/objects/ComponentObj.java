package bcclient.database.objects;

import java.util.*;

public class ComponentObj {
    String compName;
    BugObj bug;
    BugContainer bugCon;
    int numBugs = 0;
    Vector<BugContainer> bugs;

    public ComponentObj() {}
    
    public ComponentObj (String inp_compname) {
        compName = inp_compname;
        bugs = new Vector<BugContainer>();
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugCon = new BugContainer(bug);
        bugs.add(bugCon);
    }

    public String getName () {
        return compName;
    }

    public int getNumBugs() {
        numBugs = 0;
        for (BugContainer inp_bugcon : bugs) {
            bugCon = inp_bugcon;
            if(bugCon.isClosed()) {
                numBugs++;
            }
        }
        return numBugs;
    }

    public Vector<BugContainer> getBugs() {
        return bugs;
    }
}
