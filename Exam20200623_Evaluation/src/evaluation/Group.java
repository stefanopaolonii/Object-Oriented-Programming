package evaluation;

import java.util.*;

public class Group {
    private final String name;
    private Set<String> disciplinesSet= new HashSet<>();
    private Set<String> membersSet= new HashSet<>();
    public Group(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Set<String> getDisciplinesSet() {
        return disciplinesSet;
    }
    public Set<String> getMembersSet() {
        return membersSet;
    }
    
}
