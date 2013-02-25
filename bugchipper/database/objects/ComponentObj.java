package bugchipper.database.objects;

import java.util.*;

public class ComponentObj {
    String compName;
    BugObj bug;
    List<BugObj> bugs;
    
    public ComponentObj (String inp_compname) {
        compName = inp_compname;
        bugs = new ArrayList<BugObj>();
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugs.add(bug);
    }

    public String getName () {
        return compName;
    }
}
