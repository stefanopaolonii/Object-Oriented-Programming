package futsal;

import java.util.*;

/**
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields {
	Map<Integer,Field> fieldsMap= new TreeMap<>();
    Map<Integer,Associate> associatesMap= new TreeMap<>();
    private int fieldCounter=1;
    private int associateCounter=0;
    private String openingTime;
    private String closingTime;
    public static class Features {
        public final boolean indoor; // otherwise outdoor
        public final boolean heating;
        public final boolean ac;
        public Features(boolean i, boolean h, boolean a) {
            this.indoor=i; this.heating=h; this.ac = a;
        }
    }
    
	
    public void defineFields(Features... features) throws FutsalException {
        for(Features feature: features){
            if((feature.ac || feature.heating) && !feature.indoor) throw new FutsalException();
            fieldsMap.put(fieldCounter, new Field(fieldCounter, feature));
            fieldCounter++;
        }
    }
    
    public long countFields() {
        return  (long) fieldsMap.size();
    }

    public long countIndoor() {
        return (long) fieldsMap.values().stream().filter(field-> field.getFeature().indoor).count();
    }
    
    public String getOpeningTime() {
        return openingTime;
    }
    
    public void setOpeningTime(String time) {
        this.openingTime=time;
    }
    
    public String getClosingTime() {
        return closingTime;
    }
    
    public void setClosingTime(String time) {
        this.closingTime=time;
    }

    public int newAssociate(String first, String last, String mobile) {
        associateCounter++;
        associatesMap.put(associateCounter, new Associate(associateCounter, first, last, mobile));
        return associateCounter;
    }
    
    public String getFirst(int associate) throws FutsalException {
        if(!associatesMap.containsKey(associate)) throw new FutsalException();
        return associatesMap.get(associate).getFirst();
    }
    
    public String getLast(int associate) throws FutsalException {
        if(!associatesMap.containsKey(associate)) throw new FutsalException();
        return associatesMap.get(associate).getLast();
    }
    
    public String getPhone(int associate) throws FutsalException {
        if(!associatesMap.containsKey(associate)) throw new FutsalException();
        return associatesMap.get(associate).getPhoneNo();
    }
    
    public int countAssociates() {
        return associatesMap.size();
    }
    
    public void bookField(int field, int associate, String time) throws FutsalException {
        if(!fieldsMap.containsKey(field)) throw new FutsalException("1");
        if(!associatesMap.containsKey(associate)) throw new FutsalException("2");
        if((toMinutes(time)-toMinutes(openingTime))%60!=0) throw new FutsalException("3");
        fieldsMap.get(field).addBookings(time, associatesMap.get(associate));
    }

    private int toMinutes(String time){
        String[] parts=time.split(":");
        return (Integer.parseInt(parts[0])*60) + Integer.parseInt(parts[1]);
    }

    public boolean isBooked(int field, String time) {
        return fieldsMap.get(field).getBookingsMap().keySet().stream().anyMatch(bookedtime-> bookedtime.equals(time));
    }
    

    public int getOccupation(int field) {
        return -1;
    }
    
    public List<FieldOption> findOptions(String time, Features required){
        return null;
    }
    
    public long countServedAssociates() {
        return -1;
    }
    
    public Map<Integer,Long> fieldTurnover() {
        return null;
    }
    
    public double occupation() {
        return -1;
    }
    
 }
