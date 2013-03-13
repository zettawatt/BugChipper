package bcclient.database.predicates;

import com.db4o.query.*;
import bcclient.database.objects.*;
import java.util.*;

public class ProjWithComListPredicate extends Predicate<ProjectObj> {
    Vector<String> components;
    public ProjWithComListPredicate(Vector<String> inp_comps) {
        components = inp_comps;
        System.out.println("called projwithcomlistpredicate");
    }

    public boolean match(ProjectObj project) {
        System.out.println("looked at project"); //FIXME: program never enters match???
        for (String comp : components) {
            System.out.println(comp);
            if (project.hasComp(comp)) {
                return true;
            }
        }
        return false;
    }
}
