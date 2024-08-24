package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import meet.MeetException;
import meet.MeetServer;

public class TestR1_Categories {

	private MeetServer mgr;
	
	@Before
	public void setUp() {
		mgr = new MeetServer();
	}

	private final static String[] sp = {"Birthday","Business","Project"};
	
	@Test
	public void testCategories() {
		mgr.addCategories(sp);
		
		Collection<String> cats = mgr.getCategories();
		
		assertNotNull("No specialities returned", cats);
		assertEquals("Wrong number of specialities", sp.length, cats.size());
		
		for(String s : sp) 
			assertTrue("Missing " + s + " in specialities", cats.contains(s));
		
	}

	@Test
	public void testCategoriesNoDup() {
		mgr.addCategories(sp);
		
		mgr.addCategories("Exams", sp[0]);
		
		Collection<String> cats = mgr.getCategories();
		
		assertNotNull("No specialities returned", cats);
		assertEquals("Wrong number of specialities", sp.length + 1, cats.size());
		
		for(String s : sp) 
			assertTrue("Missing " + s + " in specialities", cats.contains(s));
		
	}
	
	@Test
	public void testMeeting() throws MeetException {
		mgr.addCategories("Project");
		
		String meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector","Project");
		assertNotNull(meetId);

		assertEquals("Project Poli Meeting",mgr.getMeetingTitle(meetId));
		assertEquals("Goal: find new rector",mgr.getMeetingTopic(meetId));
	}

	@Test
	public void testUniqueID() throws MeetException {
		mgr.addCategories("Project");
		
		String meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector","Project");
		assertNotNull(meetId);

		String meetId2 = mgr.addMeeting("Project Poli Meeting 2","Goal: find new rector 2","Project");
		assertNotNull(meetId2);
		
		assertNotEquals(meetId, meetId2);
	}

	@Test
	public void testMeetingBadCat() throws MeetException {
		mgr.addCategories("Project");
		
		String meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector","Project");
		assertNotNull(meetId);

		assertThrows(MeetException.class,
				 ()-> mgr.addMeeting("OOP Exam","Next session","Exams"));
	}

	@Test
	public void testMeetCategories() throws MeetException {
		mgr.addCategories(sp);
		
		String meetId = mgr.addMeeting("Project Poli Meeting","Goal: find new rector",sp[2]);
		String prjId = mgr.addMeeting("OOP Program","New program for OOP",sp[2]);
		mgr.addMeeting("Arnold's Birthday Party","Terminator dress code",sp[0]);

		Collection<String> projects = mgr.getMeetings(sp[2]);
		assertNotNull("Missing projects", projects);
		assertEquals("Wrong number of projects", 2, projects.size());
		assertTrue("Missing project: " + meetId, projects.contains(meetId));
		assertTrue("Missing project: " + meetId, projects.contains(prjId));
		
		assertEquals("There should be no business meetings", 0, mgr.getMeetings(sp[1]).size());
	}

}
