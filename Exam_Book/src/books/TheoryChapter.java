package books;

import java.util.*;
import java.util.stream.Collectors;


public class TheoryChapter extends Chapter {
    private String text;
    private Set<Topic> topicsSet= new HashSet<>();

    public TheoryChapter(String title,int numPages,String text){
        super(title, numPages);
        this.text=text;
    }
    public String getText() {
		return text;
	}

    public void setText(String newText) {
        this.text=newText;
    }

	public List<Topic> getTopics() {
        return topicsSet.stream().sorted(Comparator.comparing(Topic::getKeyword)).collect(Collectors.toList());
	}
    public void addTopic(Topic topic) {
        topicsSet.add(topic);
        if(!topic.getSubTopics().isEmpty()) topic.getSubTopics().forEach(stopic->this.addTopic(stopic));
    }
    
}
