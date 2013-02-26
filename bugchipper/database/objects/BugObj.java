package bugchipper.database.objects;

public class BugObj {
    String bugTitle, bugDesc;
    
    public BugObj (String inp_bugtitle, String inp_desc) {
        bugTitle = inp_bugtitle;
        bugDesc = inp_desc;
    }

    public String getTitle () {
        return bugTitle;
    }

    public String getDesc () {
        return bugDesc;
    }
}
