package test.additional;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tvseriesdb.TSException;

import tvseriesdb.TVSeriesDB;

public class TestR5_Stats {

	private final static String[] ts = {"Netflix","Disney+","Amazon Prime Video"};
	

	private TVSeriesDB tvsdb;
	
	@Before
	public void setUp() throws TSException {
		tvsdb = new TVSeriesDB();
		tvsdb.addTransmissionService(ts);
		
		tvsdb.addTVSeries("Daredevil", "Netflix", "Fantasy");
		tvsdb.addTVSeries("The Punisher", "Netflix", "Fantasy");
		tvsdb.addTVSeries("Loki", "Netflix", "Fantasy");
		tvsdb.addTVSeries("The Boys", "Amazon Prime Video", "Drama");
		tvsdb.addTVSeries("The Marvelous Mrs. Maisel", "Amazon Prime Video", "Comedy");
		
		tvsdb.addUser("fuckingMetalLover","Fantasy");
		tvsdb.addUser("__roby","Comedy");
		tvsdb.addUser("gianlu","Drama");
		
		tvsdb.addReview("fuckingMetalLover", "Daredevil", 8);
		tvsdb.addReview("__roby", "Daredevil", 10);
		tvsdb.addReview("gianlu", "The Marvelous Mrs. Maisel", 10);
		tvsdb.addReview("fuckingMetalLover", "The Boys", 4);
		tvsdb.addReview("__roby", "The Boys", 5);
		
		tvsdb.addActor("act", "n1", "French");
		tvsdb.addActor("act", "n2", "American");
		tvsdb.addActor("act", "n3", "English");
		
		tvsdb.addCast("The Boys", "act n1", "act n3");
		tvsdb.addCast("The Marvelous Mrs. Maisel", "act n2", "act n3");
		tvsdb.addCast("Daredevil", "act n2", "act n3");
	}
    
	@Test
	public void testMostAwaitedSeasonSuccess() throws TSException {
		
		tvsdb.addSeason("Daredevil", 13, "03:11:2021");
		tvsdb.addSeason("Daredevil", 2, "03:12:2024");
		

		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "03:12:2021");
		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "31:10:2022");
		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "03:12:2024");
		
		tvsdb.addSeason("The Punisher", 8, "03:12:2019");
		tvsdb.addSeason("The Punisher", 8, "29:11:2020");
		
	    assertEquals("Wrong most awaited season", 
	    		    "The Marvelous Mrs. Maisel 3", tvsdb.mostAwaitedSeason("14:07:2023"));
	}

	@Test
	public void testMostAwaitedSeasonTie() throws TSException {
	    
		tvsdb.addSeason("Daredevil", 13, "03:11:2021");
		tvsdb.addSeason("Daredevil", 2, "03:12:2024");
		

		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "03:12:2021");
		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "31:10:2022");
		tvsdb.addSeason("The Marvelous Mrs. Maisel", 8, "03:12:2024");
		
		tvsdb.addSeason("The Punisher", 8, "03:12:2019");
		tvsdb.addSeason("The Punisher", 8, "29:11:2020");
		
		tvsdb.addReview("__roby", "The Marvelous Mrs. Maisel", 8);
		
	    assertEquals("Wrong most awaited season", 
    		        "Daredevil 2", tvsdb.mostAwaitedSeason("14:07:2023"));
	}
	
	@Test
	public void testMostAwaitedSeasonEmpty() throws TSException {	    
		assertEquals("Expecting no most awaited season", "",
				      tvsdb.mostAwaitedSeason("14:07:2023"));
	}
	
	@Test
	public void testBestActorsSuccess() throws TSException {
	
		List<String> best = tvsdb.bestActors("Amazon Prime Video");
		assertNotNull(best);
		assertEquals("Wrong number of best actors", 1, best.size());
	    assertEquals("act n2", best.get(0));
	    
	}

	@Test
	public void testBestActorsNonExistentTransmissionService() {
	    assertThrows("Cannot find best actor for non existing service", 
	       TSException.class, ()->tvsdb.bestActors("Hulu"));
	}

	@Test
	public void testBestActorsEmpty() throws TSException {
	    assertEquals("No best actor for service " + ts[1],
	    			0, tvsdb.bestActors(ts[1]).size());
	    tvsdb.addReview("__roby", "The Marvelous Mrs. Maisel", 2);
	    assertEquals("No best actor for service " + ts[2],
	    			0, tvsdb.bestActors(ts[2]).size()); 
	}
}