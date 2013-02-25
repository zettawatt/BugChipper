package bugchipper.database.objects;

public class BugObj {
    String bugName, bugDesc;
    
    public BugObj (String inp_bugname, String inp_desc) {
        bugName = inp_bugname;
        bugDesc = inp_desc;
    }

    public String getName () {
        return bugName;
    }

    public String getDesc () {
        return bugDesc;
    }
}
