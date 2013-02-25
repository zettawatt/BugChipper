package bugchipper.database.objects;

import java.util.*;

public class CategoryObj {
    String catKey, catVal;
    BugObj bug;
    List<BugObj> bugs;
    
    public CategoryObj (String inp_catkey, String inp_catval) {
        catKey = inp_catkey;
        catVal = inp_catval;
        bugs = new ArrayList<BugObj>();
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugs.add(bug);
    }

    public String getKey() {
        return catKey;
    }

    public String getVal() {
        return catVal;
    }
}
