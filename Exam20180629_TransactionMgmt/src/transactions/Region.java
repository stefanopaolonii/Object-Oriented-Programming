package transactions;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Region {
    private final String name;
    private Set<String> placesSet= new HashSet<>();
    public Region(String name, Set<String> placeNames) {
        this.name = name;
        this.placesSet=placeNames;
    }
    public String getName() {
        return name;
    }
    public Set<String> getPlacesSet() {
        return placesSet;
    }
    
}
