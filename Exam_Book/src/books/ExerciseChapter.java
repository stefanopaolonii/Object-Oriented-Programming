package books;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class ExerciseChapter extends Chapter{
    private List<Question> questionsList= new ArrayList<>();
    public ExerciseChapter(String title,int numPages){
        super(title, numPages);
    }
    @Override
    public List<Topic> getTopics() {
        return questionsList.stream().map(Question::getMainTopic).distinct().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	};
	
	public void addQuestion(Question question) {
        questionsList.add(question);
	}	
}
