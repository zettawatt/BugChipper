package bcclient.database.predicates;

import com.db4o.query.*;
import bcclient.database.objects.*;
import java.util.*;

public class ProjWithComListPredicate extends Predicate<ProjectObj> {
    Vector<String> components;
    public ProjWithComListPredicate(Vector<String> inp_comps) {
        components = inp_comps;
    }

    public boolean match(ProjectObj project) {
        for (String comp : components) {
            if (project.hasComp(comp)) {
                return true;
            }
        }
        return false;
    }
}
