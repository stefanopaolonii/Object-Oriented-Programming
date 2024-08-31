package milliways;

import java.util.*;
import java.util.stream.Collectors;

public class Hall {
	private final int id;
	private Set<String> facilitesSet= new HashSet<>();
	private Set<Party> partySet= new HashSet<>();

	public Hall(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void addFacility(String facility) throws MilliwaysException {
		if(facilitesSet.contains(facility)) throw new MilliwaysException();
		facilitesSet.add(facility);
	}

	public List<String> getFacilities() {
        return facilitesSet.stream().sorted().collect(Collectors.toList());
	}
	
	int getNumFacilities(){
        return facilitesSet.size();
	}

	public boolean isSuitable(Party party) {
		return facilitesSet.containsAll(party.getRequirements());
	}
	public void addParty(Party party){
		partySet.add(party);
	}
	public Set<Party> getPartySet() {
		return partySet;
	}
}
