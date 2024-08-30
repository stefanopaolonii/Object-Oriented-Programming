package timesheet;

public class Report {
    private Worker worker;
    private Project project;
    private Activity activity;
    private int day;
    private int workedHours;
    public Report(Worker worker, Project project, Activity activity, int day, int workedHours) {
        this.worker = worker;
        this.project = project;
        this.activity = activity;
        this.day = day;
        this.workedHours = workedHours;
    }
    public Worker getWorker() {
        return worker;
    }
    public Project getProject() {
        return project;
    }
    public Activity getActivity() {
        return activity;
    }
    public int getDay() {
        return day;
    }
    public int getWorkedHours() {
        return workedHours;
    }
}
