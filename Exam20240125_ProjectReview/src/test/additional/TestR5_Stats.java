package test.additional;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import project.ReviewException;
import project.ReviewServer;

public class TestR5_Stats {

	private final static String[] GROUPS = {"Group1","Bucaneers","Dolphins"};
	private static final String DATE = "2023-06-28";
	private static final String EMAIL = "s987654@studenti.polito.it";
	private ReviewServer mgr;
	private String reviewId;
	private String reviewId2;
	
	@Before
	public void setUp() throws ReviewException {
		mgr = new ReviewServer();
		mgr.addGroups(GROUPS);
		
		reviewId = mgr.addReview("Project Thesis Meeting","Goal: check requierements and tests",GROUPS[2]);
		 
		reviewId2 = mgr.addReview("Project Thesis2 Meeting","Goal: check requierements and tests",GROUPS[2]);
		mgr.addReview("Initial review","Check process",GROUPS[0]);

		mgr.addOption(reviewId, DATE, "10:00", "12:00");
		mgr.addOption(reviewId, DATE, "14:00", "16:00");
		mgr.addOption(reviewId, DATE, "16:00", "17:30");
		mgr.addOption(reviewId, "2023-07-04", "15:00", "18:15");
		
		mgr.addOption(reviewId2, "2023-07-30", "21:00", "23:30");
		
		mgr.openPoll(reviewId);
		
		mgr.selectPreference(EMAIL,"Giovanni","Bianchi",reviewId,DATE,"10:00-12:00");
		mgr.selectPreference("rossi.laura@studenti.polito.it","Laura","Rossi",reviewId,DATE,"10:00-12:00");
		
		mgr.openPoll(reviewId);
		mgr.openPoll(reviewId2);
	}

	@Test
	public void testPreferences() {
		Map<String,List<String>> prefs = mgr.reviewPreferences(reviewId);
		assertNotNull(prefs);
		assertEquals(2, prefs.size());
		assertEquals(1, prefs.get(DATE).size());
		assertEquals("10:00-12:00=2", prefs.get(DATE).get(0));
	}

	@Test
	public void testPreferenceCount() {
		Map<String,Integer> prefCount = mgr.preferenceCount();
		assertNotNull(prefCount);
		assertEquals(3, prefCount.size());
		assertEquals(Integer.valueOf(2), prefCount.get(reviewId));
	}

}
