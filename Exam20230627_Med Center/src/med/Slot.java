package med;

public class Slot {
    private String date;
    private String startTime;
    private String endTime;
    private int duration;
    public Slot(String date, String startTime, String endTime, int duration) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
    }
    public String getDate() {
        return date;
    }
    public String getStartTime() {
        return startTime;
    }
    public String getEndTime() {
        return endTime;
    }
    public int getDuration() {
        return duration;
    }
}
