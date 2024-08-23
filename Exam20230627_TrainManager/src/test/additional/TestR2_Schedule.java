package test.additional;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import train.TrainException;
import train.TrainManager;

public class TestR2_Schedule {

	private final static String[] CLASSES = {"Normal","Luxury","First"};
	private final static String CAR_ID = "XD345";
	private TrainManager mgr;
	
	@Before
	public void setUp() throws TrainException {
		mgr = new TrainManager();
		mgr.addClasses(CLASSES);
		
		mgr.addCar(CAR_ID,20,'D',"Normal");
		mgr.addCar("FD386",20,'D',"Normal");
		mgr.addCar("AH876",15,'C',"Luxury");
		mgr.addCar("OK358",5,'B',"First");

	}

	@Test
	public void testStops() {
		int numSegments = mgr.defineStops("Turin","Vercelli","Novara","Milan Centrale");

		assertEquals("Wrong number of segments", 3, numSegments);
	}

	@Test
	public void testDailySlots() {
		mgr.defineStops("Turin","Vercelli","Novara","Milan Centrale");
		Map<String,List<String>> seats = mgr.findSeats("Vercelli","Milan Centrale","Normal");
		assertNotNull("Missing seats", seats);
		assertEquals(2,seats.size());
		assertTrue(seats.containsKey(CAR_ID));
		assertEquals(80, seats.get(CAR_ID).size());
		assertTrue(seats.get(CAR_ID).containsAll(Arrays.asList("1A","10B","20D")));
	}

}
