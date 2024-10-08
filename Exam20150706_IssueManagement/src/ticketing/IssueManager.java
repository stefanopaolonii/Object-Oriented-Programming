package ticketing;

import java.util.*;
import java.util.stream.Collectors;

import ticketing.Ticket.State;

public class IssueManager {
    private Map<String,User> usersMap= new HashMap<>();
    private Map<String,Component> componentsMap= new HashMap<>();
    private Map<Integer,Ticket> ticketsMap= new HashMap<>();
    private int ticketCounter=0;
    public IssueManager() {
    }

    /**
     * Eumeration of valid user classes
     */
    public static enum UserClass {
        /** user able to report an issue and create a corresponding ticket **/
        Reporter, 
        /** user that can be assigned to handle a ticket **/
        Maintainer }
    
    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username, UserClass... classes) throws TicketException {
        if(usersMap.containsKey(username)) throw new TicketException();
        if(classes.length==0) throw new TicketException();
        usersMap.put(username, new User(username, classes));
    }

    /**
     * Creates a new user
     * 
     * @param username name of the user
     * @param classes user classes
     * @throws TicketException if the username has already been created or if no user class has been specified
     */
    public void createUser(String username, Set<UserClass> classes) throws TicketException {
        if(usersMap.containsKey(username)) throw new TicketException();
        if(classes.isEmpty()) throw new TicketException();
        usersMap.put(username, new User(username, classes));
    }
   
    /**
     * Retrieves the user classes for a given user
     * 
     * @param username name of the user
     * @return the set of user classes the user belongs to
     */
    public Set<UserClass> getUserClasses(String username){
        if(!usersMap.containsKey(username)) return null;
        return usersMap.get(username).getClasses();
    }
    
    /**
     * Creates a new component
     * 
     * @param name unique name of the new component
     * @throws TicketException if a component with the same name already exists
     */
    public void defineComponent(String name) throws TicketException {
        if(componentsMap.containsKey(name)) throw new TicketException();
        componentsMap.put(name, new Component(name,null));
    }
    
    /**
     * Creates a new sub-component as a child of an existing parent component
     * 
     * @param name unique name of the new component
     * @param parentPath path of the parent component
     * @throws TicketException if the the parent component does not exist or 
     *                          if a sub-component of the same parent exists with the same name
     */
    public void defineSubComponent(String name, String parentPath) throws TicketException {
        Component parent=getComponent(parentPath);
        if(parent.getComponentsMap().keySet().contains(name)) throw new TicketException();
        parent.addSubcomponent(new Component(name,parent));
    }

    private Component getComponent(String path) throws TicketException{
        String[] parts=path.split("/");
        if(!componentsMap.containsKey(parts[1])) throw new TicketException();
        Component searchedComponent=componentsMap.get(parts[1]);
        for(int i=2;i<parts.length;i++){
            if(!searchedComponent.getComponentsMap().containsKey(parts[i])) throw new TicketException();
            searchedComponent=searchedComponent.getComponentsMap().get(parts[i]);
        }
        return searchedComponent;
    }
    
    /**
     * Retrieves the sub-components of an existing component
     * 
     * @param path the path of the parent
     * @return set of children sub-components
     */
    public Set<String> getSubComponents(String path){
        Component parent;
        try{
            parent=getComponent(path);
        }catch(TicketException te){
            return null;
        }
        return parent.getComponentsMap().keySet();
    }

    /**
     * Retrieves the parent component
     * 
     * @param path the path of the parent
     * @return name of the parent
     */
    public String getParentComponent(String path){
        Component searchedComponent;
        try{
            searchedComponent=getComponent(path);
        }catch(TicketException te){
            return null;
        }
        if(searchedComponent.getParent()==null) return null;
        return "/"+searchedComponent.getParent().getName();
    }

    /**
     * Opens a new ticket to report an issue/malfunction
     * 
     * @param username name of the reporting user
     * @param componentPath path of the component or sub-component
     * @param description description of the malfunction
     * @param severity severity level
     * 
     * @return unique id of the new ticket
     * 
     * @throws TicketException if the user name is not valid, the path does not correspond to a defined component, 
     *                          or the user does not belong to the Reporter {@link IssueManager.UserClass}.
     */
    public int openTicket(String username, String componentPath, String description, Ticket.Severity severity) throws TicketException {
        if(!usersMap.containsKey(username)) throw new TicketException();
        Component component = getComponent(componentPath);
        User user= usersMap.get(username);
        if(!user.getClasses().contains(UserClass.Reporter)) throw new TicketException();
        ticketCounter++;
        ticketsMap.put(ticketCounter, new Ticket(ticketCounter, user, component, severity, description));
        return ticketCounter;
    }
    
    /**
     * Returns a ticket object given its id
     * 
     * @param ticketId id of the tickets
     * @return the corresponding ticket object
     */
    public Ticket getTicket(int ticketId){
        return ticketsMap.get(ticketId);
    }
    
    /**
     * Returns all the existing tickets sorted by severity
     * 
     * @return list of ticket objects
     */
    public List<Ticket> getAllTickets(){
        return ticketsMap.values().stream().sorted(Comparator.comparing(Ticket::getSeverity)).collect(Collectors.toList());
    }
    
    /**
     * Assign a maintainer to an open ticket
     * 
     * @param ticketId  id of the ticket
     * @param username  name of the maintainer
     * @throws TicketException if the ticket is in state <i>Closed</i>, the ticket id or the username
     *                          are not valid, or the user does not belong to the <i>Maintainer</i> user class
     */
    public void assingTicket(int ticketId, String username) throws TicketException {
        if(!ticketsMap.containsKey(ticketId)) throw new TicketException();
        Ticket searchedTicket= ticketsMap.get(ticketId);
        if(searchedTicket.getState()!=State.Open) throw new TicketException();
        if(!usersMap.containsKey(username)) throw new TicketException();
        User searchedUser= usersMap.get(username);
        if(!searchedUser.getClasses().contains(UserClass.Maintainer)) throw new TicketException();
        searchedTicket.setState(State.Assigned);
        searchedTicket.setUsername(username);
        searchedUser.addTicket(searchedTicket);
    }

    /**
     * Closes a ticket
     * 
     * @param ticketId id of the ticket
     * @param description description of how the issue was handled and solved
     * @throws TicketException if the ticket is not in state <i>Assigned</i>
     */
    public void closeTicket(int ticketId, String description) throws TicketException {
        if(!ticketsMap.containsKey(ticketId)) throw new TicketException();
        Ticket searchedTicket=ticketsMap.get(ticketId);
        if(searchedTicket.getState()!=State.Assigned) throw new TicketException();
        searchedTicket.setSolution(description);
        searchedTicket.setState(State.Closed);
    }   

    /**
     * returns a sorted map (keys sorted in natural order) with the number of  
     * tickets per Severity, considering only the tickets with the specific state.
     *  
     * @param state state of the tickets to be counted, all tickets are counted if <i>null</i>
     * @return a map with the severity and the corresponding count 
     */
    public SortedMap<Ticket.Severity,Long> countBySeverityOfState(Ticket.State state){
        return ticketsMap.values().stream().filter(ticket-> state==null || ticket.getState()==state).collect(Collectors.groupingBy(Ticket::getSeverity,TreeMap::new,Collectors.counting()));
    }

    /**
     * Find the top maintainers in terms of closed tickets.
     * 
     * The elements are strings formatted as <code>"username:###"</code> where <code>username</code> 
     * is the user name and <code>###</code> is the number of closed tickets. 
     * The list is sorter by descending number of closed tickets and then by username.

     * @return A list of strings with the top maintainers.
     */
    public List<String> topMaintainers(){
        return usersMap.values().stream().sorted(Comparator.comparingInt((User user)->user.getClosedTicket()).reversed().thenComparing(user->user.getUsername())).map(user->user.getUsername()+":"+user.getClosedTicket()).collect(Collectors.toList());
    }



}
