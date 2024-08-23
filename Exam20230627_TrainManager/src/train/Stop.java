package train;

public class Stop {
    private int stop;
    private String station;
    public Stop(int stop, String station) {
        this.stop = stop;
        this.station = station;
    }
    public int getStop() {
        return stop;
    }
    public String getStation() {
        return station;
    }
}
