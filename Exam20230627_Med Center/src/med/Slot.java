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
    @Override
    public boolean equals(Object obj){
        if(obj==this) return true;
        if(obj==null || obj.getClass()!=this.getClass()) return false;
        Slot other= (Slot) obj;
        if(this.date.equals(other.date)){
            if(this.startTime.equals(other.getStartTime()) && this.endTime.equals(other.endTime)) return true;
        }

        return false;
    }
    public String getTime(){
        return startTime+"-"+endTime;
    }
}
