package timesheet;

import java.util.*;

public class Profile {
    private final String id;
    private Map<Integer,Integer> hoursMap= new HashMap<>();
    public Profile(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public Map<Integer, Integer> getHoursMap() {
        return hoursMap;
    }
}
