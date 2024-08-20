package ski;

import java.util.*;

public class Parking {
    private final String name;
    private int capacity;
    private List<Lift> liftsList= new ArrayList<>();
    public Parking(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
    }
    public String getName() {
        return name;
    }
    public int getCapacity() {
        return capacity;
    }
    public List<Lift> getLiftsList() {
        return liftsList;
    }
    public void addLift(Lift lift){
        liftsList.add(lift);
    }
}
