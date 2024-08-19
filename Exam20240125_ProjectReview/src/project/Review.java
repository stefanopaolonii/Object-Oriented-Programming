package project;

import java.util.*;

public class Review {
    private final String id;
    private final String title;
    private final String topic;
    private final String group;
    private List<Slot> slotsList= new ArrayList<>();
    public Review(String id, String title, String topic, String group) {
        this.id = id;
        this.title = title;
        this.topic = topic;
        this.group = group;
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
    
}
