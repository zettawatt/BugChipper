package bugchipper.database.objects;

import java.util.*;

public class CategoryObj {
    String catKey, catVal;
    BugObj bug;
    int numBugs;
    Vector<BugObj> bugs;
    
    public CategoryObj (String inp_catkey, String inp_catval) {
        catKey = inp_catkey;
        catVal = inp_catval;
        bugs = new Vector<BugObj>();
        numBugs = bugs.size();
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugs.add(bug);
        numBugs++;
    }

    public String getKey() {
        return catKey;
    }

    public String getVal() {
        return catVal;
    }

    public int getNumBugs() {
        return numBugs;
    }

    public Vector<BugObj> getBugs() {
        return bugs;
    }
}
