package test.additional;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import meet.MeetException;
import meet.MeetServer;

public class TestR2_Options {

	private final static String[] CATEGORIES = {"Birthday","Business","Project"};

	private MeetServer mgr;
	private String meetId;
	
	@Before
	public void setUp() throws MeetException {
		mgr = new MeetServer();
		mgr.addCategories(CATEGORIES);
		
		meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector","Project");
	}

	@Test
	public void testSlots() throws MeetException {
		String date = "2023-06-28";

		double hours = mgr.addOption(meetId, date, "10:00", "12:00");
		assertEquals(2,hours,0.001);
		
		double d1 = mgr.addOption(meetId, date, "14:00", "16:00");
		double d2 = mgr.addOption(meetId, date, "16:00", "17:30");
		double d3 = mgr.addOption(meetId, "2023-07-04", "15:00", "18:15");

		assertEquals("Wrong number of slots", 2, d1, 0.001);

		assertEquals("Wrong number of slots", 1.5, d2, 0.001);

		assertEquals("Wrong number of slots", 3.25, d3, 0.001);

	}

	@Test
	public void testSlotsOverlap() throws MeetException {
		String date = "2023-06-28";

		mgr.addOption(meetId, date, "10:00", "12:00");
		
		assertThrows("Overlapping options are not allowed", MeetException.class,
					 ()-> mgr.addOption(meetId, date, "11:00", "13:00"));

		assertThrows("Overlapping options are not allowed", MeetException.class,
				()-> mgr.addOption(meetId, date, "09:00", "11:00"));

		assertThrows("Overlapping options are not allowed", MeetException.class,
				()-> mgr.addOption(meetId, date, "10:30", "11:30"));

		assertThrows("Overlapping options are not allowed", MeetException.class,
				()-> mgr.addOption(meetId, date, "08:30", "14:30"));
	}

	@Test
	public void testSlotsWrongId() throws MeetException {
		assertThrows("Overlapping options are not allowed", MeetException.class,
					 ()-> mgr.addOption("NONEXISTENT", "2023-06-28", "11:00", "13:00"));
	}

	@Test
	public void testDailySlots() throws MeetException {
		String date = "2023-06-28";

		double hours = mgr.addOption(meetId, date, "10:00", "12:00");
		assertEquals(2,hours,0.001);
		
		mgr.addOption(meetId, date, "14:00", "16:00");
		mgr.addOption(meetId, date, "16:00", "17:30");
		mgr.addOption(meetId, "2023-07-04", "15:00", "18:15");
		
		Map<String,List<String>> slots = mgr.showSlots(meetId);

		assertNotNull("Missing meeting slots", slots);
		assertEquals("Wrong number of slots available for " + meetId,
					 2, slots.size());
		
		assertTrue("Missing date " + date + " in available slots", slots.containsKey(date));
		assertEquals("Wrong number of slots available on " + date, 3, slots.get(date).size());
		assertTrue("Missing first slot", slots.get(date).contains("10:00-12:00"));
	}

}
