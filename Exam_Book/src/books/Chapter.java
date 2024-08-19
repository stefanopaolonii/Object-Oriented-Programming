package books;

import java.util.*;

public class Chapter {
    private String title;
    private int numPages;
    public Chapter(String title, int numPages) {
        this.title = title;
        this.numPages = numPages;
    }
    public String getTitle() {
        return title;
    }

    public void setTitle(String newTitle) {
        this.title= newTitle;
    }
    public int getNumPages() {
        return numPages;
    }
    
    public void setNumPages(int newPages) {
        this.numPages=newPages;
    }
    public List<Topic> getTopics(){return null;};
    
}
