package bugchipper.database;

import java.sql.*;
import bugchipper.*;

public class DAO {
    Mediator mdtr;

    public DAO (Mediator inp_mdtr) {
        mdtr = inp_mdtr;
        mdtr.registerDAO(this);
        System.out.println("Database Access Object Initialized");
    }
}

