package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import meet.MeetException;
import meet.MeetServer;

public class TestR3_Appointments {

	private final static String[] CATEGORIES = {"Birthday","Business","Project"};
	private static final String DATE = "2023-06-28";
	private static final String EMAIL = "gwhites@email.com";
	private MeetServer mgr;
	private String meetId;
	
	
	@Before
	public void setUp() throws MeetException {
		mgr = new MeetServer();
		mgr.addCategories(CATEGORIES);
		
		meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector",CATEGORIES[2]);
		String prjId = mgr.addMeeting("OOP Program","New program for OOP",CATEGORIES[2]);
		mgr.addMeeting("Arnold's Birthday Party","Terminator dress code",CATEGORIES[0]);
		
		mgr.addOption(meetId, DATE, "10:00", "12:00");
		mgr.addOption(meetId, DATE, "14:00", "16:00");
		mgr.addOption(meetId, DATE, "16:00", "17:30");
		mgr.addOption(meetId, "2023-07-04", "15:00", "18:15");
	}

	@Test
	public void testPreference() throws MeetException {
		mgr.openPoll(meetId);
		
		int n1  = mgr.selectPreference(EMAIL,"Giovanni","Bianchi",meetId,DATE,"10:00-12:00");
		int n2 = mgr.selectPreference("rossil@ura.it","Laura","Rossi",meetId,DATE,"10:00-12:00");
		
		assertEquals("Wrong number of preferences", 1, n1);
		assertEquals("Wrong number of preferences", 2, n2);	
	}

	@Test
	public void testPreferenceBadCode() {
		mgr.openPoll(meetId);
		assertThrows("Invalid meeting id for preference not detected",
				MeetException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi","1NV4LI6",DATE,"10:00-12:00"));
		
	}

	@Test
	public void testPreferenceBadDate() {
		mgr.openPoll(meetId);
		assertThrows("Wrong date for appointment not detected",
				MeetException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi",meetId, "2023-06-01","10:00-12:00"));
		
	}
	
	@Test
	public void testPreferenceBadSlot() {
		mgr.openPoll(meetId);
		assertThrows("Wrong slot for appointment not detected",
				MeetException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi",meetId,DATE,"10:00-11:00"));
		
	}

	@Test
	public void testPreferenceNotOpen() {
		assertThrows("Wrong slot for appointment not detected",
				MeetException.class,
				()->mgr.selectPreference(EMAIL,"Giovanni","Bianchi",meetId,DATE,"10:00-12:00"));
		
	}

	@Test
	public void testListAppointments() throws MeetException {
		mgr.openPoll(meetId);
		
		mgr.selectPreference(EMAIL,"Giovanni","Bianchi",meetId,DATE,"10:00-12:00");
		mgr.selectPreference("rossil@ura.it","Laura","Rossi",meetId,DATE,"10:00-12:00");

		Collection<String> preferences = mgr.listPreferences(meetId);
		assertNotNull("Missing preferences", preferences);
		assertEquals("Wrong number of preferences", 2, preferences.size());
		assertTrue("Could not find preference by " + EMAIL, preferences.contains(DATE +"T10:00-12:00="+EMAIL));

	}

}
