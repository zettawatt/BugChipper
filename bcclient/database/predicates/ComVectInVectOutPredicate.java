package bcclient.database.predicates;

import com.db4o.query.*;
import bcclient.database.objects.*;
import java.util.*;

public class ComVectInVectOutPredicate extends Predicate<ComponentObj> {
    Vector<String> comBugs;
    public ComVectInVectOutPredicate(Vector<String> inp_combugs) {
        comBugs = inp_combugs;
    }

    public boolean match(ComponentObj component) {
        for (String comBug : comBugs) {
            if (component.getName().equals(comBug)) {
                return true;
            }
        }
        return false;
    }
}
