package additional;

import java.util.Set;

import ticketing.IssueManager;
import ticketing.TicketException;
import junit.framework.TestCase;

public class TestR2_Components extends TestCase {
    
    private IssueManager tm;
    
    public void setUp(){
        tm = new IssueManager();
    }
    
    public void testCreateComponent() throws TicketException{
        
        tm.defineComponent("System");
        
        String none = tm.getParentComponent("/System");
        
        assertNull(none);
    }

    public void testSubComponents() throws TicketException{
        
        tm.defineComponent("Car");
        tm.defineSubComponent("Engine", "/Car");
        tm.defineSubComponent("Wheels", "/Car");
        tm.defineSubComponent("Chassis", "/Car");

        Set<String> subs = tm.getSubComponents("/Car");
        
        assertNotNull("Missing sub-components",subs);
        assertEquals(3,subs.size());
        assertTrue(subs.contains("Engine"));
        assertTrue(subs.contains("Wheels"));
        assertTrue(subs.contains("Chassis"));
    }

    public void testParentComponents() throws TicketException{
        
        tm.defineComponent("Car");
        tm.defineSubComponent("Engine", "/Car");
        tm.defineSubComponent("Wheels", "/Car");
        tm.defineSubComponent("Chassis", "/Car");

        String parent = tm.getParentComponent("/Car/Engine");
        
        assertNotNull(parent);
        assertEquals("/Car",parent);
    }
    
    public void testCreateNoParent() throws TicketException{
        
        tm.defineComponent("Car");
        try{
            tm.defineSubComponent("Engine", "/Bicycle");
            fail("Exception expected because parent component does not exist.");
        }catch(TicketException e){
            // OK
        }
    }

    public void testCreateDuplicate() throws TicketException{
        
        tm.defineComponent("Car");
        tm.defineSubComponent("Engine", "/Car");
        tm.defineSubComponent("Wheels", "/Car");
        tm.defineSubComponent("Chassis", "/Car");
        try{
            tm.defineSubComponent("Wheels", "/Car");
            fail("Exception expected because sub-component is duplicate.");
        }catch(TicketException e){
            // OK
        }
    }

}
