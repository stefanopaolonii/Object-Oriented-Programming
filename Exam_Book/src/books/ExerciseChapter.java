package books;

import java.util.ArrayList;
import java.util.List;


public class ExerciseChapter extends Chapter{
    private List<Question> questionsList= new ArrayList<>();
    public ExerciseChapter(String title,int numPages){
        super(title, numPages);
    }
    public List<Topic> getTopics() {
        return null;
	};
	

    public String getTitle() {
        return null;
    }

    public void setTitle(String newTitle) {
    }

    public int getNumPages() {
        return -1;
    }
    
    public void setNumPages(int newPages) {
    }
    

	public void addQuestion(Question question) {
	}	
}
