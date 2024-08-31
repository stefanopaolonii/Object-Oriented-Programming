package transactions;

import java.util.*;

public class Carrier {
    private final String name;
    private Set<Region> regionsSet= new HashSet<>();
    public Carrier(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Set<Region> getRegionsSet() {
        return regionsSet;
    }
    public void addRegion(Region region){
        if(region==null) return;
        regionsSet.add(region);
    }
}
