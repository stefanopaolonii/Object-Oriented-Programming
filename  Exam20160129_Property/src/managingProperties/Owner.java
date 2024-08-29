package managingProperties;

import java.util.*;

public class Owner {
    private final String owner;
    private Map<String,Apartment> apartmentsMap= new HashMap<>();
    public Owner(String owner) {
        this.owner = owner;
    }
    public String getOwner() {
        return owner;
    }
    public Map<String, Apartment> getApartmentsMap() {
        return apartmentsMap;
    }
    public void addApartment(Apartment ap){
        apartmentsMap.put(ap.getId(), ap);
    }
    
}
