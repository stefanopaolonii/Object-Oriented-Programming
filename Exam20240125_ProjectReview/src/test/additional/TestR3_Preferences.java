package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import project.ReviewException;
import project.ReviewServer;

public class TestR3_Preferences {

	private final static String[] GROUPS = {"Group1","Bucaneers","Dolphins"};
	private static final String DATE = "2023-06-28";
	private static final String EMAIL = "s987654@studenti.polito.it";
	private ReviewServer mgr;
	private String reviewId;
	
	
	@Before
	public void setUp() throws ReviewException {
		mgr = new ReviewServer();
		mgr.addGroups(GROUPS);
		
		reviewId = mgr.addReview("Project Thesis Meeting","Goal: check requierements and tests",GROUPS[2]);
		 
		mgr.addReview("Project Thesis2 Meeting","Goal: check requierements and tests",GROUPS[2]);
		mgr.addReview("Initial review","Check process",GROUPS[0]);
		
		mgr.addOption(reviewId, DATE, "10:00", "12:00");
		mgr.addOption(reviewId, DATE, "14:00", "16:00");
		mgr.addOption(reviewId, DATE, "16:00", "17:30");
		mgr.addOption(reviewId, "2023-07-04", "15:00", "18:15");
	}

	@Test
	public void testPreference() throws ReviewException {
		mgr.openPoll(reviewId);
		
		int n1  = mgr.selectPreference(EMAIL,"Giovanni","Bianchi",reviewId,DATE,"10:00-12:00");
		int n2 = mgr.selectPreference("rossi.lura@studenti.polito.it","Laura","Rossi",reviewId,DATE,"10:00-12:00");
		
		assertEquals("Wrong number of preferences", 1, n1);
		assertEquals("Wrong number of preferences", 2, n2);	
	}

	@Test
	public void testPreferenceBadCode() {
		mgr.openPoll(reviewId);
		assertThrows("Invalid review id for preference not detected",
				ReviewException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi","1NV4LI6",DATE,"10:00-12:00"));
		
	}

	@Test
	public void testPreferenceBadDate() {
		mgr.openPoll(reviewId);
		assertThrows("Wrong date for review not detected",
				ReviewException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi",reviewId, "2023-06-01","10:00-12:00"));
		
	}
	
	@Test
	public void testPreferenceBadSlot() {
		mgr.openPoll(reviewId);
		assertThrows("Wrong tine slot for review not detected",
				ReviewException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi",reviewId,DATE,"10:00-11:00"));
		
	}

	@Test
	public void testPreferenceNotOpen() {
		assertThrows("Wrong slot for review not detected",
				ReviewException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi",reviewId,DATE,"10:00-12:00"));
		
	}

	@Test
	public void testListPreferences() throws ReviewException {
		mgr.openPoll(reviewId);
		
		mgr.selectPreference(EMAIL,"Giovanni","Bianchi",reviewId,DATE,"10:00-12:00");
		mgr.selectPreference("rossil@ura.it","Laura","Rossi",reviewId,DATE,"10:00-12:00");

		Collection<String> preferences = mgr.listPreferences(reviewId);
		assertNotNull("Missing preferences", preferences);
		assertEquals("Wrong number of preferences", 2, preferences.size());
		assertTrue("Could not find preference by " + EMAIL, preferences.contains(DATE +"T10:00-12:00="+EMAIL));
	}
}
