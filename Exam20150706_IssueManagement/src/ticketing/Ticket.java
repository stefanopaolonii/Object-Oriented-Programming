package ticketing;

/**
 * Class representing the ticket linked to an issue or malfunction.
 * 
 * The ticket is characterized by a severity and a state.
 */
public class Ticket {
    private final int id;
    private User user;
    private Component component;
    private Severity severity;
    private State state;
    private String username;
    private String solution; 
    private String description;
    public Ticket(int id, User user, Component component, Severity severity, String description) {
        this.id = id;
        this.user = user;
        this.component = component;
        this.severity = severity;
        this.description = description;
        this.state=State.Open;
    }

    /**
     * Enumeration of possible severity levels for the tickets.
     * 
     * Note: the natural order corresponds to the order of declaration
     */
    public enum Severity { Blocking, Critical, Major, Minor, Cosmetic };
    
    /**
     * Enumeration of the possible valid states for a ticket
     */
    public static enum State { Open, Assigned, Closed }
    
    public int getId(){
        return id;
    }

    public String getDescription(){
        return solution;
    }
    
    public Severity getSeverity() {
        return severity;
    }

    public String getAuthor(){
        return user.getUsername();
    }
    
    public String getComponent(){
        return component.getName();
    }
    
    public State getState(){
        return state;
    }
    
    public String getSolutionDescription() throws TicketException {
        if(state!=State.Closed) throw new TicketException();
        return solution;
    }
    public void setUsername(String username) {
        this.username = username;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }
    public String getUsername() {
        return username;
    }
    public void setState(State state) {
        this.state = state;
    }
}
