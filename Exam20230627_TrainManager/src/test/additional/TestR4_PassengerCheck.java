package test.additional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import train.TrainException;
import train.TrainManager;

public class TestR4_PassengerCheck {

	private final static String SSN = "GVNBNC80B14F219K";
	private final static String[] CLASSES = {"Normal","Luxury","First"};
	private final static String CAR_ID = "XD345";
	private TrainManager mgr;
	private String b1, b2;
	
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
		b1 = mgr.bookSeat(SSN,"Giovanni","Bianchi",begin,end,CAR_ID,"1A");
		b2 = mgr.bookSeat("LRARSS87G64A341J","Laura","Rossi","Novara","Milan Centrale",CAR_ID,"1A");
		mgr.bookSeat("MRIBNC91J82O098E","Maria","Bianchi","Turin","Milan Centrale",CAR_ID,"8B");

	}

	@Test
	public void testLastStop() {
		assertEquals(2, mgr.setLastStop("Turin"));
		assertEquals(1, mgr.setLastStop("Vercelli"));
		assertEquals(2, mgr.setLastStop("Novara"));
	}

	@Test
	public void testCheckSeat() {
		mgr.setLastStop("Turin");
		String booking = mgr.checkSeat(CAR_ID,"1A");
		assertNotNull("Missing booking for seat 1A", booking);
		assertEquals("Wrong booking the seat 1A", b1,booking);
	}

	@Test
	public void testCheckSeat2() {
		mgr.setLastStop("Novara");
		String booking = mgr.checkSeat(CAR_ID,"1A");
		assertNotNull("Missing booking for seat 1A", booking);
		assertEquals("Wrong booking the seat 1A", b2,booking);
	}
}
