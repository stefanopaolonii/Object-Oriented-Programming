package test.additional;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import social.*;

import static org.junit.Assert.*;

public class TestR2_Friends {
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
	public void testR21Friendship() throws NoSuchCodeException {
		Collection<String> friends = m.listOfFriends("ABCD");

		assertTrue(friends.contains("XYZ"));
		assertTrue(friends.contains("GGG"));
	}

	@Test
	public void testR22TwoWayFriendship() throws NoSuchCodeException {
		Collection<String> friends = m.listOfFriends("ABCD");

		assertTrue(friends.contains("XYZ"));
		assertTrue(friends.contains("GGG"));
		Collection<String> friends2 = m.listOfFriends("GGG");
		assertTrue(friends2.contains("ABCD"));
		Collection<String> friends3 = m.listOfFriends("XYZ");
		assertTrue(friends3.contains("ABCD"));
	}

	@Test
	public void testR23FriendshipNotExistingCode() {
		assertThrows("Expecting an exception for friendship with non existing code",
				NoSuchCodeException.class,
				()->m.addFriendship("ABCD", "AAA"));
	}

	@Test
	public void testR24FriendshipNotExistingCode2() {
		assertThrows("Expecting an exception for friendship with non existing code",
				NoSuchCodeException.class,
				()->m.listOfFriends("UUUU")) ;
	}

	@Test
	public void testR25FriendshipNull() throws PersonExistsException, NoSuchCodeException {
		m.addPerson("KABI", "Khaby", "Lame");
		Collection<String> friends = m.listOfFriends("KABI");

		assertEquals("Expecting no friends for newly created person",
					 0, friends.size());
	}

	@Test
	public void testR26FriendshipSecondLevel() throws PersonExistsException, NoSuchCodeException {
		//Pato <-> kaka <-> gattuso <-> (Pirlo,maldini)

		m.addPerson("PPP", "Paolo", "Maldini");
		m.addPerson("AAA", "Andrea", "Pirlo");
		m.addFriendship("PPP", "GGG");
		m.addFriendship("AAA", "GGG");

		Collection<String> friendsKaka = m.friendsOfFriends("ABCD");

		assertTrue(friendsKaka.contains("PPP"));
		assertTrue(friendsKaka.contains("AAA"));
		Collection<String> friendsPato = m.friendsOfFriends("XYZ");
		assertTrue(friendsPato.contains("GGG"));
		Collection<String> friendsPirlo = m.friendsOfFriends("AAA");
		assertTrue(friendsPirlo.contains("ABCD"));
		assertTrue(friendsPirlo.contains("PPP"));
	}

	@Test
	public void testR27FriendshipNotExisting2Level() {

		assertThrows("Expecting an exception for friend of friend with non existing code",
				NoSuchCodeException.class,
				()->m.friendsOfFriends("UXX")) ;

	}

	@Test
	public void testR28Friendship2LevelNull() throws PersonExistsException, NoSuchCodeException {

		m.addPerson("KABI", "Khaby", "Lame");
		Collection<String> friendsKabi = m.friendsOfFriends("KABI");
		assertEquals(0,friendsKabi.size());
	}

	@Test
	public void testR29Friendship2LevelNoRepetitions() throws PersonExistsException, NoSuchCodeException {

		m.addPerson("PPP", "Paolo", "Maldini");
		m.addPerson("AAA", "Andrea", "Pirlo");
		m.addFriendship("PPP", "GGG");
		m.addFriendship("AAA", "GGG");
		m.addFriendship("ABCD", "PPP");
		// AAA
		//  |
		// GGG -- ABCD -- XYZ
		//  |   /
		// PPP -
		Collection<String> friendsKaka = m.friendsOfFriendsNoRepetition("ABCD");

		assertTrue(friendsKaka.contains("AAA"));
		assertEquals(3, friendsKaka.size() );
	}

	@Test
	public void testR291Friendship2LevelNotMyself() throws PersonExistsException, NoSuchCodeException {
		//(maldini,Pato) <-> kaka <-> gattuso <-> (Pirlo,maldini)

		m.addPerson("PPP", "Paolo", "Maldini");
		m.addPerson("AAA", "Andrea", "Pirlo");
		m.addFriendship("PPP", "GGG");
		m.addFriendship("AAA", "GGG");
		m.addFriendship("ABCD", "PPP"); //add friendship kaka maldini
		Collection<String> friendsKaka = m.friendsOfFriendsNoRepetition("ABCD");
		assertFalse(friendsKaka.contains("ABCD")); // is the starting point
	}

	@Test
	public void testR2_FriendSecondNotExisting() {
		assertThrows("Expecting an exception for friend of friend with non existing code",
				NoSuchCodeException.class,
				()->m.friendsOfFriendsNoRepetition("UXX"));
	}
}