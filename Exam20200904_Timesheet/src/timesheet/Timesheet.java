package timesheet;

import java.util.*;
import java.util.stream.Collectors;

public class Timesheet {
	public enum Status{OPEN,COMPLETE};
	private Set<Integer> holidaysSet= new HashSet<>();
	private int weekDay=-1;
	private Map<String,Project> projectsMap= new HashMap<>();
	private Map<String,Profile> profilesMap= new HashMap<>();
	private Map<String,Worker> workersMap= new HashMap<>();
	private List<Report> reportsList= new ArrayList<>();
	private int profileCounter=0;
	private int workerCounter=0;
	// R1
	public void setHolidays(int... holidays) {
		if(!holidaysSet.isEmpty()) return;
		for(int ho:holidays) if(ho>0) holidaysSet.add(ho);
	}
	
	public boolean isHoliday(int day) {
		return holidaysSet.contains(day);
	}
	
	public void setFirstWeekDay(int weekDay) throws TimesheetException {
		if(weekDay<0 || weekDay>6) throw new TimesheetException();
		this.weekDay=weekDay;
	}
	
	public int getWeekDay(int day) throws TimesheetException {
		if(day<=0) throw new TimesheetException();
		int firstday=1;
		if(weekDay!=-1) firstday=weekDay;
	    return ((day-1)%7) +firstday;
	}
	
	// R2
	public void createProject(String projectName, int maxHours) throws TimesheetException {
		if(maxHours<0) throw new TimesheetException();
		projectsMap.put(projectName, new Project(projectName, maxHours));
	}
	
	public List<String> getProjects() {
        return projectsMap.values().stream().sorted(Comparator.comparingInt(Project::getMaxHours).reversed().thenComparing(Project::getName)).map(Project::getName).collect(Collectors.toList());
	}
	
	public void createActivity(String projectName, String activityName) throws TimesheetException {
		if(!projectsMap.containsKey(projectName)) throw new TimesheetException();
		projectsMap.get(projectName).addActivity(new Activity(activityName));
	}
	
	public void closeActivity(String projectName, String activityName) throws TimesheetException {
		if(!projectsMap.containsKey(projectName)) throw new TimesheetException();
		Project searchedProject= projectsMap.get(projectName);
		if(!searchedProject.getActivityMap().containsKey(activityName)) throw new TimesheetException();
		searchedProject.getActivityMap().get(activityName).setStatus(Status.COMPLETE);
	}
	
	public List<String> getOpenActivities(String projectName) throws TimesheetException {
		if(!projectsMap.containsKey(projectName)) throw new TimesheetException();
        return projectsMap.get(projectName).getActivityMap().values().stream().map(Activity::getName).sorted().collect(Collectors.toList());
	}

	// R3
	public String createProfile(int... workHours) throws TimesheetException {
		if(workHours.length!=7) throw new TimesheetException();
		String code=String.format("P%d",++profileCounter);
		profilesMap.put(code, new Profile(code, workHours));
        return code;
	}
	
	public String getProfile(String profileID) throws TimesheetException {
		if(!profilesMap.containsKey(profileID)) throw new TimesheetException();
        return profilesMap.get(profileID).toString();
	}
	
	public String createWorker(String name, String surname, String profileID) throws TimesheetException {
		if(!profilesMap.containsKey(profileID)) throw new TimesheetException();
		String code=String.format("W%d", ++workerCounter);
		workersMap.put(code, new Worker(code, name, surname, profilesMap.get(profileID)));
        return code;
	}
	
	public String getWorker(String workerID) throws TimesheetException {
		if(!workersMap.containsKey(workerID)) throw new TimesheetException();
        return workersMap.get(workerID).toString();
	}
	
	// R4
	public void addReport(String workerID, String projectName, String activityName, int day, int workedHours) throws TimesheetException {
		if(!workersMap.containsKey(workerID)) throw new TimesheetException();
		if(day<=0 || holidaysSet.contains(day)) throw new TimesheetException();
		if(workedHours<0) throw new TimesheetException();
		Worker searchedwWorker=workersMap.get(workerID);
		if(workedHours>searchedwWorker.getProfile().getHoursMap().get(getWeekDay(day))) throw new TimesheetException();
		if(!projectsMap.containsKey(projectName)) throw new TimesheetException();
		Project searchedProject=projectsMap.get(projectName);
		if(!searchedProject.getActivityMap().containsKey(activityName)) throw new TimesheetException();
		if(searchedProject.getWorkedHours()+workedHours>searchedProject.getMaxHours()) throw new TimesheetException();
		Activity searchedActivity= searchedProject.getActivityMap().get(activityName);
		if(searchedActivity.getStatus()==Status.COMPLETE) throw new TimesheetException();
		reportsList.add(new Report(searchedwWorker, searchedProject, searchedActivity, day, workedHours));
		searchedProject.setWorkedHours(searchedProject.getWorkedHours()+workedHours);
	}
	
	public int getProjectHours(String projectName) throws TimesheetException {
        if(!projectsMap.containsKey(projectName)) throw new TimesheetException();
		return projectsMap.get(projectName).getWorkedHours();
	}
	
	public int getWorkedHoursPerDay(String workerID, int day) throws TimesheetException {
		if(!workersMap.containsKey(workerID)) throw new TimesheetException();
		if(day<=0) throw new TimesheetException();
        return reportsList.stream().filter(report->report.getWorker().getId().equals(workerID) && report.getDay()==day).mapToInt(Report::getWorkedHours).sum();
	}
	
	// R5
	public Map<String, Integer> countActivitiesPerWorker() {
        return null;
	}
	
	public Map<String, Integer> getRemainingHoursPerProject() {
        return null;
	}
	
	public Map<String, Map<String, Integer>> getHoursPerActivityPerProject() {
        return null;
	}
}
