package applications;

import java.util.*;

public class Applicant {
    private final String name;
    private Map<String,Integer> skillsMap= new HashMap<>();
    public Applicant(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Map<String, Integer> getSkillsMap() {
        return skillsMap;
    }
}
