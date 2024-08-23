package test.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import train.TrainException;
import train.TrainManager;

public class TestExample {

	@Test
	public void testAll() throws TrainException {
		TrainManager train = new TrainManager();
		
		// R1 : classes and cars
		
		train.addClasses("Beasts","Normal","Luxury","Imperial");
		
		Collection<String> classes = train.getClasses();
		assertNotNull(classes);
		assertEquals(4, classes.size());
		assertTrue(classes.contains("Luxury"));
		
		String code = "XD345";
		int n = train.addCar(code,20,'D',"Normal");
		assertEquals(80, n);
		
		train.addCar("FD386",20,'D',"Normal");
		train.addCar("OQ643",30,'F',"Beasts");
		train.addCar("AH876",15,'C',"Luxury");
		train.addCar("OK358",5,'B',"Imperial");
		
		assertThrows(TrainException.class,
					 ()-> train.addCar("SL654",20,'C',"Silence"));

		Collection<String> normal = train.getCarsByClass("Normal");
		assertNotNull(normal);
		assertEquals(2,normal.size());
		
		assertEquals(10,train.getNumSeats("OK358"));
		
		// R2 : schedule
		
		// define slots on 2023-06-28 from 10:00 to 12:00 every 20 minutes
		int numSegments = train.defineStops("Turin","Vercelli","Novara","Milan Centrale");
		assertEquals(3, numSegments);
		
		Map<String,List<String>> seats = train.findSeats("Vercelli","Milan Centrale","Normal");
		assertNotNull(seats);
		assertEquals(2,seats.size());
		assertTrue(seats.containsKey(code));
		assertEquals(80, seats.get(code).size());
		assertTrue(seats.get(code).containsAll(Arrays.asList("1A","10B","20D")));
		
		
		// R3 : booking seats
		
		String ssn = "GVNBNC80B14F219K";
		String b1 = train.bookSeat(ssn,"Giovanni","Bianchi","Turin","Vercelli",code,"8B");
		train.bookSeat("LRARSS87G64A341J","Laura","Rossi","Turin","Milan Centrale",code,"1A");
		
		assertEquals(code, train.getBookingCar(b1));
		assertEquals(ssn, train.getBookingPassenger(b1));
		assertEquals("8B", train.getBookingSeat(b1));
		assertEquals("Turin-Vercelli", train.getBookingTrip(b1));
		
		String b3 = train.bookSeat("MRIBNC91J82O098E","Maria","Bianchi","Novara","Milan Centrale",code,"8B");

		Collection<String> bookings = train.listBookings(code,"8B");
		assertNotNull(bookings);
		assertEquals(2,bookings.size());
		assertTrue("Wrong bookings" + bookings, bookings.contains("Turin-Vercelli:"+ssn));
		
		
		// R4 : checking passengers
		
		int numPassengers = train.setLastStop("Turin");
		assertEquals(2, numPassengers);
		
		String bookingCode = train.checkSeat(code,"8B");
		assertNotNull(bookingCode);
		assertEquals(b1,bookingCode);
		
		assertNull(train.checkSeat(code,"10A"));

		train.setLastStop("Novara");
		bookingCode = train.checkSeat(code,"8B");
		assertNotNull(bookingCode);
		assertEquals(b3,bookingCode);

		// R5 : stats
		
		double fillRatio = train.showFillRatio("Normal");
		assertEquals(2/160.0, fillRatio, 0.0001);
		
		double occupationRatio = train.showOccupationRatio("Normal");
		assertEquals(5/(160.0*3), occupationRatio, 0.0001);

		Map<String,Long> coverage = train.checkCoverage();
		assertNotNull(coverage);
		assertEquals(coverage.toString(), 4, coverage.size());
		assertEquals(2, coverage.get("Normal"), 0.001);
		
	}
		
}