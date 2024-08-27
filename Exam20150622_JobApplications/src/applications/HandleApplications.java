package applications;

import java.util.*;

public class HandleApplications {
	private Map<String,Skill> skillsMap= new HashMap<>();
	private Map<String,Position> positionsMap= new HashMap<>();
	
	public void addSkills(String... names) throws ApplicationException {
		for(String skill:names){
			if(skillsMap.containsKey(skill)) throw new ApplicationException();
			skillsMap.put(skill, new Skill(skill));
		}
	}
	public void addPosition(String name, String... skillNames) throws ApplicationException {
		if(positionsMap.containsKey(name)) throw new ApplicationException();
		if(!skillsMap.keySet().containsAll(Arrays.asList(skillNames))) throw new ApplicationException();
		positionsMap.put(name, new Position(name, skillNames));
	}
	public Skill getSkill(String name) {return skillsMap.get(name);}
	public Position getPosition(String name) {return positionsMap.get(name);}
	
	public void addApplicant(String name, String capabilities) throws ApplicationException {
		
	}
	public String getCapabilities(String applicantName) throws ApplicationException {
		return null;
	}
	
	public void enterApplication(String applicantName, String positionName) throws ApplicationException {
		
	}
	
	public int setWinner(String applicantName, String positionName) throws ApplicationException {
		return 0;
	}
	
	public SortedMap<String, Long> skill_nApplicants() {
		return null;
	}
	public String maxPosition() {
		return null;
	}
}

