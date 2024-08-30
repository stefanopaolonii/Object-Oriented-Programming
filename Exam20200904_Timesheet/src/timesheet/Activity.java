package timesheet;

import timesheet.Timesheet.Status;

public class Activity {
    private final String name;
    private Status status;
    public Activity(String name) {
        this.name = name;
        this.status=Status.OPEN;
    }
    public String getName() {
        return name;
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
