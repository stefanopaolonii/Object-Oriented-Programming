package books;

import java.util.*;
import java.util.stream.Collectors;

public class Book {
	Map<String,Topic> topicsMap= new HashMap<>();
	List<Question> questionsList = new ArrayList<>();
	List<Chapter> chaptersList= new ArrayList<>();
	List<Assignment> assignmentsList= new ArrayList<>();
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
        TheoryChapter newChapter= new TheoryChapter(title, numPages, text);
		chaptersList.add(newChapter);
		return newChapter;
	}

	public ExerciseChapter createExerciseChapter(String title, int numPages) {
        ExerciseChapter newChapter= new ExerciseChapter(title, numPages);
		chaptersList.add(newChapter);
		return newChapter;
	}

	public List<Topic> getAllTopics() {
        return chaptersList.stream().flatMap(chapter->chapter.getTopics().stream()).distinct().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	}

	public boolean checkTopics() {
        return chaptersList.stream().filter(chapter->chapter instanceof ExerciseChapter).flatMap( exchapter-> exchapter.getTopics().stream()).distinct().allMatch(extopic-> chaptersList.stream().filter(schapter->schapter instanceof TheoryChapter).flatMap(tchapther->tchapther.getTopics().stream()).map(Topic::getKeyword).distinct().collect(Collectors.toList()).contains(extopic.getKeyword()));
	}

	public Assignment newAssignment(String ID, ExerciseChapter chapter) {
        Assignment newAssignment= new Assignment(ID, chapter);
		assignmentsList.add(newAssignment);
		return newAssignment;
	}
	
    /**
     * builds a map having as key the number of answers and 
     * as values the list of questions having that number of answers.
     * @return
     */
    public Map<Long,List<Question>> questionOptions(){
        return questionsList.stream().collect(Collectors.groupingBy(question->(long) question.getAnswersMap().size(),Collectors.toList()));
    }

	public List<String> topicPopularity(){
		return topicsMap.values().stream().collect(Collectors.toMap(Topic::getKeyword,topic->(int)chaptersList.stream().filter(chapter->chapter instanceof TheoryChapter).filter(chapter->chapter.getTopics().stream().anyMatch(chtopic->chtopic.getKeyword().equals(topic.getKeyword()))).count())).entrySet().stream().map(entry->entry.getKey()+" : "+entry.getValue()).collect(Collectors.toList());
	}
}
