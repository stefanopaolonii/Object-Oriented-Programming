package tvseriesdb;

import java.util.*;

public class Series {
    private final String title;
    private String tService;
    private final String genre;
    List<Actor> castList= new ArrayList<>();
    public Series(String title, String tService, String genre) {
        this.title = title;
        this.tService = tService;
        this.genre = genre;
    }
    public String getTitle() {
        return title;
    }
    public String gettService() {
        return tService;
    }
    public String getGenre() {
        return genre;
    }
    public List<Actor> getCastList() {
        return castList;
    }
    public void addActor(Actor actor){
        castList.add(actor);
    }
}
