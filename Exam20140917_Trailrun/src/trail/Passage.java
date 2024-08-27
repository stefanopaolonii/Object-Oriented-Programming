package trail;

public class Passage {
    private Delegate delegate;
    private Location location;
    private Runner runner;
    private long time;
    
    public Passage(Delegate delegate, Location location, Runner runner, long time) {
        this.delegate = delegate;
        this.location = location;
        this.runner = runner;
        this.time = time;
    }

    public Delegate getDelegate() {
        return delegate;
    }
    public Location getLocation() {
        return location;
    }
    public Runner getRunner() {
        return runner;
    }
    public long getTime() {
        return time;
    }
    
}
