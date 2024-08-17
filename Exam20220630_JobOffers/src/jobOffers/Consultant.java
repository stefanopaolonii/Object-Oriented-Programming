package jobOffers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Consultant {
    private final String name;
    private List<String> skillsSet= new ArrayList<>();
    public Consultant(String name, String... skills) {
        this.name = name;
        this.skillsSet = Arrays.asList(skills);
    }
    public String getName() {
        return name;
    }
    public List<String> getSkillsSet() {
        return skillsSet;
    }
    
}
