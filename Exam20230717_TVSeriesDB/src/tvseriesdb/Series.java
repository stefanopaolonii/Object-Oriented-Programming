package tvseriesdb;

import java.util.*;

public class Series {
    private final String title;
    private String tService;
    private final String genre;
    private int seasonCounter=0;
    List<Actor> castList= new ArrayList<>();
    Map<Integer,Season> seasonMap= new TreeMap<>();
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
    public void addSeason(Season season){
        seasonMap.put(++seasonCounter,season);
    }
    public Map<Integer, Season> getSeasonMap() {
        return seasonMap;
    }
}
