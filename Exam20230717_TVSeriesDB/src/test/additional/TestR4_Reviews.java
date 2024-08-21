package test.additional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tvseriesdb.TSException;

import tvseriesdb.TVSeriesDB;

public class TestR4_Reviews {

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
		tvsdb.addUser("fuckingMetalLover","Fantasy");
		tvsdb.addUser("__roby","Comedy");
		tvsdb.addUser("gianlu","Drama");
	}
    
	@Test
	public void testAddReviewSuccess() throws TSException {
	    
		tvsdb.addReview("fuckingMetalLover", "The Marvelous Mrs. Maisel", 8);
		tvsdb.addReview("fuckingMetalLover", "The Bear", 4);
		tvsdb.addReview("gianlu", "The Marvelous Mrs. Maisel", 10);

	    assertEquals("Wrong rating average", 9.0,
	    		      tvsdb.addReview("__roby", "The Marvelous Mrs. Maisel", 9), 0); 
	}

	@Test
	public void testAddReviewNonExistentUser() throws TSException {
	    assertThrows("Non existing user cannot add a review", TSException.class,
	    		    ()->tvsdb.addReview("pippo91", "The Bear", 10));
	    assertEquals("Wrong review average", 4.0, tvsdb.addReview("fuckingMetalLover", "The Bear", 4), 0.0);
	}

	@Test
	public void testAddReviewNonExistentTVSeries() {
		assertThrows("Cannot add review to non existing series", TSException.class,
					()->tvsdb.addReview("__roby", "Game of Thrones", 10));
	}

	@Test
	public void testAddReviewInvalidScore() {
		assertThrows("Rating must be in [0;10]", TSException.class,
					()->tvsdb.addReview("__roby",  "The Bear", 11));
		assertThrows("Rating must be in [0;10]", TSException.class,
					()->tvsdb.addReview("__roby",  "The Bear", -2));
	}
	
	@Test
	public void testAverageRatingSuccess() throws TSException {
		tvsdb.addReview("fuckingMetalLover", "The Marvelous Mrs. Maisel", 8);
		tvsdb.addReview("fuckingMetalLover", "The Bear", 4);
		tvsdb.likeTVSeries("fuckingMetalLover", "The Marvelous Mrs. Maisel");
		tvsdb.likeTVSeries("fuckingMetalLover", "The Bear");
		tvsdb.likeTVSeries("fuckingMetalLover", "Loki");
	    assertEquals("Wrong average rating for favorite series of user",
	    			4, tvsdb.averageRating("fuckingMetalLover"), 0.0); // Using delta for double comparison
	}

	@Test
	public void testAverageRatingNonExistentUser() {
	    assertThrows("Cannot compute average for non existing user", 
	       TSException.class, ()->tvsdb.averageRating("pippo91"));
	}
}