package additional;

import java.util.List;

import ticketing.IssueManager;
import ticketing.IssueManager.UserClass;
import ticketing.Ticket;
import ticketing.TicketException;
import junit.framework.TestCase;

public class TestR3_TicketOpen extends TestCase {

    private IssueManager tm;
    
    public void setUp() throws TicketException{
        tm = new IssueManager();
        tm.createUser("alpha", UserClass.Reporter);
        tm.createUser("beta", UserClass.Reporter);
        tm.createUser("gamma", UserClass.Reporter, UserClass.Maintainer);
        tm.createUser("delta", UserClass.Reporter, UserClass.Maintainer);
        tm.createUser("epsilon", UserClass.Maintainer);

        tm.defineComponent("Car");
        tm.defineSubComponent("Engine", "/Car");
        tm.defineSubComponent("Wheels", "/Car");
        tm.defineSubComponent("Chassis", "/Car");
    }

    public void testOpenTicket() throws TicketException{
        int tid = tm.openTicket("alpha", "/Car/Wheels", "Got a flat tire", Ticket.Severity.Blocking);
        int tid2 = tm.openTicket("alpha", "/Car/Engine", "Noisy when decelerating", Ticket.Severity.Minor);
        
        assertEquals("The first ticket should have id == 1",1,tid);
        assertEquals("The second ticket should have id == 2",2,tid2);
    }
    
    public void testGetTicket() throws TicketException{
        final String description = "Got a flat tire";
        final String username = "alpha";
        int tid = tm.openTicket(username, "/Car/Wheels", description, Ticket.Severity.Blocking);
        Ticket t = tm.getTicket(tid);
        
        assertNotNull(t);
        assertEquals(tid,t.getId());
        assertEquals(description,t.getDescription());
        assertEquals(username,t.getAuthor());        
    }
    
    public void testGetAllTickets() throws TicketException{
        final String description = "Got a flat tire";
        final String username = "alpha";
        tm.openTicket(username, "/Car/Wheels", description, Ticket.Severity.Blocking);
        tm.openTicket("alpha", "/Car", "The color is not uniform", Ticket.Severity.Cosmetic);
        tm.openTicket("alpha", "/Car/Engine", "Noisy when decelerating", Ticket.Severity.Minor);
        tm.openTicket("alpha", "/Car/Engine", "Cut power at 2000 rpm", Ticket.Severity.Critical);
        tm.openTicket("alpha", "/Car/Chassis", "Found some rust spot", Ticket.Severity.Major);
        
        List<Ticket> tickets = tm.getAllTickets();
        
        assertNotNull(tickets);
        assertEquals(5,tickets.size());
        
        Ticket.Severity prev = Ticket.Severity.Blocking;
        for(Ticket t : tickets){
            assertTrue("Severity of ticket " + t.getId() + " (i.e. " + t.getSeverity() + ") is greater than previous ticket",
                    prev.compareTo(t.getSeverity()) <= 0);
        }
    }
    
    
    public void testOpenTicketNonexistentUser() {
        try {
            tm.openTicket("omega", "/Car/Wheels", "Got a flat tire", Ticket.Severity.Blocking);
            fail("Expected exception because username is nonexistent.");
        } catch (TicketException e) {
            // OK
        }
        
    }
    
    public void testOpenTicketWrongUserClass() throws TicketException{
        try {
            tm.openTicket("epsilon", "/Car/Wheels", "Got a flat tire", Ticket.Severity.Blocking);
            fail("Expected exception because username is not a Reporter.");
        } catch (TicketException e) {
            // OK
        }
    }
    
    public void testOpenTicketWrongComponent() throws TicketException{
        try {
            tm.openTicket("alpha", "/Car/Handlebar", "Cannot steer", Ticket.Severity.Blocking);
            fail("Expected exception because component is not existing.");
        } catch (TicketException e) {
            // OK
        }
    }
    
}
