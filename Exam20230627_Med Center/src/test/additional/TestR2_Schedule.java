package test.additional;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import med.MedException;
import med.MedManager;

public class TestR2_Schedule {

	private static final String DOC_ID = "FD845";
	private static final String SPECIALITY = "Pediatry";
	private MedManager mgr;
	
	@Before
	public void setUp() throws MedException {
		mgr = new MedManager();
		mgr.addSpecialities(SPECIALITY);
		
		mgr.addDoctor(DOC_ID,"Mary","White",SPECIALITY);

	}

	@Test
	public void testSlots() {
		int numSlots_20 = mgr.addDailySchedule(DOC_ID, "2023-06-28", "10:00", "12:00", 20);

		int numSlots_30 = mgr.addDailySchedule(DOC_ID, "2023-06-29", "10:00", "12:00", 30);

		int numSlots_10 = mgr.addDailySchedule(DOC_ID, "2023-06-30", "10:00", "12:00", 10);
		
		assertEquals("Wrong number of slots", 6, numSlots_20);

		assertEquals("Wrong number of slots", 4, numSlots_30);

		assertEquals("Wrong number of slots", 12, numSlots_10);

	}

	@Test
	public void testSlots2() {
		int numSlots = mgr.addDailySchedule(DOC_ID, "2023-06-28", "08:30", "10:00", 30);

		assertEquals("Wrong number of slots", 3, numSlots);
	}

	@Test
	public void testDailySlots() {
		String date = "2023-06-28";
		mgr.addDailySchedule(DOC_ID, date, "10:30", "13:00", 30);
		
		Map<String,List<String>> slots = mgr.findSlots(date, SPECIALITY);

		assertNotNull("Missing daily slots", slots);
		assertEquals("Wrong number of "+ SPECIALITY + " specialists available on " + date,
					 1,slots.size());
		
		assertTrue("Missing doctor " + DOC_ID + " in available slots", slots.containsKey(DOC_ID));
		assertEquals("Wrong number of slots available for " + DOC_ID, 5, slots.get(DOC_ID).size());
		assertTrue("Missing first slot", slots.get(DOC_ID).contains("10:30-11:00"));
	}

	@Test
	public void testDailySlots2() throws MedException {
		String date = "2023-06-28";
		mgr.addDailySchedule(DOC_ID, date, "10:30", "13:00", 30);
		
		mgr.addDoctor("ABC98", "Mick", "Jag", SPECIALITY);
		mgr.addDailySchedule("ABC98", date, "08:00", "11:00", 20);
		
		Map<String,List<String>> slots = mgr.findSlots(date, SPECIALITY);

		assertNotNull("Missing daily slots", slots);
		assertEquals("Wrong number of "+ SPECIALITY + " specialists available on " + date,
					 2, slots.size());
		
		assertTrue("Missing doctor " + DOC_ID + " in available slots", slots.containsKey(DOC_ID));
		assertTrue("Missing doctor ABC98 in available slots", slots.containsKey("ABC98"));
	}

}
