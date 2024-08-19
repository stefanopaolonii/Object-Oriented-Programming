package books;

import java.util.*;

public class Book {
	Map<String,Topic> topicsMap= new HashMap<>();
	List<Question> questionsList = new ArrayList<>();
    /**
	 * Creates a new topic, if it does not exist yet, or returns a reference to the
	 * corresponding topic.
	 * 
	 * @param keyword the unique keyword of the topic
	 * @return the {@link Topic} associated to the keyword
	 * @throws BookException
	 */
	public Topic getTopic(String keyword) throws BookException {
	    if(keyword=="" || keyword==null) throw new BookException();
		if(!topicsMap.containsKey(keyword)) topicsMap.put(keyword, new Topic(keyword));
		return topicsMap.get(keyword);
	}

	public Question createQuestion(String question, Topic mainTopic) {
        Question newQuestion= new Question(question, mainTopic);
		questionsList.add(newQuestion);
		return newQuestion;
	}

	public TheoryChapter createTheoryChapter(String title, int numPages, String text) {
        return null;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
        return null;
	}

	public List<Topic> getAllTopics() {
        return null;
	}

	public boolean checkTopics() {
        return false;
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
        return null;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
        return null;
    }
}
