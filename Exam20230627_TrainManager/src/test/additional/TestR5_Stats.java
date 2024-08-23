package test.additional;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import train.TrainException;
import train.TrainManager;

public class TestR5_Stats {

	private final static String SSN = "GVNBNC80B14F219K";
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
		
		mgr.defineStops("Turin","Vercelli","Novara","Milan Centrale");
		
		String begin = "Turin";
		String end = "Vercelli";
		mgr.bookSeat(SSN,"Giovanni","Bianchi",begin,end,CAR_ID,"1A");
		mgr.bookSeat("LRARSS87G64A341J","Laura","Rossi","Novara","Milan Centrale",CAR_ID,"1A");
		mgr.bookSeat("MRIBNC91J82O098E","Maria","Bianchi","Turin","Milan Centrale",CAR_ID,"8B");
		
		mgr.setLastStop("Turin");

		mgr.checkSeat(CAR_ID,"1A");

		mgr.setLastStop("Novara");
		mgr.checkSeat(CAR_ID,"1A");
	}
	

	@Test
	public void testFillRatio() {
		double fillRatio = mgr.showFillRatio("Normal");
		assertEquals("Wrong fill ratio", 2/160.0, fillRatio, 0.0001);
		
	}

	@Test
	public void testOccupationRatio() {
		double occupationRatio = mgr.showOccupationRatio("Normal");
		assertEquals("Wrong occupation ratio", 5/(160.0*3), occupationRatio, 0.0001);
	}

	@Test
	public void testCoverage() {
		Map<String,Long> coverage = mgr.checkCoverage();
		assertNotNull("Missing coverage info", coverage);
		assertEquals("Wrong number of classes in coverage " + coverage.keySet(), CLASSES.length, coverage.size());
		assertEquals("Wrong coverage for Normal:", 2, coverage.get("Normal"), 0.001);
	}

}
