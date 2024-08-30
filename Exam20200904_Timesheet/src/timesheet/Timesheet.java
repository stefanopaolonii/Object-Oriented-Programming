package timesheet;

import java.util.*;

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
	}
	
	public List<String> getProjects() {
        return null;
	}
	
	public void createActivity(String projectName, String activityName) throws TimesheetException {
	}
	
	public void closeActivity(String projectName, String activityName) throws TimesheetException {
	}
	
	public List<String> getOpenActivities(String projectName) throws TimesheetException {
        return null;
	}

	// R3
	public String createProfile(int... workHours) throws TimesheetException {
        return null;
	}
	
	public String getProfile(String profileID) throws TimesheetException {
        return null;
	}
	
	public String createWorker(String name, String surname, String profileID) throws TimesheetException {
        return null;
	}
	
	public String getWorker(String workerID) throws TimesheetException {
        return null;
	}
	
	// R4
	public void addReport(String workerID, String projectName, String activityName, int day, int workedHours) throws TimesheetException {
	}
	
	public int getProjectHours(String projectName) throws TimesheetException {
        return -1;
	}
	
	public int getWorkedHoursPerDay(String workerID, int day) throws TimesheetException {
        return -1;
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
