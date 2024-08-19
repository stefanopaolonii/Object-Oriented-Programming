package books;

import java.util.*;
import java.util.stream.Collectors;

public class Topic {
	private final String keyword;
	Map<String,Topic> subtopicsMap= new HashMap<>();
	public Topic(String keyword) {
		this.keyword = keyword;
	}

	public String getKeyword() {
        return keyword;
	}
	
	@Override
	public String toString() {
	    return keyword;
	}

	public boolean addSubTopic(Topic topic) {
		if(subtopicsMap.containsKey(topic.getKeyword())) return false;
        subtopicsMap.put(topic.getKeyword(), topic);
		return true;
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
        return subtopicsMap.values().stream().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	}
}
