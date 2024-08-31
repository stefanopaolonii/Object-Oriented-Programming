package milliways;

import java.util.*;
import java.util.stream.Collectors;

public class Party {
	private Map<Race,Integer> companionsMap= new HashMap<>();
	public Party() {
	}

	public void addCompanions(Race race, int num) {
		if(!companionsMap.containsKey(race)) companionsMap.put(race,num);
		else companionsMap.put(race, companionsMap.get(race)+num);
	}

	public int getNum() {
        return companionsMap.values().stream().mapToInt(Integer::intValue).sum();
	}

	public int getNum(Race race) {
		if(!companionsMap.containsKey(race)) return -1;
	    return companionsMap.get(race);
	}

	public List<String> getRequirements() {
        return companionsMap.keySet().stream().flatMap(race->race.getRequirements().stream()).distinct().sorted().collect(Collectors.toList());
	}

    public Map<String,Integer> getDescription(){
        return companionsMap.entrySet().stream().collect(Collectors.toMap(entry->entry.getKey().getName(), Map.Entry::getValue));
    }
	public Map<Race, Integer> getCompanionsMap() {
		return companionsMap;
	}

}
