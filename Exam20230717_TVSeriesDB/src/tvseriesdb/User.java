package tvseriesdb;
import java.util.*;


public class User {
    public User(String username, String genre) {
        this.username = username;
        this.genre = genre;
    }
    private final String username;
    private String genre;
    private List<Series> likedseriesList= new ArrayList<>();
    private Map<String,Integer> ratingsMap= new HashMap<>();
    public String getUsername() {
        return username;
    }
    public String getGenre() {
        return genre;
    }
    public List<Series> getLikedseriesList() {
        return likedseriesList;
    }
    public Map<String, Integer> getRatingsMap() {
        return ratingsMap;
    }
    public void addSeries(Series series){
        likedseriesList.add(series);
    }
    public void addRating(String series,int score){
        ratingsMap.put(series,score );
    }
}
