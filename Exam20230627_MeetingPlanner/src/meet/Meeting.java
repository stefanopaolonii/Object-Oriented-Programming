package meet;

import java.util.*;

public class Meeting {
    private final String id;
    private String title;
    private String topic;
    private String category;
    private boolean poolopen;
    private List<Slot> slotsList = new ArrayList<>();
    private List<Preference> preferencesList= new ArrayList<>();
    public Meeting(String id, String title, String topic, String category) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.category = category;
        this.poolopen=false;
    }
    public String getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getTopic() {
        return topic;
    }
    public String getCategory() {
        return category;
    }
    public boolean isPoolopen() {
        return poolopen;
    }
    public List<Slot> getSlotsList() {
        return slotsList;
    }
    public List<Preference> getPreferencesList() {
        return preferencesList;
    }
    public void addSlot(Slot slot){
        slotsList.add(slot);
    }
    public void addPreference(Preference preference){
        preferencesList.add(preference);
    }

}
