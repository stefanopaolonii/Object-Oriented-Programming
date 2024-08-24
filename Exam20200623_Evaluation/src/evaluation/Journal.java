package evaluation;

import java.util.*;

public class Journal {
    private final String name;
    private String discipline;
    private int level;
    private List<Paper> papersList= new ArrayList<>();
    public Journal(String name, String discipline, int level) {
        this.name = name;
        this.discipline = discipline;
        this.level = level;
    }
    public String getName() {
        return name;
    }
    public String getDiscipline() {
        return discipline;
    }
    public int getLevel() {
        return level;
    }
    public void addPaper(Paper paper){
        papersList.add(paper);
    }
    public List<Paper> getPapersList() {
        return papersList;
    }
}
