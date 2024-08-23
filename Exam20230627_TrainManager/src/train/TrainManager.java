package train;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class TrainManager {

//R1
	/**
	 * add a set of travel classes to the list of classes
	 * offered in the train.
	 * Method can be invoked multiple times.
	 * Possible duplicates are ignored.
	 * 
	 * @param classes the classes
	 */
	public void addClasses(String... classes) {

	}

	/**
	 * retrieves the list of classes defined for the train
	 * 
	 * @return list of classes
	 */
	public Collection<String> getClasses() {
		return null;
	}
	
	/**
	 * adds a new car to train
	 * The car has a unique id, a given number of rows
	 * and for each row the seats go from 'A' to the {@code lastSeat}
	 * 
	 * @param id		unique id of car
	 * @param rows		name of car
	 * @param lastSeat	lastLetter of car
	 * @param klass 	class of the car
	 * @return number of available seats on the train car
	 * @throws TrainException in case of duplicate id or non-existing class
	 */
	public int addCar(String id, int rows, char lastSeat, String klass) throws TrainException {
		return -1;
	}

	/**
	 * retrieves the list of cars with the given class
	 * 
	 * @param klass required class
	 * @return the list of car ids
	 */
	public Collection<String> getCarsByClass(String klass) {
		return null;
	}

	/**
	 * retrieves the number of seats in the car with the given code
	 * 
	 * @param id id of the car 
	 * @return number of seats
	 */
	public int getNumSeats(String id) {
		return -1;
	}

	/**
	 * Define the stop stations for a train.
	 * Stops define the segments that are the part of the
	 * train path between two consecutive stops. 
	 * 
	 * @param stops		stops of the train
	 * @return the number of segments defined
	 */
	public int defineStops(String... stops) {
		return -1;
	}
	
	/**
	 * retrieves the available seats on a given trip from source to destination.
	 * The returned map contains an entry for each car that has seats
	 * available for the specific class and in all the segments from begin to end.
	 * The map contains a list of seats "#X", where # is the row number and X is the seat
	 * e.g. "8A" is the seat in row 8 position A.
	 * The method return only the available seats (i.e. those that are not booked)
	 * 
	 * @param begin		initial stop
	 * @param end		final stop
	 * @param klass 	car class
	 * @return the available seats by car
	 */
	public Map<String, List<String>> findSeats(String begin, String end, String klass) {
		return null;
	}

	/**
	 * Book a seat for a person from begin station to end station
	 * on a given car
	 * 
	 * @param ssn		SSN of the passenger
	 * @param name		name of the passenger
	 * @param surname	surname of the passenger
	 * @param begin		initial stop
	 * @param end		final stop
	 * @param car		car id
	 * @param seat		seat number
	 * @return a unique booking code
	 * @throws TrainException in case the car or seat are not valid,
	 * 						  the stops are not valid, 
	 * 						  or the seat is not available for all the segments of the trip
	 */
	public String bookSeat(String ssn, String name, String surname, 
						   String begin, String end, String car, String seat) throws TrainException {
		return null;
	}

	/**
	 * retrieves the car of a given booking
	 * 
	 * @param booking 	id of booking
	 * @return car id
	 */
	public String getBookingCar(String booking) {
		return null;
	}

	/**
	 * retrieves the SSN of the booked person
	 * 
	 * @param bookingID id of booking
	 * @return  booked person's SSN
	 */
	public String getBookingPassenger(String bookingID) {
		return null;
	}

	/**
	 * retrieves the seat for a booking
	 * 
	 * @param bookingID id of booking
	 * @return the seat
	 */
	public String getBookingSeat(String bookingID) {
		return null;
	}

	/**
	 * retrieves the trip for a booking.
	 * A trip is described as the initial and final stop, separated by a "-",
	 * e.g. "Turin-Milan"
	 * 
	 * @param bookingID id of booking
	 * @return trip
	 */
	public String getBookingTrip(String bookingID) {
		return null;
	}

	/**
	 * retrieves the list bookings for a given seat.
	 * Bookings are reported as string with the format "begin-end:SSN"
	 * 
	 * @param car car id
	 * @param seat seat
	 * @return list of bookings
	 */
	public Collection<String> listBookings(String car, String seat) {
		return null;
	}

	/**
	 * Define the last station the train stopped at.
	 * this information will be used to understand which bookings
	 * are valid for a seat.
	 * 
	 * @param stop name of the latest stop
	 * @return the number of total people booked on the train after the stop
	 */
	public int setLastStop(String stop) {
		return -1;
	}


	/**
	 * check the booking id of a given seat.
	 * It takes into consideration the last stop, for a given
	 * seat the booking starting at or before the last stop
	 * and not yet terminated is considered.
	 * Returns null if no such booking is available.
	 * Mark the specific booking as checked.
	 * 
	 * @param car	id of the car
	 * @param seat 	seat code
	 * @return booking id
	 */
	public String checkSeat(String car, String seat) {
		return null;
	}


	/**
	 * computes the fill ratio for all the seat of the given class.
	 * It is computed dividing the number of seats (of the class in any car)
	 * that have at least a booking, by the number of seats (of the class in any car)
	 *  
	 * @param klass		class
	 * @return	fill ratio
	 */
	public double showFillRatio(String klass) {
		return -1.0;
	}

	/**
	 * computes the check count per class.
	 * The result map reports, for each class, the number of
	 * booking that have been checked.
	 *
	 * @return the map class : check count
	 */
	public Map<String, Long> checkCoverage() {
		
		return null;
	}

	/**
	 * computes the occupation ratio for all the seat of the given class and for each segment of the train path.
	 * It is computed dividing the number of seats occupied in each segment by 
	 * the number of seats multiplied by number of segments.
	 * This method is similar to {@link #showFillRatio} but consider all the slots represented by
	 * a seat in a given segment (path between two consecutive stops), the result is the proportion
	 * of slots covered by a booking (divided per class).
	 *  
	 * @param klass		class
	 * @return	occupation ratio
	 */
	public double showOccupationRatio(String klass) {
		return -1.0;
	}

}