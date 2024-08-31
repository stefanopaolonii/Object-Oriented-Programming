package milliways;

import java.util.*;

public class Hall {
	private final int id;
	private Set<String> facilitesSet= new HashSet<>();
	private Set<Party> partySet= new HashSet<>();
	
	public Hall(int id) {
		this.id = id;
	}

	public int getId() {
		return -1;
	}

	public void addFacility(String facility) throws MilliwaysException {
	}

	public List<String> getFacilities() {
        return null;
	}
	
	int getNumFacilities(){
        return -1;
	}

	public boolean isSuitable(Party party) {
		return false;
	}

}
