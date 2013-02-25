package bugchipper.database.objects;

import java.util.*;

public class ProjectObj {
    String projName, ownerName;
    ComponentObj comp;
    CategoryObj cat;
    BugObj bug;
    List<ComponentObj> components;
    List<CategoryObj> categories;
    List<BugObj> bugs;
    
    public ProjectObj (String inp_projname, String inp_ownername) {
        projName = inp_projname;
        ownerName = inp_ownername;
        components = new ArrayList<ComponentObj>();
        categories = new ArrayList<CategoryObj>();
        bugs = new ArrayList<BugObj>();
    }

    public void addComp (ComponentObj inp_comp) {
        comp = inp_comp;
        components.add(comp);
    }

    public void addCat (CategoryObj inp_cat) {
        cat = inp_cat;
        categories.add(cat);
    }

    public void addBug (BugObj inp_bug) {
        bug = inp_bug;
        bugs.add(bug);
    }

    public String getName () {
        return projName;
    }

    public String getOwner () {
        return ownerName;
    }
}
