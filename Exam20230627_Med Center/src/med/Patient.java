package med;

public class Patient {
    private final String ssn;
    private final String name;
    private final String surname;
    private boolean accepted;
    public Patient(String ssn, String name, String surname) {
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
        this.accepted=false;
    }
    public String getSsn() {
        return ssn;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public boolean isAccepted() {
        return accepted;
    }
    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }
}
