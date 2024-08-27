package applications;

import java.util.*;


public class Skill {
	private final String name;
	private List<Position> positionsList= new ArrayList<>();
	public Skill(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	public List<Position> getPositions() {
		return positionsList;
	}
}