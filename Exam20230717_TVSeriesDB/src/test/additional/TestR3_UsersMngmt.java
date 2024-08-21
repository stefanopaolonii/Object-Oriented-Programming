package test.additional;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import tvseriesdb.TSException;

import tvseriesdb.TVSeriesDB;

public class TestR3_UsersMngmt {

	private final static String[] ts = {"Netflix","Disney+","Amazon Prime Video"};
	

	private TVSeriesDB tvsdb;
	
	@Before
	public void setUp() throws TSException {
		tvsdb = new TVSeriesDB();
		tvsdb.addTransmissionService(ts);
		tvsdb.addTVSeries("Daredevil", "Netflix", "Fantasy");
		tvsdb.addTVSeries("The Punisher", "Netflix", "Fantasy");
		tvsdb.addTVSeries("Loki", "Netflix", "Fantasy");
		tvsdb.addTVSeries("The Bear", "Disney+", "Drama");
		tvsdb.addTVSeries("The Marvelous Mrs. Maisel", "Amazon Prime Video", "Comedy");
	}
    
	@Test
	public void testAddUserSuccess() throws TSException {
	    assertEquals("Wrong number of users", 1, 
	    		      tvsdb.addUser("fuckingMetalLover","Fantasy"));
	    assertEquals("Wrong number of users", 2,
	    		      tvsdb.addUser("___roberta","Horror"));
	}

	@Test
	public void testAddUserExistingUser() throws TSException {
		tvsdb.addUser("fuckingMetalLover","Fantasy");
		assertThrows("Cannot add duplicate user names", TSException.class,
				    ()->tvsdb.addUser("fuckingMetalLover","Comedy"));

	}
	
	@Test
	public void testLikeTVSeriesSuccess() throws TSException {
		tvsdb.addUser("fuckingMetalLover","Fantasy");
	    assertEquals("Wrong number of favorite series", 1,
	    		      tvsdb.likeTVSeries("fuckingMetalLover", "The Marvelous Mrs. Maisel"));
	    assertEquals("Wrong number of favorite series",2,
	    		      tvsdb.likeTVSeries("fuckingMetalLover", "The Bear"));
	}

	
	@Test
	public void testLikeTVSeriesNonExistentUser() {
		assertThrows("Cannot add favorites for non existing user", TSException.class,
				    ()->tvsdb.likeTVSeries("fuckingMetalLover", "The Marvelous Mrs. Maisel"));
	}

	@Test
	public void testLikeTVSeriesNonExistentTVSeries() throws TSException {
		tvsdb.addUser("fuckingMetalLover","Fantasy");
		assertThrows("Cannot add non existing series to favorites", TSException.class,
			        ()->tvsdb.likeTVSeries("fuckingMetalLover", "Game of Thrones"));

	}

	@Test
	public void testLikeTVSeriesAlreadyLikedTVSeries() throws TSException {
	    
		tvsdb.addUser("fuckingMetalLover","Fantasy");
	    tvsdb.likeTVSeries("fuckingMetalLover", "The Marvelous Mrs. Maisel");
		assertThrows("Cannot add twice the same series to favorites", TSException.class,
				    ()->tvsdb.likeTVSeries("fuckingMetalLover", "The Marvelous Mrs. Maisel"));

	}
	
	@Test
	public void testSuggestTVSeriesSuccess() throws TSException {
	    
		tvsdb.addUser("fuckingMetalLover","Fantasy");
		List<String> suggested=tvsdb.suggestTVSeries("fuckingMetalLover");
	    assertEquals("Wrong number of suggested series", 
	    			3, suggested.size());
	    assertTrue("Missing expected series in suggestions", 
	    		 suggested.contains("Daredevil"));
	    assertTrue("Missing expected series in suggestions", 
	    		 suggested.contains("The Punisher"));
	    assertTrue("Missing expected series in suggestions", 
	    		 suggested.contains("Loki"));
	    
	    tvsdb.likeTVSeries("fuckingMetalLover", "Daredevil");
	    suggested=tvsdb.suggestTVSeries("fuckingMetalLover");
	    assertEquals("Wrong number of suggested series", 
	    			2, suggested.size());
	    assertFalse("Favorite series should not be suggested", 
	    		 suggested.contains("Daredevil"));
	}
	
	@Test
	public void testSuggestTVSeriesEmpty() throws TSException {
		tvsdb.addUser("fuckingMetalLover","Thriller");
		List<String> suggested=tvsdb.suggestTVSeries("fuckingMetalLover");
	    assertEquals("Wrong number of suggestions", 
	    			1, suggested.size());
	    assertTrue("Expecting empyt suggestions", 
	    		 suggested.contains(""));
	}

	@Test
	public void testSuggestTVSeriesNonExistentUser() {
	    assertThrows("Cannot ask suggestions for non existing user", 
	       TSException.class, ()->tvsdb.suggestTVSeries("pippo91"));
	}
}