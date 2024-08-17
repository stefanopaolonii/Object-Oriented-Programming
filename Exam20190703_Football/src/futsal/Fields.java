package futsal;

import java.util.*;

/**
 * Represents a infrastructure with a set of playgrounds, it allows teams
 * to book, use, and  leave fields.
 *
 */
public class Fields {
	Map<Integer,Field> fieldsMap= new TreeMap<>();
    private int fieldCounter=1;
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
        return -1;
    }
    
    public String getFirst(int associate) throws FutsalException {
        return null;
    }
    
    public String getLast(int associate) throws FutsalException {
        return null;
    }
    
    public String getPhone(int associate) throws FutsalException {
        return null;
    }
    
    public int countAssociates() {
        return -1;
    }
    
    public void bookField(int field, int associate, String time) throws FutsalException {
    }

    public boolean isBooked(int field, String time) {
        return false;
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
