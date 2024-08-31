package milliways;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Restaurant {
	private Map<String,Race> racesMap= new HashMap<>();
	private Map<Integer,Hall> hallsMap= new HashMap<>();
    public Restaurant() {
	}
	
	public Race defineRace(String name) throws MilliwaysException{
		if(racesMap.containsKey(name)) throw new MilliwaysException();
		Race newRace= new Race(name);
		racesMap.put(name, newRace);
	    return newRace;
	}
	
	public Party createParty() {
	    return new Party();
	}
	
	public Hall defineHall(int id) throws MilliwaysException{
		if(hallsMap.containsKey(id)) throw new MilliwaysException();
		Hall newHall= new Hall(id);
		hallsMap.put(id, newHall);
	    return newHall;
	}

	public List<Hall> getHallList() {
		return hallsMap.values().stream().collect(Collectors.toList());
	}

	public Hall seat(Party party, Hall hall) throws MilliwaysException {
		if(!hallsMap.containsKey(hall.getId())) throw new MilliwaysException();
		if(!hall.isSuitable(party)) throw new MilliwaysException();
		hall.addParty(party);
        return hall;
	}

	public Hall seat(Party party) throws MilliwaysException {
		Hall searchedHall=hallsMap.values().stream().filter(hall->hall.isSuitable(party)).findFirst().orElse(null);
		if(searchedHall==null) throw new MilliwaysException();
		searchedHall.addParty(party);
        return searchedHall;
	}

	public Map<Race, Integer> statComposition() {
        return null;
	}

	public List<String> statFacility() {
        return null;
	}
	
	public Map<Integer,List<Integer>> statHalls() {
        return null;
	}

}
