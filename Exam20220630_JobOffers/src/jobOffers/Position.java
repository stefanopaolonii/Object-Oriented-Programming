package jobOffers;

import java.util.*;

public class Position {
    private final String name;
    private Map<String,Integer> skillslevelMap = new HashMap<>();
    public Position(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Map<String, Integer> getSkillslevelMap() {
        return skillslevelMap;
    }

    public void addSkill(String skillName,int skillLevel){
        skillslevelMap.put(skillName, skillLevel);
    }
    
}
