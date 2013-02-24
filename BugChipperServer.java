import com.db4o.*;
import com.db4o.cs.*;

public class BugChipperServer {
    private int dbPort = 1677;
    private String dbFile = "db.db4o";
    private ObjectServer server;
    private boolean stop = false;

    public BugChipperServer () {
        synchronized (this) {
            server = null;
            try {
                server = Db4oClientServer.openServer(dbFile, dbPort);
                System.out.println("Successfully created Bugchipper server instance in file "+dbFile+" on port "+dbPort);
                server.grantAccess("guest","password");
                if (!stop) {
                    this.wait(Long.MAX_VALUE);
                }
            } catch (Exception e) {
                System.out.println("Server exception: "+e);
            }
        }
    }
    
    public static void main (String[] args) {
        new BugChipperServer();
    }
}
