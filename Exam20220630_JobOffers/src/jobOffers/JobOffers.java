package jobOffers; 
import java.util.*;
import java.util.stream.Collectors;


public class JobOffers  {
	Set<String> skillsSet= new HashSet<>();
	Map<String,Position> positionsMap= new HashMap<>();
	Map<String,Candidate> candidatesMap= new HashMap<>();
	List<Application> applicationsList= new ArrayList<>();
	public enum Status {SUBMITTED,ACCEPTED,REJECTED};
	Map<String,Consultant> consultantsMap= new HashMap<>();
//R1
	public int addSkills (String... skills) {
		skillsSet.addAll(Arrays.asList(skills));
		return skillsSet.size();
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		if(positionsMap.containsKey(position)) throw new JOException("");
		Position newPosition= new Position(position);
		for(String singolSkill:skillLevels){
			String[] parts= singolSkill.split(":");
			int level= Integer.parseInt(parts[1]);
			if(!skillsSet.contains(parts[0])) throw new JOException("");
			if(level<4 || level>8) throw new JOException("");
			newPosition.addSkill(parts[0], level);
		}
		positionsMap.put(position, newPosition);
		return (int) newPosition.getSkillslevelMap().entrySet().stream().mapToInt(entry->entry.getValue()).average().orElse(0);
	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		if(candidatesMap.containsKey(name)) throw new JOException("");
		if(!skillsSet.containsAll(Arrays.asList(skills))) throw new JOException("");
		candidatesMap.put(name, new Candidate(name, skills));
		return Arrays.asList(skills).size();
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		if(!candidatesMap.containsKey(candidate)) throw new JOException("");
		if(!positionsMap.keySet().containsAll(Arrays.asList(positions))) throw new JOException("");
		Candidate searchedCandidate=candidatesMap.get(candidate);
		for(String positionName: positions){
			Position searchedPosition=positionsMap.get(positionName);
			if(!searchedCandidate.getCandidateskills().containsAll(searchedPosition.getSkillslevelMap().keySet())) throw new JOException("");
			applicationsList.add(new Application(searchedCandidate.getName(), searchedPosition.getName()));
		}
		return applicationsList.stream().filter(app->app.getCandidate().equals(candidate)).sorted(Comparator.comparing(Application::getCandidate).thenComparing(Application::getPosition)).map(app->app.getCandidate()+":"+app.getPosition()).collect(Collectors.toList());
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
		return applicationsList.stream().collect(Collectors.groupingBy(Application::getPosition,TreeMap::new,Collectors.mapping(Application::getCandidate,Collectors.collectingAndThen(Collectors.toList(), list-> {list.sort(String::compareTo); return list;}))));
	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		if(consultantsMap.containsKey(name)) throw new JOException("");
		if(!skillsSet.containsAll(Arrays.asList(skills))) throw new JOException("");
		consultantsMap.put(name, new Consultant(name, skills));
		return Arrays.asList(skills).size();
	}
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {
		if(!consultantsMap.containsKey(consultant)) throw new JOException("");
		if(!candidatesMap.containsKey(candidate)) throw new JOException("");
		if(!consultantsMap.get(consultant).getSkillsSet().containsAll(candidatesMap.get(candidate).getCandidateskills())) throw new JOException("");
		for(String skill:skillRatings){
			String[] parts=skill.split(":");
			int level=Integer.parseInt(parts[1]);
			if(level<4 || level>10) throw new JOException("");
			candidatesMap.get(candidate).addRating(parts[0], level);
		}
		return (int) candidatesMap.get(candidate).getSkillsRating().entrySet().stream().mapToInt(entry-> entry.getValue()).average().orElse(0);
	}
	
//R4
	public List<String> discardApplications() {
		/*List<String> daritornare= new ArrayList<>();
		for(Application app:applicationsList){
			Candidate searchCandidate=candidatesMap.get(app.getCandidate());
			Position searchedPosition=positionsMap.get(app.getPosition());
				if(searchedPosition.getSkillslevelMap().entrySet().stream().anyMatch(entry->{Integer rating=searchCandidate.getSkillsRating().get(entry.getKey()); return rating==null || rating<entry.getValue();})){
					daritornare.add(app.getCandidate()+":"+app.getPosition());
				}
		}
		return daritornare;*/
		return applicationsList.stream().filter(app-> {Candidate searchedCandidate= candidatesMap.get(app.getCandidate()); Position searchedPosition= positionsMap.get(app.getPosition()); return searchedPosition.getSkillslevelMap().entrySet().stream().anyMatch(entry->{Integer rating=searchedCandidate.getSkillsRating().get(entry.getKey()); return rating==null || rating<entry.getValue();});}).sorted(Comparator.comparing(Application::getCandidate).thenComparing(Application::getPosition)).map(app->app.getCandidate()+":"+app.getPosition()).collect(Collectors.toList());
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		return applicationsList.stream().filter(app-> {Candidate searchedCandidate= candidatesMap.get(app.getCandidate()); Position searchedPosition= positionsMap.get(app.getPosition()); return searchedPosition.getSkillslevelMap().entrySet().stream().allMatch(entry->{Integer rating=searchedCandidate.getSkillsRating().get(entry.getKey()); return rating!=null && rating>=entry.getValue();});}).sorted(Comparator.comparing(Application::getCandidate)).map(app->app.getCandidate()).distinct().collect(Collectors.toList());
	}
	

	
}

		
