package med;

import java.util.*;

public class Doctor {
    private final String id;
    private final String name;
    private final String surname;
    private final String speciality;
    private List<Slot> slotsList= new ArrayList<>();
    public Doctor(String id, String name, String surname, String speciality) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.speciality = speciality;
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
    public String getSpeciality() {
        return speciality;
    }
    public void addSlot(Slot slot){
        slotsList.add(slot);
    }
    public List<Slot> getSlotsList() {
        return slotsList;
    }
}
