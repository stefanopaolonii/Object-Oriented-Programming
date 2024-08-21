package test.additional;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import tvseriesdb.TSException;

import tvseriesdb.TVSeriesDB;

public class TestR2_SeasonEpisodeMngmt {

	private final static String[] ts = {"Netflix","Disney+","Amazon Prime Video"};
	

	private TVSeriesDB tvsdb;
	
	@Before
	public void setUp() throws TSException {
		tvsdb = new TVSeriesDB();
		tvsdb.addTransmissionService(ts);
		tvsdb.addTVSeries("Daredevil", "Netflix", "Fantasy");
		tvsdb.addTVSeries("The Punisher", "Netflix", "Fantasy");
		tvsdb.addTVSeries("The Marvelous Mrs. Maisel", "Amazon Prime Video", "Comedy");
	}
    
	@Test
	public void testAddSeasonSuccess() throws TSException {
	    
	    assertEquals("Wrong number of seasons", 1,
	    			  tvsdb.addSeason("Daredevil", 10, "01:01:2023"));
	    assertEquals("Wrong number of seasons", 2, 
	    			  tvsdb.addSeason("Daredevil", 5, "01:02:2024"));
	}

	@Test
	public void testAddSeasonNoSeries() {

		assertThrows("Cannot add a seson to a series that does not exist", TSException.class,
					()-> tvsdb.addSeason("The Bear", 10, "01:01:2023"));
	}

	@Test
	public void testAddSeasonInvalidReleaseDate() throws TSException {
		tvsdb.addSeason("Daredevil", 10, "03:11:2023");
	    assertThrows("Cannot add a season released before the last one", TSException.class,
	    		    ()->tvsdb.addSeason("Daredevil", 10, "01:01:2022"));
	    assertThrows("Cannot add a season released before the last one", TSException.class,
    		        ()->tvsdb.addSeason("Daredevil", 10, "01:10:2023"));
	    assertThrows("Cannot add a season released before the last one", TSException.class,
    		        ()->tvsdb.addSeason("Daredevil", 10, "02:11:2023"));
	}
    
	@Test
	public void testAddEpisodeSuccess() throws TSException {
		tvsdb.addSeason("Daredevil", 13, "03:11:2023");
		tvsdb.addSeason("Daredevil", 2, "03:12:2024");
		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "03:12:2024");
		
	    assertEquals("Wrong number of episodes in the season", 1, 
	    		tvsdb.addEpisode("Daredevil", 1, "Into the ring"));
	    assertEquals("Wrong number of episodes in the season", 2,
	    		tvsdb.addEpisode("Daredevil", 1, "Cut Man"));
	    assertEquals("Wrong number of episodes in the season", 1,
	    		tvsdb.addEpisode("Daredevil", 2, "Bang!"));
	    assertEquals("Wrong number of episodes in the season", 1,
	    		tvsdb.addEpisode("The Marvelous Mrs. Maisel", 1, "Pilot"));
	}

	@Test
	public void testAddEpisodeNonExistentSeries() {
	    assertThrows("Cannot add episode to non-existing series", TSException.class,
	    		    ()->tvsdb.addEpisode("The Office", 1, "Pilot"));
	}

	@Test
	public void testAddEpisodeNonExistentSeason() throws TSException {
		tvsdb.addSeason("Daredevil", 13, "03:11:2023");
		assertThrows("Cannot add episode to non-existing season", TSException.class,
					()->tvsdb.addEpisode("Daredevil", 2, "Bang!"));
		
		assertThrows("Cannot add episode to non-existing series and season", TSException.class,
					()->tvsdb.addEpisode("The Marvelous Mrs. Maisel", 2, "Bang!"));
	
	}

	@Test
	public void testAddEpisodeMaxEpisodesReached() throws TSException {
		tvsdb.addSeason("Daredevil", 2, "03:11:2023");
		tvsdb.addEpisode("Daredevil", 1, "Into the Ring");
		tvsdb.addEpisode("Daredevil", 1, "Cut Man");
		assertThrows("Cannot more episodes than declared in the season", TSException.class,
					()->tvsdb.addEpisode("Daredevil", 1, "Rabbit in a Snow Storm"));
		
	}

	@Test
	public void testAddEpisodeDuplicateTitle() throws TSException {
		tvsdb.addSeason("Daredevil", 13, "03:11:2023");
		tvsdb.addSeason("Daredevil", 13, "03:11:2023");
		tvsdb.addEpisode("Daredevil", 1, "Into the Ring");
		tvsdb.addEpisode("Daredevil", 1, "Cut Man");
		assertThrows("Cannot more episodes with same title in same season",TSException.class,
					()->tvsdb.addEpisode("Daredevil", 1, "Cut Man"));
		assertEquals("Should be possible to add episodes with same title in different seasons", 
					1, tvsdb.addEpisode("Daredevil", 2, "Cut Man"));
	}

	@Test
	public void testCheckMissingEpisodesEmptyMap() throws TSException {
		tvsdb.addSeason("Daredevil", 2, "03:11:2023");
		tvsdb.addSeason("The Marvelous Mrs. Maisel", 1, "03:11:2023");
		
		tvsdb.addEpisode("Daredevil", 1, "Into the Ring");
		tvsdb.addEpisode("Daredevil", 1, "Cut Man");
		tvsdb.addEpisode("The Marvelous Mrs. Maisel", 1, "Pilot");
		
		Map<String, List<Integer>> incompleteSeasons=tvsdb.checkMissingEpisodes();
		assertEquals("All seasons of all sereies should be complete", 
					0, incompleteSeasons.size());
		
	}
	
	@Test
	public void testCheckMissingEpisodes() throws TSException {
		
		tvsdb.addSeason("Daredevil", 2, "03:11:2023");
		tvsdb.addSeason("Daredevil", 10, "03:12:2023");
		tvsdb.addSeason("Daredevil", 10, "03:03:2024");
		
		tvsdb.addEpisode("Daredevil", 1, "Into the Ring");
		tvsdb.addEpisode("Daredevil", 1, "Cut Man");
		
		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "03:01:2023");
		
		tvsdb.addEpisode("The Marvelous Mrs. Maisel", 1, "Pilot");
		
		Map<String, List<Integer>> incompleteSeasons=tvsdb.checkMissingEpisodes();
		
		assertEquals("There shuould be 2 seires with incomplete seasons", 
				    2, incompleteSeasons.size());
		assertTrue("Could no find incomplete series", 
				 incompleteSeasons.containsKey("The Marvelous Mrs. Maisel"));
		assertTrue("Could no find incomplete series",
				 incompleteSeasons.containsKey("Daredevil"));
		assertEquals("Could not find incomplete season" , 2,
					  incompleteSeasons.get("Daredevil").get(0).intValue());
		assertEquals("Could not find incomplete season" ,3,
					  incompleteSeasons.get("Daredevil").get(1).intValue());
		assertEquals("Could not find incomplete season" ,1,
					  incompleteSeasons.get("The Marvelous Mrs. Maisel").get(0).intValue());
		
	}
}
