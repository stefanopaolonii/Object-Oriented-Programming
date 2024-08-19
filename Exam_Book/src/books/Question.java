package books;

import java.util.*;
import java.util.stream.Collectors;

public class Question {
	private String question;
	private Topic mainTopic;
	private Map<String,Boolean> answersMap= new HashMap<>();
	public Question(String question, Topic mainTopic) {
		this.question = question;
		this.mainTopic = mainTopic;
	}

	public String getQuestion() {
		return question;
	}
	public Map<String, Boolean> getAnswersMap() {
		return answersMap;
	}
	public Topic getMainTopic() {
		return mainTopic;
	}

	public void addAnswer(String answer, boolean correct) {
		answersMap.put(answer, correct);
	}
	
    @Override
    public String toString() {
        return question+" ("+mainTopic.getKeyword()+")";
    }

	public long numAnswers() {
	    return answersMap.values().stream().count();
	}

	public Set<String> getCorrectAnswers() {
		return answersMap.entrySet().stream().filter(entry->entry.getValue()).map(Map.Entry::getKey).collect(Collectors.toSet());
	}

	public Set<String> getIncorrectAnswers() {
		return answersMap.entrySet().stream().filter(entry->!entry.getValue()).map(Map.Entry::getKey).collect(Collectors.toSet());
	}
}
