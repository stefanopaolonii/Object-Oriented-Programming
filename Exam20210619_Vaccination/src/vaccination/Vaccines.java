package vaccination;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.*;
import java.util.stream.Collectors;


public class Vaccines {
	public final static int CURRENT_YEAR=java.time.LocalDate.now().getYear();
	private Map<String,Person> personMap= new HashMap<>();
	private List<String> intervalList= new ArrayList<>();
	private Map<String,Hub> hubMap= new HashMap<>();
	private Map<Integer,Integer> hoursMap= new TreeMap<>();
	
// R1

	/**
	 * Add a new person to the vaccination system.
	 * 
	 * Persons are uniquely identified by SSN (italian "codice fiscale")
	 * 
	 * @param first first name
	 * @param last  last name
	 * @param ssn	italian "codice fiscale"
	 * @param year  birth year
	 * @return {@code false} if ssn is duplicate, 
	 */
	public boolean addPerson(String first, String last, String ssn, int year) {
		if(personMap.containsKey(ssn)) return false;
		personMap.put(ssn, new Person(ssn, first, last, year));
		return true;
	}
	
	/**
	 * Count the number of people added to the system
	 * 
	 * @return person count
	 */
	public int countPeople() {
		return personMap.size();
	}
	
	/**
	 * Retrieves information about a person.
	 * Information is formatted as ssn, last name, and first name
	 * separate by {@code ','} (comma).
	 * 	 
	 * @param ssn "codice fiscale" of person searched
	 * @return info about the person
	 */
	public String getPerson(String ssn) {
		if(!personMap.containsKey(ssn)) return null;
		return personMap.get(ssn).toString();
	}
	
	
	/**
	 * Retrieves of a person given their SSN (codice fiscale).
	 * 	 
	 * @param ssn 	"codice fiscale" of person searched
	 * @return age of person (in years)
	 */
	public int getAge(String ssn) {
		if(!personMap.containsKey(ssn)) return -1;
		return CURRENT_YEAR-personMap.get(ssn).getYear();
	}

	
	/**
	 * Define the age intervals by providing the breaks between intervals.
	 * The first interval always start at 0 (non included in the breaks) 
	 * and the last interval goes until infinity (not included in the breaks).
	 * All intervals are closed on the lower boundary and open at the upper one.
	 * <p>
	 * For instance {@code setAgeIntervals(40,50,60)} 
	 * defines four intervals {@code "[0,40)", "[40,50)", "[50,60)", "[60,+)"}.
	 * 
	 * @param breaks the array of breaks
	 */
	public void setAgeIntervals(int... breaks) {
		int initial=0;
		for(int value: breaks){
			intervalList.add(String.format("[%d,%d)", initial,value));
			initial=value;
		}
		intervalList.add(String.format("[%d,+)", initial));
	}
	
	
	/**
	 * Retrieves the labels of the age intervals defined.
	 * 
	 * Interval labels are formatted as {@code "[0,10)"},
	 * if the upper limit is infinity {@code '+'} is used
	 * instead of the number.
	 * 
	 * @return labels of the age intervals
	 */
	public Collection<String> getAgeIntervals(){
		return intervalList;
	}
	
	
	/**
	 * Retrieves people in the given interval.
	 * 
	 * The age of the person is computed by subtracting
	 * the birth year from current year.
	 * 
	 * @param interval age interval label
	 * @return collection of SSN of person in the age interval
	 */
	public Collection<String> getInInterval(String interval){
		String[] parts=interval.split(",");
		int initial;
		final int finali;
		initial=Integer.parseInt(parts[0].replaceFirst("[",""));
		if(!parts[1].trim().equals("+")){
			finali = Integer.parseInt(parts[1].replaceFirst(")",""));
		}else{
			finali=0;
		}
		return personMap.values().stream().map(Person::getSsn).filter(ssn->getAge(ssn)>=initial && (parts[1].trim().equals("+") || getAge(ssn)<=finali)).collect(Collectors.toList());
	}
	
	// R2
	/**
	 * Define a vaccination hub
	 * 
	 * @param name name of the hub
	 * @throws VaccineException in case of duplicate name
	 */
	public void defineHub(String name) throws VaccineException {
		if(hubMap.containsKey(name)) throw new VaccineException();
		hubMap.put(name, new Hub(name));
	}
	
	/**
	 * Retrieves hub names
	 * 
	 * @return hub names
	 */
	public Collection<String> getHubs() {
		return hubMap.keySet();
	}

	/**
	 * Define the staffing of a hub in terms of
	 * doctors, nurses and other personnel.
	 * 
	 * @param name name of the hub
	 * @param doctors number of doctors
	 * @param nurses number of nurses
	 * @param other number of other personnel
	 * 
	 * @throws VaccineException in case of undefined hub, or any number of personnel not greater than 0.
	 */
	public void setStaff(String name, int doctors, int nurses, int other) throws VaccineException {
		if(!hubMap.containsKey(name)) throw new VaccineException();
		if(doctors<=0 || nurses<=0 || other<=0) throw new VaccineException();
		hubMap.get(name).setStaff(doctors, nurses, other);
	}
	
	
	/**
	 * Estimates the hourly vaccination capacity of a hub
	 * 
	 * The capacity is computed as the minimum among 
	 * 10*number_doctor, 12*number_nurses, 20*number_other
	 * 
	 * @param hubName name of the hub
	 * @return hourly vaccination capacity
	 * 
	 * @throws VaccineException in case of undefined or hub without staff
	 */
	public int estimateHourlyCapacity(String hubName)  throws VaccineException {
		if(!hubMap.containsKey(hubName)) throw new VaccineException();
		Hub searchedHub= hubMap.get(hubName);
		if(searchedHub.getDoctors()==0) throw new VaccineException();
		return searchedHub.getDoctors()*10+searchedHub.getNurses()*12+searchedHub.getOther()*20;
	}
	
	// R3
	/**
	 * Load people information stored in CSV format.
	 * 
	 * The header must start with {@code "SSN,LAST,FIRST"}.
	 * All lines must have at least three elements.
	 * 
	 * In case of error in a person line the line is skipped.
	 * 
	 * @param people {@code Reader} for the CSV content
	 * @return number of correctly added people
	 * @throws IOException in case of IO error
	 * @throws VaccineException in case of error in the header
	 */
	public long loadPeople(Reader people) throws IOException, VaccineException {
		BufferedReader br = new BufferedReader(people);
		int lineCounter=0;
		try{
			String line;
			while((line=br.readLine())!=null){
				String[] lineparts= line.split(",");
				if(lineCounter==0 & (!lineparts[0].equals("SSN") || !lineparts[1].equals("LAST") || !lineparts[2].equals("FIRST") || !lineparts[0].equals("YEAR"))) throw new VaccineException();
				if(lineCounter==0) continue;
				if(lineparts.length!=4) continue;
				if(personMap.containsKey(lineparts[0])) continue;
				addPerson(lineparts[0], lineparts[1], lineparts[2], Integer.parseInt(lineparts[3]));
				lineCounter++;
			}
		}catch(IOException e){
			e.getLocalizedMessage();
		}

		br.close();
		return lineCounter;
	}
	
	// R4
	/**
	 * Define the amount of working hours for the days of the week.
	 * 
	 * Exactly 7 elements are expected, where the first one correspond to Monday.
	 * 
	 * @param hours workings hours for the 7 days.
	 * @throws VaccineException if there are not exactly 7 elements or if the sum of all hours is less than 0 ore greater than 24*7.
	 */
	public void setHours(int... hours) throws VaccineException {
		if(hours.length!=7) throw new VaccineException();
		for(int value:hours) if(value>12) throw new VaccineException();
		for(int i=0;i<hours.length;i++){
			hoursMap.put(i, hours[i]);
		}
	}
	
	/**
	 * Returns the list of standard time slots for all the days of the week.
	 * 
	 * Time slots start at 9:00 and occur every 15 minutes (4 per hour) and
	 * they cover the number of working hours defined through method {@link #setHours}.
	 * <p>
	 * Times are formatted as {@code "09:00"} with both minuts and hours on two
	 * digits filled with leading 0.
	 * <p>
	 * Returns a list with 7 elements, each with the time slots of the corresponding day of the week.
	 * 
	 * @return the list hours for each day of the week
	 */
	public List<List<String>> getHours(){
		return hoursMap.values().stream().map(hour->{List<String> dayslot= new ArrayList<>(); int currtime=timeinMin("09:00"); while(currtime<=timeinMin("09:00")+hour*60){ dayslot.add(String.format("%02d:%02d",currtime));}return dayslot;}).collect(Collectors.toList());
	}

	private int timeinMin(String time){
		String[] parts=time.split(":");
		return Integer.parseInt(parts[0])*60+Integer.parseInt(parts[1]);
	}

	/**
	 * Compute the available vaccination slots for a given hub on a given day of the week
	 * <p>
	 * The availability is computed as the number of working hours of that day
	 * multiplied by the hourly capacity (see {@link #estimateCapacity} of the hub.
	 *  
	 * @return
	 */
	public int getDailyAvailable(String hubName, int day) {
		if(day<0 || day>7) return -1;
		try{
			return estimateHourlyCapacity(hubName)*hoursMap.get(day);
		}catch(VaccineException ve){
			return -1;
		}
		
	}

	/**
	 * Compute the available vaccination slots for each hub and for each day of the week
	 * <p>
	 * The method returns a map that associates the hub names (keys) to the lists
	 * of number of available hours for the 7 days.
	 * <p>
	 * The availability is computed as the number of working hours of that day
	 * multiplied by the capacity (see {@link #estimateCapacity} of the hub.
	 *  
	 * @return
	 */
	public Map<String,List<Integer>> getAvailable(){
		return hubMap.values().stream().collect(Collectors.toMap(Hub::getName, hub-> hoursMap.values().stream().map(hour->getDailyAvailable(hub.getName(), hour)).collect(Collectors.toList())));
	}
	
	/**
	 * Computes the general allocation plan a hub on a given day.
	 * Starting with the oldest age intervals 40% 
	 * of available places are allocated
	 * to persons in that interval before moving the the next
	 * interval and considering the remaining places.
	 * <p>
	 * The returned value is the list of SSNs (codice fiscale) of the
	 * persons allocated to that day 
	 * <p>
	 * <b>N.B.</b> no particular order of allocation is guaranteed
	 * 
	 * @param hubName  name of the hub
	 * @param day 	   day of week index (0 = Monday)
	 * @return the list of daily allocations
	 */
	public List<String> allocate(String hubName, int day){
		int n=getDailyAvailable(hubName, day);
		List<Person> allocatedPerson;
		List<String> totalAllocated= new ArrayList<>();
		for(String interval: intervalList.stream().sorted((s1,s2)-> s2.compareTo(s1)).collect(Collectors.toList())){
			allocatedPerson=getInInterval(interval).stream().map(ssn->personMap.get(ssn)).filter(person->!person.isAllocated()).limit((long) (n*0.4)).collect(Collectors.toList());
			for(Person person:allocatedPerson){
				person.setAllocated(true);
				person.allocate(hubName, day);
				totalAllocated.add(person.getSsn());
			}
			n-=allocatedPerson.size();
		}
		if(n!=0){
			allocatedPerson=personMap.values().stream().filter(person->!person.isAllocated()).sorted(Comparator.comparingInt((Person person)-> getAge(person.getSsn())).reversed()).limit(n).collect(Collectors.toList());
			for(Person person:allocatedPerson){
				person.setAllocated(true);
				person.allocate(hubName, day);
				totalAllocated.add(person.getSsn());
			}
		}
		return totalAllocated;
	}
	
	/**
	 * Removes all people from allocation lists and 
	 * clears their allocation status
	 * 
	 */
	public void clearAllocation() {
		personMap.values().stream().forEach(person->person.setAllocated(false));
	}
	
	/**
	 * Computes the general allocation plan for the week.
	 * For every day, starting with the oldest age intervals
	 * 40% available places are allocated
	 * to persons in that interval before moving the the next
	 * interval and considering the remaining places.
	 * <p>
	 * The returned value is a list with 7 elements, one
	 * for every day of the week, each element is a map that 
	 * links the name of each hub to the list of SSNs (codice fiscale) 
	 * of the persons allocated to that day in that hub
	 * <p>
	 * <b>N.B.</b> no particular order of allocation is guaranteed
	 * but the same invocation (after {@link #clearAllocation}) must return the same 
	 * allocation.
	 * 
	 * @return the list of daily allocations
	 */
	public List<Map<String,List<String>>> weekAllocate(){
		return null;
	}
	
	// R5
	/**
	 * Returns the proportion of allocated people
	 * w.r.t. the total number of persons added
	 * in the system
	 * 
	 * @return proportion of allocated people
	 */
	public double propAllocated() {
		return -1.0;
	}
	
	/**
	 * Returns the proportion of allocated people
	 * w.r.t. the total number of persons added
	 * in the system, divided by age interval.
	 * <p>
	 * The map associates the age interval label
	 * to the proportion of allocates people in that interval
	 * 
	 * @return proportion of allocated people by age interval
	 */
	public Map<String,Double> propAllocatedAge(){
		return null;
	}

	/**
	 * Retrieves the distribution of allocated persons
	 * among the different age intervals.
	 * <p>
	 * For each age intervals the map reports the
	 * proportion of allocated persons in the corresponding
	 * interval w.r.t the total number of allocated persons 
	 * 
	 * @return
	 */
	public Map<String,Double> distributionAllocated(){
		return null;
	}

	// R6
	/**
	 * Defines a listener for the file loading method.
	 * The {@ accept()} method of the listener is called
	 * passing the line number and the offending line.
	 * <p>
	 * Lines start at 1 with the header line.
	 * 
	 * @param listener the listener for load errors
	 */
	/*public void setLoadListener(BiConsumer<Integer,String> listener) {
	}*/

}
