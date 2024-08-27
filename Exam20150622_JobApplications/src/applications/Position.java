package applications;

import java.util.*;
import java.util.stream.Collectors;

public class Position {
	private final String name;
	private List<String> skillsList= new ArrayList<>();
	private Map<String,Applicant> applicantsMap= new HashMap<>();
	private Applicant winner;
	public Position(String name,String ...skills) {
		this.name = name;
		this.winner=null;
		skillsList.addAll(Arrays.asList(skills));
	}

	public String getName() {
		return name;
	}
	
	public List<String> getApplicants() {
		return applicantsMap.keySet().stream().sorted().collect(Collectors.toList());
	}
	
	public String getWinner() {
		if(winner==null) return null;
		return winner.getName(); 
	}
}