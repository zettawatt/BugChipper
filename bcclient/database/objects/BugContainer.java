package bcclient.database.objects;

import java.util.*;

public class BugContainer {
    BugObj bug;
    boolean closed, viewed;
    String comment;
    
    public BugContainer() {}

    public BugContainer (BugObj inp_bug) {
        bug = inp_bug;
        closed = false;
        viewed = false;
        comment = "";
    }

    public boolean isClosed() {
        return closed;
    }

    public boolean wasViewed() {
        return viewed;
    }

    public void setClosedStatus(boolean inp_closed) {
        closed = inp_closed;
    }

    public void setViewedStatus(boolean inp_viewed) {
        viewed = inp_viewed;
    }

    public void addComment(String inp_comment) {
        comment = inp_comment;
    }

    public String getComment() {
        return comment;
    }
}
