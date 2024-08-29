package managingProperties;

import java.util.*;
import java.util.stream.*;

public class PropertyManager {
	public enum Status{PENDING,ASSIGNED,COMPLETED};
	private Map<String,Integer> buildingsMap= new HashMap<>();
	private Map<String,Owner> ownersMap= new HashMap<>();
	private Map<String,Profession> professionsMap= new HashMap<>();
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
				
	}

	/**
	 * Returns a map ( profession => number of workers )
	 *
	 */
	public SortedMap<String, Integer> getProfessions() {
		
		return null;
	}

	public int addRequest(String owner, String apartment, String profession) throws PropertyException {
		
		return 0;
	}

	public void assign(int requestN, String professional) throws PropertyException {
		
		
	}

	public List<Integer> getAssignedRequests() {
		
		return null;
	}

	
	public void charge(int requestN, int amount) throws PropertyException {
		
		
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
