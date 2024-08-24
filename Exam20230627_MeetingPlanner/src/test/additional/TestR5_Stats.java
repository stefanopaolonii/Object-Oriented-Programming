package test.additional;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import meet.MeetException;
import meet.MeetServer;

public class TestR5_Stats {

	private final static String[] CATEGORIES = {"Birthday","Business","Project"};
	private static final String DATE = "2023-06-28";
	private static final String EMAIL = "gwhites@email.com";
	private MeetServer mgr;
	private String meetId;
	private String prjId;
	
	@Before
	public void setUp() throws MeetException {
		mgr = new MeetServer();
		mgr.addCategories(CATEGORIES);
		
		meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector",CATEGORIES[2]);
		 
		prjId = mgr.addMeeting("OOP Program","New program for OOP",CATEGORIES[2]);
		mgr.addMeeting("Arnold's Birthday Party","Terminator dress code",CATEGORIES[0]);
		
		mgr.addOption(meetId, DATE, "10:00", "12:00");
		mgr.addOption(meetId, DATE, "14:00", "16:00");
		mgr.addOption(meetId, DATE, "16:00", "17:30");
		mgr.addOption(meetId, "2023-07-04", "15:00", "18:15");
		
		mgr.addOption(prjId, "2023-07-30", "21:00", "23:30");
		
		mgr.openPoll(meetId);
		
		mgr.selectPreference(EMAIL,"Giovanni","Bianchi",meetId,DATE,"10:00-12:00");
		mgr.selectPreference("rossil@ura.it","Laura","Rossi",meetId,DATE,"10:00-12:00");
		
		mgr.openPoll(meetId);
		mgr.openPoll(prjId);
	}

	@Test
	public void testPreferences() {

		Map<String,List<String>> prefs = mgr.meetingPreferences(meetId);
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
		assertEquals(Integer.valueOf(2), prefCount.get(meetId));

	}

}
