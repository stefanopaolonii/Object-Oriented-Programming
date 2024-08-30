package timesheet;

import java.util.*;

public class Profile {
    private final String id;
    private Map<Integer,Integer> hoursMap= new HashMap<>();
    public Profile(String id,int...workHours) {
        this.id = id;
        addHours(workHours);
    }
    public String getId() {
        return id;
    }
    public Map<Integer, Integer> getHoursMap() {
        return hoursMap;
    }
    public void addHours(int... workHours){
        for(int i=0;i<workHours.length;i++){
            if(workHours[i]>8) hoursMap.put(i, 8);
            else hoursMap.put(i, workHours[i]);
        }
    }
    @Override
    public String toString(){
        return "Sun: "+hoursMap.get(0)+"; Mon: "+hoursMap.get(1)+"; Tue: "+hoursMap.get(2)+"; Wed: "+hoursMap.get(3)+"; Thu: "+hoursMap.get(4)+"; Fri: "+hoursMap.get(5)+"; Sat: "+hoursMap.get(6);
    }
}
