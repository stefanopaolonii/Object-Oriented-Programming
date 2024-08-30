package ticketing;

import java.util.*;

import ticketing.IssueManager.UserClass;

public class User {
    private final String username;
    private Set<UserClass> classes = new HashSet<>();
    private Map<Integer,Ticket> closedticketMap= new HashMap<>();
    
    public User(String username, Set<UserClass> classes) {
        this.username = username;
        this.classes = classes;
    }
    public User(String username,UserClass... classes) {
        this.username = username;
        this.classes.addAll(Arrays.asList(classes));
    }
    
    public String getUsername() {
        return username;
    }
    public Set<UserClass> getClasses() {
        return classes;
    }
    public Map<Integer, Ticket> getClosedticketMap() {
        return closedticketMap;
    }
    public void addTicket(Ticket ticket){
        closedticketMap.put(ticket.getId(), ticket);
    }
}
