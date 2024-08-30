package additional;

import ticketing.IssueManager;
import ticketing.Ticket;
import ticketing.TicketException;
import ticketing.IssueManager.UserClass;
import junit.framework.TestCase;

public class TestR4_TicketCycle extends TestCase {

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

        tm.openTicket("alpha", "/Car/Wheels", "Got a flat tire", Ticket.Severity.Blocking);
        tm.openTicket("alpha", "/Car", "The color is not uniform", Ticket.Severity.Cosmetic);
        tm.openTicket("alpha", "/Car/Engine", "Noisy when decelerating", Ticket.Severity.Minor);
        tm.openTicket("alpha", "/Car/Engine", "Cut power at 2000 rpm", Ticket.Severity.Critical);
        tm.openTicket("alpha", "/Car/Chassis", "Found some rust spots", Ticket.Severity.Major);
    }
    
    public void testAssignTicket() throws TicketException{
        Ticket t = tm.getTicket(1);
        
        assertNotNull(t);
        assertEquals(Ticket.State.Open,t.getState());
        tm.assingTicket(1, "epsilon");
        
        t = tm.getTicket(1);
        assertEquals(Ticket.State.Assigned,t.getState());
    }

    public void testCloseTicket() throws TicketException{
        tm.assingTicket(1, "gamma");
        final String solution = "Replaced flat tire";
        tm.closeTicket(1, solution);
        
        Ticket t = tm.getTicket(1);
        assertNotNull(t);

        assertEquals(Ticket.State.Closed,t.getState());
        assertNotNull(t.getSolutionDescription());
        assertEquals(solution,t.getSolutionDescription());
    }

    public void testAssignTicketWrongUser() throws TicketException{

        try{
            tm.assingTicket(1, "omega");
            fail("Expected exception because user name is not valid.");
        }catch(TicketException e){
            // OK
        }
        
        try{
            tm.assingTicket(1, "beta");
            fail("Expected exception because user not a Maintainer.");
        }catch(TicketException e){
            
        }
    }

    public void testAssignTicketWrongStatus() throws TicketException{

        try{
            tm.assingTicket(99, "epsilon");
            fail("Expected exception because ticket id is not valid.");
        }catch(TicketException e){
            // OK
        }

        tm.assingTicket(1, "delta");
        tm.closeTicket(1, "replaced tire");
        
        try{
            tm.assingTicket(1, "epsilon");
            fail("Expected exception because ticket 'epsilon' is closed already and cannot be assigned.");
        }catch(TicketException e){
            // OK
        }
    }

    public void testCloseTicketWrongStatus() throws TicketException{

        try{
            tm.closeTicket(1, "epsilon");
            fail("Expected exception because ticket is not assigned yet.");
        }catch(TicketException e){
            // OK
        }
        
    }
    
}
