package bcclient.database.objects;

import java.util.*;

public class CategoryObj {
    String catKey, catVal;
    BugObj bug;
    BugContainer bugCon;
    int numBugs = 0;
    Vector<BugContainer> bugs;

    public CategoryObj() {}
    
    public CategoryObj (String inp_catkey, String inp_catval) {
        catKey = inp_catkey;
        catVal = inp_catval;
        bugs = new Vector<BugContainer>();
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugCon = new BugContainer(bug);
        bugs.add(bugCon);
    }

    public String getKey() {
        return catKey;
    }

    public String getVal() {
        return catVal;
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
