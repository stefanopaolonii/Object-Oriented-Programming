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
    public double slotinH(){
        return (getinM(end)-getinM(start))/(double) 60;
    }
    public boolean overlap(Slot other){
        if(date.compareTo(other.getDate())!=0) return false;
        return start.compareTo(other.getEnd())<0 && other.getStart().compareTo(end)<0;
    }
    @Override
    public boolean equals(Object other){
        if(this==other) return true;
        if(other!=null && other.getClass()!=this.getClass()) return false;
        Slot slot2= (Slot) other;
        return date.compareTo(slot2.getDate())==0 && start.compareTo(slot2.getStart())==0 && end.compareTo(slot2.getEnd())==0;
    }
}
