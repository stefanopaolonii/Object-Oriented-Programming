package tvseriesdb;

import java.util.*;

public class TVSeriesDB {
	private Set<String> servicesSet= new HashSet<>();
	private Map<String,Series> seriesMap= new HashMap<>();
	private Map<String,Actor> actorsMap= new HashMap<>();
	// R1
	
	/**
	 * adds a list of transmission services.
	 * The method can be invoked multiple times.
	 * Possible duplicates are ignored.
	 * 
	 * @param tServices the transmission services
	 * @return number of transmission service inserted so far
	 */
	public int addTransmissionService(String...tServices) {
		servicesSet.addAll(Arrays.asList(tServices));
		return servicesSet.size();
	}
	
	/**
	 * adds a TV series whose name is unique. 
	 * The method can be invoked multiple times.
	 * 
	 * @param title the title of the TV Series
	 * @param tService the name of the transmission service 
	 * broadcasting the TV series.
	 * @param genre the genre of the TV Series
	 * @return number of TV Series inserted so far
	 * @throws TSException if TV Series is already inserted or transmission service does not exist.
	 */
	public int addTVSeries(String title, String tService, String genre) throws TSException {
		if(!servicesSet.contains(tService)) throw new TSException();
		if(seriesMap.containsKey(title)) throw new TSException();
		seriesMap.put(title, new Series(title, tService, genre));
		return seriesMap.size();
	}
	
	/**
	 * adds an actor whose name and surname is unique. 
	 * The method can be invoked multiple times.
	 * 
	 * @param name the name of the actor
	 * @param surname the surname of the actor
	 * @param nationality the nationality of the actor
	 * @return number of actors inserted so far
	 * @throws TSException if actor has already been inserted.
	 */
	public int addActor(String name, String surname, String nationality) throws TSException {
		if(actorsMap.containsKey(name+" "+surname)) throw new TSException();
		actorsMap.put(name+" "+surname, new Actor(name, surname, nationality));
		return actorsMap.size();
	}
	
	/**
	 * adds a cast of actors to a TV series
	 * 
	 * @param tvSeriesTitle	TV series for which the cast is inserted
	 * @param actors	list of actors to add to a TV series, format of 
	 * 					each actor identity is "name surname"
	 * @return number of actors in the cast
	 * @throws TSException in case of non-existing actor or TV Series does not exist
	 */
	public int addCast(String tvSeriesTitle, String...actors) throws TSException {
		if(!actorsMap.keySet().containsAll(Arrays.asList(actors))) throw new TSException();
		if(!seriesMap.containsKey(tvSeriesTitle)) throw new TSException();
		Arrays.asList(actors).stream().forEach(actor-> seriesMap.get(tvSeriesTitle).addActor(actorsMap.get(actor)));
		return seriesMap.get(tvSeriesTitle).getCastList().size();
	}
      
	// R2
	
	/**
	 * adds a season to a TV series
	 * 
	 * @param tvSeriesTitle	TV series for which the season is inserted
	 * @param numEpisodes	number of episodes of the season
	 * @param releaseDate	release date for the season (format "gg:mm:yyyy")
	 * @return number of seasons inserted so far for the TV series
	 * @throws TSException in case of non-existing TV Series or wrong releaseDate
	 */
	public int addSeason(String tvSeriesTitle, int numEpisodes, String releaseDate) throws TSException {
		return -1;
	}
	

	/**
	 * adds an episode to a season of a TV series
	 * 
	 * @param tvSeriesTitle	TV series for which the season is inserted
	 * @param numSeason	number of season to which add an episode
	 * @param episodeTitle	title of the episode (unique)
	 * @return number of episodes inserted so far for that season of the TV series
	 * @throws TSException in case of non-existing TV Series, season, repeated title 
	 * 			of the episode or exceeding number of episodes inserted
	 */
	public int addEpisode(String tvSeriesTitle, int numSeason, String episodeTitle) throws TSException {
		return -1;
	}

	/**
	 * check which series and which seasons are still lacking
	 * episodes information
	 * 
	 * @return map with TV series and a list of seasons missing episodes information
	 * 
	 */
	public Map<String, List<Integer>> checkMissingEpisodes() {
		return null;
	}

	// R3
	
	/**
	 * Add a new user to the database, with a unique username.
	 * 
	 * @param username	username of the user
	 * @param genre		user favourite genre
	 * @return number of registered users
	 * @throws TSException in case username is already registered
	 */
	public int addUser(String username, String genre) throws TSException {
		return -1;
	}

	/**
	 * Adds a series to the list of favourite
	 * series of a user.
	 * 
	 * @param username	username of the user
	 * @param tvSeriesTitle	 title of TV series to add to the list of favourites
	 * @return number of favourites TV series of the users so far
	 * @throws TSException in case user is not registered or TV series does not exist
	 */
	public int likeTVSeries(String username, String tvSeriesTitle) throws TSException {
		return -1;
	}
	
	/**
	 * Returns a list of suggested TV series. 
	 * A series is suggested if it is not already in the user list and if it is of the user's favourite genre 
	 * 
	 * @param username name of the user
	 * @throws TSException if user does not exist
	 */
	public List<String> suggestTVSeries(String username) throws TSException {
		return null;
	}
	
	//R4 

	/**
	 * Add reviews from a user to a tvSeries
	 * 
	 * @param username	username of the user
	 * @param tvSeries		name of the participant
	 * @param score		review score assigned
	 * @return the average score of the series so far from 0 to 10, extremes included
	 * @throws TSException	in case of invalid user, score or TV Series
	 */
	public double addReview(String username, String tvSeries, int score) throws TSException {
		return -1.0;
	}

	/**
	 * Average rating of tv series in the favourite list of a user
	 * 
	 * @param username	username of the user
	 * @return the average score of the series in the list of favourites of the user
	 * @throws TSException	in case of invalid user, score or TV Series
	 */
	public double averageRating(String username) throws TSException {
		return -1.0;
	}
	
	// R5

	/**
	 * Returns most awaited season of a tv series using format "TVSeriesName seasonNumber", the last season of the best-reviewed TV series who has not come out yet with
	 * respect to the current date passed in input. In case of tie, select using alphabetical order. Date format: dd::mm::yyyy
	 * 
	 * @param currDate	currentDate
	 * @return the most awaited season of TV series who still has to come out
	 * @throws TSException	in case of invalid user, score or TV Series
	 */
	public String mostAwaitedSeason(String currDate) throws TSException {
		return null;
	}
	
	/**
	 * Computes the best actors working in tv series of a transmission service passed
	 * in input. The best actors have worked only in tv series of that transmission service
	 * with average rating higher than 8.
	 * @param transmissionService	transmission service to consider
	 * @return the best actors' names as "name surname" list
	 * @throws TSException	in case of transmission service not in the DB
	 */
	public List<String> bestActors(String transmissionService) throws TSException {
		return null;
	}

	
}
