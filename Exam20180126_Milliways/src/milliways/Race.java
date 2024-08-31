package milliways;

import java.util.*;
import java.util.stream.Collectors;

public class Race {
    private final String name;
	private List<String> requirementsList= new ArrayList<>();

	public Race(String name) {
		this.name = name;
	}

	public void addRequirement(String requirement) throws MilliwaysException {
		if(requirementsList.contains(requirement)) throw new MilliwaysException();
		requirementsList.add(requirement);
	}
	
	public List<String> getRequirements() {
        return requirementsList.stream().sorted().collect(Collectors.toList());
	}
	
	public String getName() {
        return name;
	}
}
