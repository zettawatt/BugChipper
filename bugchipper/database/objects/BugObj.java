package bugchipper.database.objects;

public class BugObj {
    String bugTitle, bugDesc, bugOwner;
    
    public BugObj (String inp_bugtitle, String inp_desc, String inp_owner) {
        bugTitle = inp_bugtitle;
        bugDesc = inp_desc;
        bugOwner = inp_owner;
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
}
