package project;

import java.util.*;

import project.ReviewServer.Status;

public class Review {
    private final String id;
    private final String title;
    private final String topic;
    private final String group;
    private Status status;
    private List<Slot> slotsList= new ArrayList<>();
    private List<Preference> preferencesList= new ArrayList<>();
    public Review(String id, String title, String topic, String group) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.group = group;
        this.status=Status.CREATED;
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
    public String getGroup() {
        return group;
    }
    public List<Slot> getSlotsList() {
        return slotsList;
    }
    public void addSlot(Slot slot){
        slotsList.add(slot);
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public List<Preference> getPreferencesList() {
        return preferencesList;
    }
    public void addPreference(Preference preference){
        preferencesList.add(preference);
    }
}
