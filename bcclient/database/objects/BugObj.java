package bugchipper.database.objects;

public class BugObj {
    String bugTitle, bugDesc, bugOwner;
    int id;
    
    public BugObj (String inp_bugtitle, String inp_desc, String inp_owner, int inp_id) {
        bugTitle = inp_bugtitle;
        bugDesc = inp_desc;
        bugOwner = inp_owner;
        id = inp_id;
    }

    public String getTitle () {
        return bugTitle;
    }

    public String getDesc () {
        return bugDesc;
    }

    public String getOwner () {
        return bugOwner;
    }

    public int getID () {
        return id;
    }
}
