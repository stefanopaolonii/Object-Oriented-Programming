package additional;

import java.util.List;
import java.util.SortedMap;

import ticketing.IssueManager;
import ticketing.Ticket;
import ticketing.TicketException;
import ticketing.IssueManager.UserClass;
import junit.framework.TestCase;

public class TestR5_Stats extends TestCase {

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
        tm.openTicket("alpha", "/Car/Engine", "Engine turn off every 5 minutes", Ticket.Severity.Critical);
        tm.openTicket("alpha", "/Car", "Windshield is cracked", Ticket.Severity.Major);
        
        tm.assingTicket(1, "gamma");
        tm.closeTicket(1, "Replaced flat tire");
        tm.assingTicket(5, "gamma");
        tm.closeTicket(5, "Painted over rust");
        tm.assingTicket(4, "gamma");

        tm.assingTicket(2, "epsilon");
        tm.closeTicket(2, "Washed car...");

        tm.assingTicket(3, "delta");
        tm.closeTicket(3, "Replaced silencer");

    }
    
    public void testTopTenMaintainers(){
        
        List<String> top = tm.topMaintainers();
        
        
        assertNotNull(top);
        assertTrue("Top maintainer list must have 3 or five elements, it has " + top.size(),
        		    top.size()==3 || top.size()==5);
        
        String[] first = top.get(0).split(":");
        assertEquals("gamma", first[0].trim());
        assertEquals("2", first[1].trim());
        
        String[] second = top.get(1).split(":");
        assertEquals("delta", second[0].trim());
        assertEquals("1", second[1].trim());
    }
    
    public void testByServerity(){
        SortedMap<Ticket.Severity,Long> res = tm.countBySeverityOfState(null);
        
        assertNotNull(res);
        assertEquals(1,res.get(Ticket.Severity.Blocking).longValue());
        assertEquals(2,res.get(Ticket.Severity.Critical).longValue());
        assertEquals(2,res.get(Ticket.Severity.Major).longValue());
        assertEquals(1,res.get(Ticket.Severity.Minor).longValue());
        assertEquals(1,res.get(Ticket.Severity.Cosmetic).longValue());
    }

    public void testByServerityClosed(){
        SortedMap<Ticket.Severity,Long> res = tm.countBySeverityOfState(Ticket.State.Closed);
        
        assertNotNull(res);
        assertEquals(1,res.get(Ticket.Severity.Blocking).longValue());
        assertEquals(1,res.get(Ticket.Severity.Major).longValue());
        assertEquals(1,res.get(Ticket.Severity.Minor).longValue());
        assertEquals(1,res.get(Ticket.Severity.Cosmetic).longValue());
    }

}
