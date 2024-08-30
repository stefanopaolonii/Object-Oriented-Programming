package timesheet;

import java.util.*;

public class Project {
    private final String name;
    private int maxHours;
    private Map<String,Activity> activityMap= new HashMap<>();
    private int workedHours=0;
    public Project(String name, int maxHours) {
        this.name = name;
        this.maxHours = maxHours;
    }
    public String getName() {
        return name;
    }
    public int getMaxHours() {
        return maxHours;
    }
    public Map<String, Activity> getActivityMap() {
        return activityMap;
    }
    public void setMaxHours(int maxHours) {
        this.maxHours = maxHours;
    }
    public void addActivity(Activity activity){
        activityMap.put(activity.getName(), activity);
    }
    public int getWorkedHours() {
        return workedHours;
    }
    public void setWorkedHours(int workedHours) {
        this.workedHours = workedHours;
    }
    @Override
    public String toString(){
        return name+": "+maxHours;
    }
}
