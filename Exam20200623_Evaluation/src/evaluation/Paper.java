package evaluation;

import java.util.*;

public class Paper {
    private String title;
    private String journal;
    private Set<String> authrosSet= new HashSet<>();
    public Paper(String title, String journal) {
        this.title = title;
        this.journal = journal;
    }
    public String getTitle() {
        return title;
    }
    public String getJournal() {
        return journal;
    }
    public Set<String> getAuthrosSet() {
        return authrosSet;
    }
}
