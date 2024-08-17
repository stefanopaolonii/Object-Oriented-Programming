package emergency;

import java.util.*;

public class Department {
    private final String name;
    private final int maxpatients;
    private List<Patient> patients = new ArrayList<>();
    public Department(String name, int maxpatients) {
        this.name = name;
        this.maxpatients = maxpatients;
    }
    public String getName() {
        return name;
    }
    public int getMaxpatients() {
        return maxpatients;
    }
    public List<Patient> getPatients() {
        return patients;
    }

    public boolean isAvailable(){
        return patients.size()<maxpatients;
    }
    
    public void addPatient(Patient patient){
        patients.add(patient);
    }
}
