package project;

public class Slot {
    private final String date;
    private final String start;
    private final String end;
    public Slot(String date, String start, String end) {
        this.date = date;
        this.start = start;
        this.end = end;
    }
    public String getDate() {
        return date;
    }
    public String getStart() {
        return start;
    }
    public String getEnd() {
        return end;
    }
    public boolean overlaps(Slot other){
        return this.getDate().equals(other.getDate()) && this.getStart().compareTo(other.getEnd())<0 && other.getStart().compareTo(this.getEnd())<0;
    }

    private int timeinMinutes(String time){
        String[] parts=time.split(":");
        return Integer.parseInt(parts[0])*60+Integer.parseInt(parts[1]);
    }

    public double slotinHour(){
        return (timeinMinutes(end)-timeinMinutes(start))/ (double) 60;
    }
    
}
