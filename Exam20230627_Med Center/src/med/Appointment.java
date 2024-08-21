package med;

public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private final String id;
    private Slot slot;
    private boolean completed;
    public Appointment(Patient patient, Doctor doctor, String id, Slot slot) {
        this.patient = patient;
        this.doctor = doctor;
        this.id = id;
        this.slot = slot;
        this.completed=false;
    }
    public Patient getPatient() {
        return patient;
    }
    public Doctor getDoctor() {
        return doctor;
    }
    public String getId() {
        return id;
    }
    public Slot getSlot() {
        return slot;
    }
    public boolean isCompleted() {
        return completed;
    }
    public void setCompleted(boolean completed) {
        this.completed = completed;
    }
}
