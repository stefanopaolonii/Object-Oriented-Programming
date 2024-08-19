package books;

import java.util.*;


public class TheoryChapter extends Chapter {
    private String text;
    private Map<String,Topic> topicsMap= new HashMap<>();

    public TheoryChapter(String title,int numPages,String text){
        super(title, numPages);
        this.text=text;
    }
    public String getText() {
		return null;
	}

    public void setText(String newText) {
    }


	public List<Topic> getTopics() {
        return null;
	}

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
    
    public void addTopic(Topic topic) {
    }
    
}
