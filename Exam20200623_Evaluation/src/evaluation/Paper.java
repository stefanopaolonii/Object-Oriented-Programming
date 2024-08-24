package evaluation;

import java.util.*;

public class Paper {
    private String title;
    private Journal journal;
    private Set<String> authrosSet= new HashSet<>();
    public Paper(String title, Journal journal,String ...authors) {
        this.title = title;
        this.journal = journal;
        this.authrosSet.addAll(Arrays.asList(authors));
    }
    public String getTitle() {
        return title;
    }
    public Journal getJournal() {
        return journal;
    }
    public Set<String> getAuthrosSet() {
        return authrosSet;
    }
}
