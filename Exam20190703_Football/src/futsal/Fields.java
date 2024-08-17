package futsal;

import java.util.*;
import java.util.stream.Collectors;

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
        return fieldsMap.get(field).getOccupation();
    }
    
    public List<FieldOption> findOptions(String time, Features required){
        return fieldsMap.values().stream().filter(field->!field.getBookingsMap().keySet().contains(time)).filter(field->{Features feature=field.getFeature(); return feature.indoor==required.indoor ;}).sorted(Comparator.comparingInt(Field::getOccupation).reversed().thenComparing(Comparator.comparing(Field::getField))).collect(Collectors.toList());
    }
    
    public long countServedAssociates() {
        return fieldsMap.values().stream().flatMap(field->field.getBookingsMap().values().stream()).distinct().count();
    }
    
    public Map<Integer,Long> fieldTurnover() {
        return fieldsMap.values().stream().collect(Collectors.toMap(Field::getField,field -> (long) field.getOccupation()));
    }
    
    public double occupation() {
        return ((double)fieldsMap.values().stream().mapToInt(Field::getOccupation).sum()/(((toMinutes(closingTime)-toMinutes(openingTime))/60)*fieldsMap.size()));
    }
    
 }
