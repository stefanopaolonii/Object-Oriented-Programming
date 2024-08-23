package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import train.TrainException;
import train.TrainManager;

public class TestR1_Cars {

	private TrainManager mgr;
	private final static String[] CLASSES = {"Normal","Luxury","First"};
	
	@Before
	public void setUp() {
		mgr = new TrainManager();
	}

	
	@Test
	public void testClasses() {
		mgr.addClasses(CLASSES);
		
		Collection<String> classes = mgr.getClasses();
		
		assertNotNull("No classes returned", classes);
		assertEquals("Wrong number of classes:", CLASSES.length, classes.size());
		
		for(String s : CLASSES) 
			assertTrue("Missing " + s + " in classes", classes.contains(s));
	}

	@Test
	public void testClassesNoDup() {
		mgr.addClasses(CLASSES);
		
		mgr.addClasses("Imperial", CLASSES[0]);
		
		Collection<String> classes = mgr.getClasses();
		
		assertNotNull("No classes returned", classes);
		assertEquals("Wrong number of classes:", CLASSES.length + 1, classes.size());		
	}
	
	@Test
	public void testCars() throws TrainException {
		mgr.addClasses("Normal");
		
		String code = "XD345";
		int n = mgr.addCar(code,20,'D',"Normal");
		assertEquals("Wrong number of seats for new car", 80, n);

		assertEquals("Wrong number of seats for car", 80,mgr.getNumSeats(code));
	}

	@Test
	public void testCarsDupCode() throws TrainException {
		mgr.addClasses("Normal");
		
		String code = "XD345";
		mgr.addCar(code,20,'D',"Normal");

		assertThrows("Duplicate car id not detected",
				TrainException.class,
				()-> mgr.addCar(code,15,'D',"Normal"));
	}

	@Test
	public void testCarsBadClass() throws TrainException {
		mgr.addClasses("Normal");
		
		String code = "XD345";
		mgr.addCar(code,20,'D',"Normal");

		assertThrows("Non existent class not detected",
				TrainException.class,
				()-> mgr.addCar("ABN02",15,'D',"Majestic"));
	}

	@Test
	public void testCarClasses() throws TrainException {
		mgr.addClasses(CLASSES);
		
		String code = "XD345";
		mgr.addCar(code,20,'D',"Normal");
		mgr.addCar("FD386",20,'D',"Normal");
		mgr.addCar("AH876",15,'C',"Luxury");
		mgr.addCar("OK358",5,'B',"First");

		Collection<String> normal = mgr.getCarsByClass("Normal");
		assertNotNull("Missing normal class cars", normal);
		assertEquals("Wrong number of normal cars", 2, normal.size());
		assertTrue("Missing car: " + code, normal.contains(code));
	}

}
