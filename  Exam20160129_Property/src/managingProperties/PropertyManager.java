package managingProperties;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;

public class PropertyManager {

	/**
	 * Add a new building 
	 */
	public void addBuilding(String building, int n) throws PropertyException {
	}

	public void addOwner(String owner, String... apartments) throws PropertyException {
		
		
	}

	/**
	 * Returns a map ( number of apartments => list of buildings ) 
	 * 
	 */
	public SortedMap<Integer, List<String>> getBuildings() {
		
		return null;
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
