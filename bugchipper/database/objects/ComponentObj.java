package bugchipper.database.objects;

import java.util.*;

public class ComponentObj {
    String compName;
    BugObj bug;
    int numBugs;
    Vector<BugObj> bugs;
    
    public ComponentObj (String inp_compname) {
        compName = inp_compname;
        bugs = new Vector<BugObj>();
        numBugs = bugs.size();
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugs.add(bug);
        numBugs++;
    }

    public String getName () {
        return compName;
    }

    public int getNumBugs() {
        return numBugs;
    }

    public Vector<BugObj> getBugs() {
        return bugs;
    }
}
