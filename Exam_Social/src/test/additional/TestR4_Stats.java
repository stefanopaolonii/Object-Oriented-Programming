package test.additional;

import org.junit.Before;
import org.junit.Test;
import social.*;

import static org.junit.Assert.*;

public class TestR4_Stats {
	private Social m;

	@Before
	public void setUp() throws PersonExistsException, NoSuchCodeException {
		m = new Social();
		m.addPerson("ABCD", "Ricardo", "Kaka");
		m.addPerson("XYZ", "Alex", "Pato");
		m.addPerson("GGG", "Gennaro", "Gattuso");
		m.addPerson("PPP", "Paolo", "Maldini");
		m.addPerson("AAA", "Andrea", "Pirlo");
		m.addFriendship("ABCD", "XYZ");
		m.addFriendship("ABCD", "GGG");
		m.addFriendship("PPP", "GGG");
		m.addFriendship("AAA", "GGG");

		m.addGroup("milan");
		m.addGroup("brasile");
		m.addGroup("poli");

		m.addPersonToGroup("XYZ", "brasile");
		m.addPersonToGroup("ABCD", "brasile");
		m.addPersonToGroup("GGG", "milan");
		m.addPersonToGroup("XYZ", "milan");
		m.addPersonToGroup("PPP", "milan");
	}

	@Test
	public void testR41PersonWithLargestNumberOfFriends() {
		String s = m.personWithLargestNumberOfFriends();
		assertEquals("GGG", s);
	}

	@Test
	public void testR42PersonWithMostFriendsOfFriends() throws PersonExistsException, NoSuchCodeException {
		m.addPerson("SSS", "Andrey", "Sheva");
		m.addFriendship("SSS", "XYZ");
		String s = m.personWithMostFriendsOfFriends();
		assertEquals(s, "ABCD"); //kaka = 3
	}

	@Test
	public void testR43PopularGroup() {
		String s = m.largestGroup();
		assertEquals(s, "milan");
	}

	@Test
	public void testR44PersonInLargestNumberOfGroups() {
		String s = m.personInLargestNumberOfGroups();
		assertEquals(s, "XYZ"); //pato 2
	}

}