package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import train.TrainException;
import train.TrainManager;

public class TestR3_Bookings {

	private static final String CAR_ID2 = "FD386";
	private final static String[] CLASSES = {"Normal","Luxury","First"};
	private final static String CAR_ID = "XD345";
	private TrainManager mgr;
	
	@Before
	public void setUp() throws TrainException {
		mgr = new TrainManager();
		mgr.addClasses(CLASSES);
		
		mgr.addCar(CAR_ID,20,'D',"Normal");
		mgr.addCar(CAR_ID2,20,'D',"Normal");
		mgr.addCar("AH876",15,'C',"Luxury");
		mgr.addCar("OK358",5,'B',"First");
		
		mgr.defineStops("Turin","Vercelli","Novara","Milan Centrale");
	}
	
	@Test
	public void testBooking() throws TrainException {
		String ssn = "GVNBNC80B14F219K";
		String b = mgr.bookSeat(ssn,"Giovanni","Bianchi","Turin","Vercelli",CAR_ID,"8B");
		
		assertEquals(CAR_ID, mgr.getBookingCar(b));
		assertEquals(ssn, mgr.getBookingPassenger(b));
		assertEquals("8B", mgr.getBookingSeat(b));
		assertEquals("Turin-Vercelli", mgr.getBookingTrip(b));
	}

	@Test
	public void testBookingBadCode() {
		assertThrows("Invalid car id for booking not detected",
				TrainException.class,
				()->mgr.bookSeat("GVNBNC80B14F219K","Giovanni","Bianchi","Turin","Vercelli","INV4L10","8B"));
	}

	@Test
	public void testBookingBadSeat() {
		assertThrows("Wrong seat for booking not detected",
				TrainException.class,
				()->mgr.bookSeat("GVNBNC80B14F219K","Giovanni","Bianchi","Turin","Vercelli",CAR_ID,"99Z"));
	}
	
	@Test
	public void testBookingFullOverlap() throws TrainException {
		mgr.bookSeat("GVNBNC80B14F219K","Giovanni","Bianchi","Turin","Vercelli",CAR_ID,"1A");
		assertThrows("Wrong slot for booking not detected",
				TrainException.class,
				()->mgr.bookSeat("LRARSS87G64A341J","Laura","Rossi","Turin","Vercelli",CAR_ID,"1A"));
	}

	@Test
	public void testBookingPartialOverlap() throws TrainException {
		mgr.bookSeat("GVNBNC80B14F219K","Giovanni","Bianchi","Turin","Novara",CAR_ID,"1A");
		assertThrows("Wrong slot for booking not detected",
				TrainException.class,
				()->mgr.bookSeat("LRARSS87G64A341J","Laura","Rossi","Vercelli","Milan Centrale",CAR_ID,"1A"));
	}

	@Test
	public void testListBookings() throws TrainException {
		String ssn = "GVNBNC80B14F219K";
		String begin = "Turin";
		String end = "Vercelli";
		mgr.bookSeat(ssn,"Giovanni","Bianchi",begin,end,CAR_ID,"1A");
		mgr.bookSeat("LRARSS87G64A341J","Laura","Rossi","Novara","Milan Centrale",CAR_ID,"1A");

		Collection<String> bookings = mgr.listBookings(CAR_ID, "1A");
		assertNotNull("Missing bookings", bookings);
		assertEquals("Wrong number of bookings", 2,bookings.size());
		assertTrue("Could not find booking with "+ssn, bookings.contains(begin + "-" + end + ":"+ssn));
	}

	@Test
	public void testListBookings2() throws TrainException {
		String ssn = "GVNBNC80B14F219K";
		String begin = "Turin";
		String end = "Vercelli";
		mgr.bookSeat(ssn,"Giovanni","Bianchi",begin,end,CAR_ID,"1A");
		mgr.bookSeat("LRARSS87G64A341J","Laura","Rossi",end,"Milan Centrale",CAR_ID,"1A");

		Collection<String> bookings = mgr.listBookings(CAR_ID, "1A");
		assertNotNull("Missing bookings", bookings);
		assertEquals("Wrong number of bookings", 2,bookings.size());
		assertTrue("Could not find booking with "+ssn+ " in " + bookings, 
				   bookings.contains(begin + "-" + end + ":"+ssn));
	}

	@Test
	public void testListBookingsNotPresent() throws TrainException {
		String ssn = "GVNBNC80B14F219K";
		String begin = "Turin";
		String end = "Vercelli";
		mgr.bookSeat(ssn,"Giovanni","Bianchi",begin,end,CAR_ID,"1A");
		mgr.bookSeat("LRARSS87G64A341J","Laura","Rossi","Novara","Milan Centrale",CAR_ID,"2B");
		mgr.bookSeat("MARRSS88X33A321L","Mario","Rosso","Turin","Milan Centrale",CAR_ID2,"1A");
		mgr.bookSeat("SRAVRD04F45V987D","Sara","Verdi","Vercelli","Milan Centrale",CAR_ID2,"7C");

		Collection<String> bookings = mgr.listBookings(CAR_ID, "1A");
		assertNotNull("Missing bookings", bookings);
		assertEquals("Wrong number of bookings", 1,bookings.size());
		assertTrue("Could not find booking with "+ssn+ " in " + bookings, 
				   bookings.contains(begin + "-" + end + ":"+ssn));
	}

	@Test
	public void testNonBookedSeats() throws TrainException {
		String ssn = "GVNBNC80B14F219K";
		mgr.bookSeat(ssn,"Giovanni","Bianchi","Turin","Vercelli",CAR_ID,"8B");
		

		Map<String,List<String>> seats = mgr.findSeats("Turin","Milan Centrale","Normal");
		assertNotNull("Missing seats", seats);
		assertEquals("Wrong number of available seats", 79, seats.get(CAR_ID).size());
		assertFalse("Seat should be booked", seats.get(CAR_ID).contains("8B"));
	}

}
