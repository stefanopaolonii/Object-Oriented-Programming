package tvseriesdb;

import java.util.*;

public class Season {
    private final String tvSeriesTitle;
    private int numEpisodes;
    private String releaseDate;
    private List<String> episodesList= new ArrayList<>();
    public Season(String tvSeriesTitle, int numEpisodes, String releaseDate) {
        this.tvSeriesTitle = tvSeriesTitle;
        this.numEpisodes = numEpisodes;
        this.releaseDate = releaseDate;
    }
    public List<String> getEpisodesList() {
        return episodesList;
    }
    public String getTvSeriesTitle() {
        return tvSeriesTitle;
    }
    public int getNumEpisodes() {
        return numEpisodes;
    }
    public String getReleaseDate() {
        return releaseDate;
    }

    public int compareTo(Season other){
        String[] thisparts=releaseDate.split(":");
        String[] otherparts=other.getReleaseDate().split(":");
        if(thisparts[2].compareTo(otherparts[2])==0){
            if(thisparts[1].compareTo(otherparts[1])==0){
                return thisparts[0].compareTo(otherparts[0]);
            }
            return thisparts[1].compareTo(otherparts[1]);
        }
        return thisparts[2].compareTo(otherparts[2]);
    }
    public void addEpisode(String episodeTitle){
        episodesList.add(episodeTitle);
    }
}
