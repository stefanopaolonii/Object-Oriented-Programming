package books;

import java.util.List;

public class Topic {

	public String getKeyword() {
        return null;
	}
	
	@Override
	public String toString() {
	    return null;
	}

	public boolean addSubTopic(Topic topic) {
        return false;
	}

	/*
	 * Returns a sorted list of subtopics. Topics in the list *MAY* be modified without
	 * affecting any of the Book topic.
	 */
	public List<Topic> getSubTopics() {
        return null;
	}
}
