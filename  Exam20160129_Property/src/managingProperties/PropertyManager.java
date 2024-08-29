package managingProperties;

import java.util.*;
import java.util.stream.*;

public class PropertyManager {
	public enum Status{PENDING,ASSIGNED,COMPLETED};
	private Map<String,Integer> buildingsMap= new HashMap<>();
	private Map<String,Owner> ownersMap= new HashMap<>();
	private Map<String,Profession> professionsMap= new HashMap<>();
	private Map<Integer,Request> requestsMap= new HashMap<>();
	private int requestCounter=0;
	/**
	 * Add a new building 
	 */
	public void addBuilding(String building, int n) throws PropertyException {
		if(buildingsMap.containsKey(building)) throw new PropertyException();
		if(n<1 || n>100) throw new PropertyException();
		buildingsMap.put(building, n);
	}

	public void addOwner(String owner, String... apartments) throws PropertyException {
		if(ownersMap.containsKey(owner)) throw new PropertyException();
		Owner newOwner= new Owner(owner);
		for(String apartment:apartments){
			String[] parts=apartment.split(":");
			if(!buildingsMap.containsKey(parts[0])) throw new PropertyException();
			int n=buildingsMap.get(parts[0]);
			if(Integer.parseInt(parts[1])<1 && Integer.parseInt(parts[1])>n) throw new PropertyException();
			newOwner.addApartment(new Apartment(parts[0], Integer.parseInt(parts[1]), apartment));
		}
		ownersMap.put(owner, newOwner);
	}

	/**
	 * Returns a map ( number of apartments => list of buildings ) 
	 * 
	 */
	public SortedMap<Integer, List<String>> getBuildings() {
		return buildingsMap.entrySet().stream().collect(Collectors.groupingBy(Map.Entry::getValue,TreeMap::new,Collectors.mapping(Map.Entry::getKey, Collectors.toList())));
	}

	public void addProfessionals(String profession, String... professionals) throws PropertyException {
			if(professionsMap.containsKey(profession)) throw new PropertyException();
			List<String> professionalsList=professionsMap.values().stream().flatMap(prof->prof.getProfessionalList().stream()).collect(Collectors.toList());
			for(String professional:professionals) if(professionalsList.contains(professional)) throw new PropertyException();
			professionsMap.put(profession, new Profession(profession, professionals));
	}

	/**
	 * Returns a map ( profession => number of workers )
	 *
	 */
	public SortedMap<String, Integer> getProfessions() {
		return professionsMap.values().stream().collect(Collectors.toMap(Profession::getName,profession->profession.getProfessionalList().size(),(e1,e2)->e1,TreeMap::new));
	}

	public int addRequest(String owner, String apartment, String profession) throws PropertyException {
		if(!ownersMap.containsKey(owner)) throw new PropertyException();
		if(!professionsMap.containsKey(profession)) throw new PropertyException();
		if(!ownersMap.get(owner).getApartmentsMap().containsKey(apartment)) throw new PropertyException();
		requestCounter++;
		requestsMap.put(requestCounter, new Request(requestCounter, ownersMap.get(owner), ownersMap.get(owner).getApartmentsMap().get(apartment), professionsMap.get(profession)));
		return requestCounter;
	}

	public void assign(int requestN, String professional) throws PropertyException {
		if(!requestsMap.containsKey(requestN)) throw new PropertyException();
		Request searchedRequest= requestsMap.get(requestN);
		if(searchedRequest.getStatus()!=Status.PENDING)throw new PropertyException();
		if(!searchedRequest.getProfession().getProfessionalList().contains(professional)) throw new PropertyException();
		searchedRequest.setStatus(Status.ASSIGNED);
		searchedRequest.setProfessionalId(professional);
	}

	public List<Integer> getAssignedRequests() {
		return requestsMap.values().stream().sorted(Comparator.comparingInt(Request::getId)).map(Request::getId).collect(Collectors.toList());
	}

	
	public void charge(int requestN, int amount) throws PropertyException {
		if(!requestsMap.containsKey(requestN)) throw new PropertyException();
		Request searchedRequest= requestsMap.get(requestN);
		if(searchedRequest.getStatus()!=Status.ASSIGNED) throw new PropertyException();
		if(amount<0 || amount>1000) throw new PropertyException();
		searchedRequest.setAmount(amount);
		searchedRequest.setStatus(Status.COMPLETED);
		
	}

	/**
	 * Returns the list of request ids
	 * 
	 */
	public List<Integer> getCompletedRequests() {
		
		return null;
	}
	
	/**
	 * Returns a map ( owner => total expenses )
	 * 
	 */
	public SortedMap<String, Integer> getCharges() {
		
		return null;
	}

	/**
	 * Returns the map ( building => ( profession => total expenses) ).
	 * Both buildings and professions are sorted alphabetically
	 * 
	 */
	public SortedMap<String, Map<String, Integer>> getChargesOfBuildings() {
		
		return null;
	}

}
