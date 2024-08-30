package fit;

import java.util.*;

public class Gym {
    private final String name;
    private Map<String,Lesson> lessonsMap= new HashMap<>();

    public Gym(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Map<String,Lesson> getLessonMap() {
        return lessonsMap;
    }
    public void addLesson(String slot,Lesson lesson){
        lessonsMap.put(slot,lesson);
    }
    
}
