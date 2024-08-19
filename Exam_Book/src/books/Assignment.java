package books;

import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;


public class Assignment {
    private String id;
    private ExerciseChapter chapter;
    private Map<Question,List<String>> answersMap= new HashMap<>();
    private List<Double> scoresList= new ArrayList<>();
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
        answersMap.put(q, answers);
        int n=q.getAnswersMap().size();
        int fp= (int) answers.stream().filter(answer->q.getAnswersMap().entrySet().stream().filter(entry->!entry.getValue()).map(entry->entry.getKey()).collect(Collectors.toList()).contains(answer)).count();
        int fn= (int) q.getAnswersMap().entrySet().stream().filter(entry->entry.getValue()).map(entry->entry.getKey()).filter(answer->!answers.contains(answer)).count();
        scoresList.add((n-fp-fn)/(double)n);
        return (n-fp-fn)/(double)n;
    }
    
    public double totalScore() {
        return scoresList.stream().mapToDouble(Double::doubleValue).sum();
    }
}
