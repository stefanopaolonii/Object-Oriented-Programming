package evaluation;

import java.util.*;

public class Paper {
    private String title;
    private String journal;
    private Set<String> authrosSet= new HashSet<>();
    public Paper(String title, String journal,String ...authors) {
        this.title = title;
        this.journal = journal;
        this.authrosSet.addAll(Arrays.asList(authors));
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
