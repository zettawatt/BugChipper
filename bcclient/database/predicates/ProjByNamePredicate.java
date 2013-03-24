package bcclient.database.predicates;

import com.db4o.query.*;
import bcclient.database.objects.*;
import java.util.*;

public class ProjByNamePredicate extends Predicate<ProjectObj> {
    String projName;
    public ProjByNamePredicate(String inp_projname) {
        projName = inp_projname;
    }

    public boolean match(ProjectObj project) {
        return project.getName().equals(projName);
    }
}
