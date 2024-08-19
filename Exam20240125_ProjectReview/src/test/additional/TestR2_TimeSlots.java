package test.additional;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import project.ReviewException;
import project.ReviewServer;

public class TestR2_TimeSlots {

	private final static String[] GROUPS = {"Group1","Bucaneers","Dolphins"};

	private ReviewServer mgr;
	private String reviewId;
	
	@Before
	public void setUp() throws ReviewException {
		mgr = new ReviewServer();
		mgr.addGroups(GROUPS);
		
		reviewId = mgr.addReview("Project Thesis Meeting","Goal: check requierements and tests",GROUPS[2]);
	}

	@Test
	public void testSlots() throws ReviewException {
		String date = "2023-06-28";

		double hours = mgr.addOption(reviewId, date, "10:00", "12:00");
		assertEquals(2,hours,0.001);
		
		double d1 = mgr.addOption(reviewId, date, "14:00", "16:00");
		double d2 = mgr.addOption(reviewId, date, "16:00", "17:30");
		double d3 = mgr.addOption(reviewId, "2023-07-04", "15:00", "18:15");

		assertEquals("Wrong number of slots", 2, d1, 0.001);

		assertEquals("Wrong number of slots", 1.5, d2, 0.001);

		assertEquals("Wrong number of slots", 3.25, d3, 0.001);

	}

	@Test
	public void testSlotsOverlap() throws ReviewException {
		String date = "2023-06-28";

		mgr.addOption(reviewId, date, "10:00", "12:00");
		
		assertThrows("Overlapping options are not allowed", ReviewException.class,
					 ()-> mgr.addOption(reviewId, date, "11:00", "13:00"));

		assertThrows("Overlapping options are not allowed", ReviewException.class,
				()-> mgr.addOption(reviewId, date, "09:00", "11:00"));

		assertThrows("Overlapping options are not allowed", ReviewException.class,
				()-> mgr.addOption(reviewId, date, "10:30", "11:30"));

		assertThrows("Overlapping options are not allowed", ReviewException.class,
				()-> mgr.addOption(reviewId, date, "08:30", "14:30"));
	}

	@Test
	public void testSlotsWrongId() throws ReviewException {
		assertThrows("Overlapping options are not allowed", ReviewException.class,
					 ()-> mgr.addOption("NONEXISTENT", "2023-06-28", "11:00", "13:00"));
	}

	@Test
	public void testDailySlots() throws ReviewException {
		String date = "2023-06-28";

		double hours = mgr.addOption(reviewId, date, "10:00", "12:00");
		assertEquals(2,hours,0.001);
		
		mgr.addOption(reviewId, date, "14:00", "16:00");
		mgr.addOption(reviewId, date, "16:00", "17:30");
		mgr.addOption(reviewId, "2023-07-04", "15:00", "18:15");
		
		Map<String,List<String>> slots = mgr.showSlots(reviewId);

		assertNotNull("Missing review time slots", slots);
		assertEquals("Wrong number of time slots available for " + reviewId,
					 2, slots.size());
		
		assertTrue("Missing date " + date + " in available slots", slots.containsKey(date));
		assertEquals("Wrong number of slots available on " + date, 3, slots.get(date).size());
		assertTrue("Missing first slot", slots.get(date).contains("10:00-12:00"));
	}
}
