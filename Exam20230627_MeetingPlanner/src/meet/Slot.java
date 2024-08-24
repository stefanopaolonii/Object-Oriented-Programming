package meet;

public class Slot {
    private String date;
    private String start;
    private String end;
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
    @Override
    public String toString(){
        return start+"-"+end;
    }
    private int getinM(String time){
        String[] parts=time.split(":");
        return Integer.parseInt(parts[0])*60+Integer.parseInt(parts[1]);
    }
    public int slotinH(){
        return (getinM(end)-getinM(start))/60;
    }
    public boolean overlap(Slot other){
        return start.compareTo(other.getEnd())<0 && other.getStart().compareTo(end)<0;
    }
}
