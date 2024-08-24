package meet;

public class Preference {
    private String email;
    private String name;
    private String surname;
    private String meeting;
    private Slot slot;
    public Preference(String email, String name, String surname, String meeting, Slot slot) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.meeting = meeting;
        this.slot = slot;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getMeeting() {
        return meeting;
    }
    public Slot getSlot() {
        return slot;
    }
}
