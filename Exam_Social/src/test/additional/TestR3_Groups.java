package test.additional;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import social.*;

import static org.junit.Assert.*;

public class TestR3_Groups {
	private Social m;

	@Before
	public void setUp() throws PersonExistsException, NoSuchCodeException {
		m = new Social();
		m.addPerson("ABCD", "Ricardo", "Kaka");
		m.addPerson("XYZ", "Alex", "Pato");
		m.addPerson("GGG", "Gennaro", "Gattuso");
		m.addFriendship("ABCD", "XYZ");
		m.addFriendship("ABCD", "GGG");
	}

	@Test
	public void testR31Group() {

		m.addGroup("milan");

		Collection<String> s = m.listOfGroups();
		assertTrue(s.contains("milan"));

		m.addGroup("juve");
		s = m.listOfGroups();
		assertTrue(s.contains("milan"));
		assertTrue(s.contains("juve"));
	}

	@Test
	public void testR32NoGroups() {
		Collection<String> s = m.listOfGroups();
		assertEquals(0, s.size());
	}

	@Test
	public void testR33GroupListing() throws NoSuchCodeException {
		m.addGroup("milan");
		m.addGroup("brasile");
		m.addGroup("poli");

		m.addPersonToGroup("XYZ", "brasile");
		m.addPersonToGroup("ABCD", "brasile");
		m.addPersonToGroup("ABCD", "milan");
		m.addPersonToGroup("GGG", "milan");
		Collection<String> s = m.listOfPeopleInGroup("brasile");

		assertTrue(s.contains("XYZ"));
		assertTrue(s.contains("ABCD"));

		s = m.listOfPeopleInGroup("milan");
		assertTrue(s.contains("ABCD"));
		assertTrue(s.contains("GGG"));
	}

	@Test
	public void testR3_MissingPerson() {
		m.addGroup("milan");
		assertThrows("When adding an unknown person to a group an exception is expected",
				NoSuchCodeException.class,
				() -> m.addPersonToGroup("NONEXISTENT", "brasil"));
	}

	@Test
	public void testR3_MissingGroup() {
		m.addGroup("milan");
		assertThrows("When adding to an unknown group an exception is expected",
				NoSuchCodeException.class,
				() -> m.addPersonToGroup("ABCD", "NO_GROUP"));
	}

	@Test
	public void testR3_EmptyGroup() {
		m.addGroup("milan");
		Collection<String> s = m.listOfPeopleInGroup("milan");
		assertNotNull("Missing collection of members for empty group", s);
		assertEquals("Empty group should have no members", 0 ,s.size());
	}

}