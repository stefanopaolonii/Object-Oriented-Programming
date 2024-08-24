package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.junit.Test;
import meet.MeetException;
import meet.MeetServer;

public class TestExample {

	@Test
	public void testAll() throws MeetException {
		MeetServer mgr = new MeetServer();
		
		// R1 : categories and meetings
		
		mgr.addCategories("Birthday","Business","Project");
		
		Collection<String> cats = mgr.getCategories();
		assertNotNull(cats);
		assertEquals(3, cats.size());
		assertTrue(cats.contains("Project"));
		
		String meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector","Project");
		mgr.addMeeting("OOP Program","New program for OOP","Project");
		String arnold = mgr.addMeeting("Arnold's Birthday Party","Terminator dress code","Birthday");
		
		assertThrows(MeetException.class,
					 ()-> mgr.addMeeting("OOP Exam","Next session","Exams"));

		Collection<String> projects = mgr.getMeetings("Project");
		assertNotNull(projects);
		assertEquals(2,projects.size());
		
		assertEquals("Project Poli Meeting",mgr.getMeetingTitle(meetId));
		assertEquals("Goal: find new rector",mgr.getMeetingTopic(meetId));
		
		// R2 : meeting slot options
		
		// define slot on 2023-06-28 from 10:00 to 12:00
		String date = "2023-06-28";
		double hours = mgr.addOption(meetId, date, "10:00", "12:00");
		assertEquals(2,hours,0.001);
		
		mgr.addOption(meetId, date, "14:00", "16:00");
		mgr.addOption(meetId, date, "16:00", "17:30");
		mgr.addOption(meetId, "2023-07-04", "15:00", "17:00");

		mgr.addOption(arnold, "2023-07-30", "20:00", "22:00");
		
		assertThrows("Overlapping slots should fail", MeetException.class,
				 ()-> mgr.addOption(meetId, date, "11:00", "13:00"));

		Map<String,List<String>> slots = mgr.showSlots(meetId);
		assertNotNull(slots);
		assertEquals(2,slots.size());
		assertTrue(slots.containsKey(date));
		assertEquals(3, slots.get(date).size());
		assertTrue(slots.get(date).contains("10:00-12:00"));
		
		
		// R3 : express preferences
		
		mgr.openPoll(meetId);
		
		String email = "gwhites@email.com";
		int n1  = mgr.selectPreference(email,"Giovanni","Bianchi",meetId,date,"10:00-12:00");
		int n2 = mgr.selectPreference("rossil@ura.it","Laura","Rossi",meetId,date,"10:00-12:00");
		mgr.selectPreference("eva@kant.eu","Eva","Kant",meetId,"2023-07-04","15:00-17:00");
		
		assertEquals(1, n1);
		assertEquals(2, n2);
		
		assertThrows("Not enabled", MeetException.class,
					 () -> mgr.selectPreference("sarah@sky.net","Sarah","Connor",arnold,"2023-07-30","20:00-22:00") );
		
		Collection<String> preferences = mgr.listPreferences(meetId);
		assertNotNull(preferences);
		assertEquals(3,preferences.size());
		assertTrue("Missing slot in " + preferences, preferences.contains(date+"T10:00-12:00="+email));
		
		
		// R4 : close meeting poll
		
		Collection<String> bestOptions = mgr.closePoll(meetId);
		
		
		assertNotNull(bestOptions);
		assertEquals(1, bestOptions.size());
		assertEquals(date+"T10:00-12:00=2", bestOptions.iterator().next());
		
		
		// R5 : stats
		
		Map<String,List<String>> prefs = mgr.meetingPreferences(meetId);
		assertNotNull(prefs);
		assertEquals(2, prefs.size());
		assertEquals(1, prefs.get(date).size());
		assertEquals("10:00-12:00=2", prefs.get(date).get(0));
		
		
		Map<String,Integer> prefCount = mgr.preferenceCount();
		assertNotNull(prefCount);
		assertEquals(3, prefCount.size());
		assertEquals(Integer.valueOf(3), prefCount.get(meetId));
	}
		
}