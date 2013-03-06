package bcclient.database.objects;

public class BugIDCounter {
    int id;
    
    public BugIDCounter () {
        id = 0;
    }

    public int getCurrentIDCount () {
        return id;
    }

    public void incCurrentIDCount () {
        id++;
    }
}
