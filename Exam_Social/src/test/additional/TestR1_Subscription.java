package test.additional;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import social.*;

public class TestR1_Subscription {

	private Social m;

	@Before
	public void setUp(){
		m = new Social();
	}

	@Test
	public void testR11AddPerson() throws PersonExistsException, NoSuchCodeException {
		m.addPerson("ABCD", "Ricardo", "Kaka");
		String s = m.getPerson("ABCD");
		assertEquals("Wrong person information","ABCD Ricardo Kaka", s);
	}

	@Test
	public void testR13PersonDoesNotExist() throws PersonExistsException {
		m.addPerson("ABCD", "Ricardo", "Kaka");
		assertThrows("Non existing person should throw exception",NoSuchCodeException.class,
				()->m.getPerson("ZZ"));
	}

	@Test
	public void testR14PersonExists() throws PersonExistsException {
		m.addPerson("ABCD", "Ricardo", "Kaka");
		assertThrows("Duplicate code should throw exception", PersonExistsException.class,
					 ()->m.addPerson("ABCD", "Alex", "Pato"));
	}
}
