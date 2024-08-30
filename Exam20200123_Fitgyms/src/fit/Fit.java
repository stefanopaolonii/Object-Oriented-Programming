package fit;

import java.util.*;
import java.util.stream.Collectors;


public class Fit {
    private Map<String,Gym> gymsMap= new HashMap<>();
	private Map<Integer,Customer> customersMap= new HashMap<>();
	private int customerCounter=0;
    public static int MONDAY    = 1;
    public static int TUESDAY   = 2;
    public static int WEDNESDAY = 3;
    public static int THURSDAY  = 4;
    public static int FRIDAY    = 5;
    public static int SATURDAY  = 6;
    public static int SUNDAY    = 7;
    
	public Fit() {
	
	}
	// R1 
	
	public void addGymn (String name) throws FitException {
		if(!gymsMap.containsKey(name)) throw new FitException();
		gymsMap.put(name, new Gym(name));
	}
	
	public int getNumGymns() {
		return gymsMap.size();
	}
	
	//R2

	public void addLessons (String gymnname, 
	                        String activity, 
	                        int maxattendees, 
	                        String slots, 
	                        String ...allowedinstructors) throws FitException{
		if(!gymsMap.containsKey(gymnname)) throw new FitException();
		Gym searchedGym= gymsMap.get(gymnname);
		Set<String> gymslots= searchedGym.getLessonMap().keySet();
		List<String> slotsList=Arrays.asList(slots.split(","));
		for(String slot:slots.split(",")){
			String[] parts=slot.split(".");
			int day=Integer.parseInt(parts[0]);
			int hour=Integer.parseInt(parts[1]);
			if(day<1 || day>7) throw new FitException();
			if(hour<8 || hour>20) throw new FitException();
			if(gymslots.contains(slot)) throw new FitException();
		}
		slotsList.stream().forEach(slot-> searchedGym.addLesson(slot, new Lesson(gymnname, activity, maxattendees, slot, allowedinstructors)));
	}
	
	//R3
	public int addCustomer(String name) {
		customersMap.put(customerCounter, new Customer(++customerCounter, name));
		return customerCounter;
	}
	
	public String getCustomer (int customerid) throws FitException{
		if(!customersMap.containsKey(customerid)) throw new FitException();
	    return customersMap.get(customerid).getName();
	}
	
	//R4
	
	public void placeReservation(int customerid, String gymnname, int day, int slot) throws FitException{
		if(!customersMap.containsKey(customerid)) throw new FitException();
		if(!gymsMap.containsKey(gymnname)) throw new FitException();
		if(day<1 || day>7) throw new FitException();
		if(slot<8 || slot>20) throw new FitException();
		Customer searchedCustomer= customersMap.get(customerid);
		Gym searchedGym= gymsMap.get(gymnname);
		if(!searchedGym.getLessonMap().containsKey(String.format("%d.%d", day,slot))) throw new FitException();
		Lesson searchedLesson= searchedGym.getLessonMap().get(String.format("%d.%d", day,slot));
		if(searchedLesson.getMaxattendees()<=searchedLesson.getCustomersList().size()) throw new FitException();
		if(searchedLesson.getCustomersList().stream().map(Customer::getId).collect(Collectors.toList()).contains(customerid)) throw new FitException();
		searchedLesson.addCustomer(searchedCustomer);
		searchedCustomer.addLesson(searchedLesson); 
	}
	
	public int getNumLessons(int customerid) {
		if(!customersMap.containsKey(customerid)) return 0;
		return customersMap.get(customerid).getLessonsList().size();
	}
	
	
	//R5
	
	public void addLessonGiven (String gymnname, int day, int slot, String instructor) throws FitException{

	}
	
	public int getNumLessonsGiven (String gymnname, String instructor) throws FitException {
	    return -1;
	}
	//R6
	
	public String mostActiveGymn() {
		return null;
	}
	
	public Map<String, Integer> totalLessonsPerGymn() {		
		return null;
	}
	
	public SortedMap<Integer, List<String>> slotsPerNofParticipants(String gymnname) throws FitException{
	    return null;
	}
	

	
	
	
	


}
