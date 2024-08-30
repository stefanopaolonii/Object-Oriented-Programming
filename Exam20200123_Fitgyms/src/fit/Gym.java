package fit;

import java.util.*;

public class Gym {
    private final String name;
    private List<Lesson> lessonsList= new ArrayList<>();

    public Gym(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public List<Lesson> getLessonsList() {
        return lessonsList;
    }
    public void addLesson(Lesson lesson){
        lessonsList.add(lesson);
    }
    
}
