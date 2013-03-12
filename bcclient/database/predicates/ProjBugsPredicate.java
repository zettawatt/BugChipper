package bcclient.database.predicates;

import com.db4o.query.*;
import bcclient.database.objects.*;
import java.util.*;

public class ProjBugsPredicate extends Predicate<ProjectObj> {
    Vector<String> projBugs;
    public ProjBugsPredicate(Vector<String> inp_projbugs) {
        projBugs = inp_projbugs;
    }

    public boolean match(ProjectObj project) {
        for (String projBug : projBugs) {
            if (project.getName().equals(projBug)) {
                return true;
            }
        }
        return false;
    }
}
