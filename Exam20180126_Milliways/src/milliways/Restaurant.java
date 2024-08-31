package milliways;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
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
        return hallsMap.values().stream().flatMap(hall->hall.getPartySet().stream().flatMap(party->party.getCompanionsMap().entrySet().stream())).collect(Collectors.groupingBy(entry->entry.getKey(),Collectors.summingInt(entry->entry.getValue())));
	}

	public List<String> statFacility() {
        return hallsMap.values().stream().flatMap(hall->hall.getFacilities().stream()).collect(Collectors.groupingBy(facility->facility,Collectors.counting())).entrySet().stream().sorted(Comparator.<Map.Entry<String,Long>>comparingLong(Map.Entry::getValue).reversed().thenComparing(entry->entry.getKey())).map(Map.Entry::getKey).collect(Collectors.toList());
	}
	
	public Map<Integer,List<Integer>> statHalls() {
        return hallsMap.values().stream().collect(Collectors.groupingBy(hall->hall.getNumFacilities(),TreeMap::new,Collectors.mapping(hall->hall.getId(), Collectors.collectingAndThen(Collectors.toList(), list->{Collections.sort(list); return list;}))));
	}

}
