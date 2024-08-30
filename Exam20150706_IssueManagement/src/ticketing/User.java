package ticketing;

import java.util.*;

import ticketing.IssueManager.UserClass;
import ticketing.Ticket.State;

public class User {
    private final String username;
    private Set<UserClass> classes = new HashSet<>();
    private Map<Integer,Ticket> ticketMap= new HashMap<>();
    
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
    public Map<Integer, Ticket> getticketMap() {
        return ticketMap;
    }
    public void addTicket(Ticket ticket){
        ticketMap.put(ticket.getId(), ticket);
    }
    public int getClosedTicket(){
        return (int)ticketMap.values().stream().filter(ticket->ticket.getState()==State.Closed).count();
    }
}
