package utility;

import java.util.*;


public class ContractImpl implements Contract{
    private final String id;
    private User user;
    private ServicePoint servicepoint;
    private List<Reading> readingsList = new ArrayList<>();
    public ContractImpl(String id, User user, ServicePoint servicepoint) {
        this.id = id;
        this.user = user;
        this.servicepoint = servicepoint;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public ServicePoint getServicePoint() {
        return servicepoint;
    }
    @Override
    public User getUser() {
        return user;
    }
    @Override
    public void addReading(String mcode,String date,double value){
        readingsList.add(new Reading(this.id, mcode, date, value));
    }
    @Override
    public List<Reading> getReadings(){
        return readingsList;
    }
}
