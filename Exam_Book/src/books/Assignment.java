package books;

import java.util.*;


public class Assignment {
    private String id;
    private ExerciseChapter chapter;
    private Map<Question,List<String>> answersMap= new HashMap<>();
    
    public Assignment(String id, ExerciseChapter chapter) {
        this.id = id;
        this.chapter = chapter;
    }

    public String getID() {
        return id;
    }

    public ExerciseChapter getChapter() {
        return chapter;
    }

    public double addResponse(Question q,List<String> answers) {
        return -1.0;
    }
    
    public double totalScore() {
        return -1.0;
    }

}
