package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import project.ReviewException;
import project.ReviewServer;


public class TestR1_Groups {

	private ReviewServer mgr;
	
	@Before
	public void setUp() {
		mgr = new ReviewServer();
	}

	private final static String[] GROUPS = {"Group1","Bucaneers","Dolphins"};
	
	@Test
	public void testGroups() {
		mgr.addGroups(GROUPS);
		
		Collection<String> cats = mgr.getGroups();
		
		assertNotNull("No groups returned", cats);
		assertEquals("Wrong number of groups", GROUPS.length, cats.size());
		
		for(String s : GROUPS) 
			assertTrue("Missing " + s + " in groups", cats.contains(s));
		
	}

	@Test
	public void testGroupsNoDup() {
		mgr.addGroups(GROUPS);
		
		mgr.addGroups("Exams", GROUPS[0]);
		
		Collection<String> cats = mgr.getGroups();
		
		assertNotNull("No groups returned", cats);
		assertEquals("Wrong number of groups", GROUPS.length + 1, cats.size());
		
		for(String s : GROUPS) 
			assertTrue("Missing " + s + " in groups", cats.contains(s));
		
	}
	
	@Test
	public void testReview() throws ReviewException {
		mgr.addGroups(GROUPS);
		
		String reviewId = mgr.addReview("Project Thesis Meeting","Goal: check requierements and tests",GROUPS[2]);

		assertNotNull(reviewId);

		assertEquals("Project Thesis Meeting",mgr.getReviewTitle(reviewId));
		assertEquals("Goal: check requierements and tests",mgr.getReviewTopic(reviewId));
	}

	@Test
	public void testUniqueID() throws ReviewException {
		mgr.addGroups(GROUPS);
		
		String reviewId = mgr.addReview("Project Thesis Meeting","Goal: check requierements and tests",GROUPS[2]);		 
		assertNotNull(reviewId);

		String reviewId2 = mgr.addReview("Project Thesis2 Meeting","Goal: check requierements and tests",GROUPS[2]);
		assertNotNull(reviewId2);
		
		assertNotEquals(reviewId, reviewId2);
	}

	@Test
	public void testMeetingBadGroup() throws ReviewException {
		mgr.addGroups(GROUPS);
		
		String reviewId = mgr.addReview("Project Thesis Meeting","Goal: check requierements and tests",GROUPS[2]);		 
		assertNotNull(reviewId);

		assertThrows(ReviewException.class,
				 ()-> mgr.addReview("Fake task","Check error","Best"));
	}

	@Test
	public void testReviewGroups() throws ReviewException {
		mgr.addGroups(GROUPS);
		
		String reviewId = mgr.addReview("Project Thesis Meeting","Goal: check requierements and tests",GROUPS[2]);		 
		String reviewId2 = mgr.addReview("Project Thesis2 Meeting","Goal: check requierements and tests",GROUPS[2]);
		mgr.addReview("Initial review","Check process",GROUPS[0]);

		Collection<String> reviews = mgr.getReviews(GROUPS[2]);
		assertNotNull("Missing projects", reviews);
		assertEquals("Wrong number of reviews", 2, reviews.size());
		assertTrue("Missing reviews: " + reviewId, reviews.contains(reviewId));
		assertTrue("Missing reviews: " + reviewId, reviews.contains(reviewId2));
		
		Collection<String> reviews2 = mgr.getReviews(GROUPS[1]);
		assertEquals("There should be no reviews with group " + GROUPS[1], 0, reviews2.size());
	}

}
