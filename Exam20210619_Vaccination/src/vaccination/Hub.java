package vaccination;

public class Hub {
    private String name;
    private int doctors;
    private int nurses;
    private int other;
    public Hub(String name) {
        this.name = name;
        this.doctors=0;
        this.nurses=0;
        this.other=0;
    }
    public String getName() {
        return name;
    }
    public int getDoctors() {
        return doctors;
    }
    public int getNurses() {
        return nurses;
    }
    public int getOther() {
        return other;
    }
    public void setStaff(int doctors, int nurses, int other){
        this.doctors=doctors;
        this.nurses=nurses;
        this.other=other;
    }
}
