package futsal;

import futsal.Fields.Features;
import java.util.*;

public class Field {
    private final int id;
    private Features feature;
    private Map<String,Associate> bookingsMap= new TreeMap<>();
    
    public Field(int id, Features feature) {
        this.id = id;
        this.feature = feature;
    }
    public int getId() {
        return id;
    }
    public Features getFeature() {
        return feature;
    }
    public Map<String, Associate> getBookingsMap() {
        return bookingsMap;
    }
    public void addBookings(String time,Associate associate){
        bookingsMap.put(time, associate);
    }
    
}
