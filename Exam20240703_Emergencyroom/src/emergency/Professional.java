package emergency;

import java.util.*;

public class Professional {
    private final String id;
    private final String name;
    private final String surname;
    private String period;
    private String specialization;
    private Set<Patient> patients = new HashSet<>();
    public Professional(String id, String name, String surname, String period, String specialization) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.period = period;
        this.specialization = specialization;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSpecialization() {
        return specialization;
    }

    public String getPeriod() {
        return period;
    }

    public String getWorkingHours() {
        return null;
    }

    public void addPatient(Patient patient){
        patients.add(patient);
    }

    public Collection<Patient> getPatients(){
        return patients;
    }
}
