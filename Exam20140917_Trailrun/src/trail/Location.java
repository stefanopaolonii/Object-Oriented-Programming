package trail;

import java.util.*;

public class Location {
    private final String name;
    private int ordernum;
    private List<Delegate> delegatesList= new ArrayList<>();
    
    public Location(String name, int ordernum) {
        this.name = name;
        this.ordernum = ordernum;
    }

    public String getName(){
        return name;
    }
    public int getOrderNum(){
        return ordernum;
    }
    public List<Delegate> getDelegatesList() {
        return delegatesList;
    }
    public void addDelegate(Delegate delegate){
        delegatesList.add(delegate);
    }
}
