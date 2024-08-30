package fit;

import java.util.*;

public class Customer {
    private final int id;
    private final String name;
    private List<Lesson> lessonsList= new ArrayList<>();
    
    public Customer(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
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
