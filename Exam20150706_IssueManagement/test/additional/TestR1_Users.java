package additional;

import java.util.HashSet;
import java.util.Set;

import ticketing.IssueManager;
import ticketing.TicketException;
import junit.framework.TestCase;

public class TestR1_Users extends TestCase {
    
    private IssueManager tm;
    
    public void setUp(){
        tm = new IssueManager();
    }
    
    public void testCreateUser() throws TicketException{
        tm.createUser("omega", IssueManager.UserClass.Reporter);
        
        Set<IssueManager.UserClass> classes = tm.getUserClasses("omega");
        
        assertNotNull(classes);
        
        assertEquals("Expected exactly one user class",1,classes.size());
        
        assertTrue("Expected Reported class",classes.contains(IssueManager.UserClass.Reporter));
    }

    public void testCreateUserSet() throws TicketException{
        
        Set<IssueManager.UserClass> defClasses = new HashSet<>();
        defClasses.add(IssueManager.UserClass.Reporter);
        tm.createUser("omega", defClasses);
        
        Set<IssueManager.UserClass> classes = tm.getUserClasses("omega");
        
        assertNotNull(classes);
        
        assertEquals("Expected exactly one user class",1,classes.size());
        
        assertTrue("Expected Reported class",classes.contains(IssueManager.UserClass.Reporter));
    }

    public void testCreateUserTwoClasses() throws TicketException{
        tm.createUser("omega", IssueManager.UserClass.Reporter,IssueManager.UserClass.Maintainer);
        
        Set<IssueManager.UserClass> classes = tm.getUserClasses("omega");
        
        assertNotNull(classes);
        
        assertEquals("Expected exactly one user class",2,classes.size());
        
        assertTrue("Expected Reported class",classes.contains(IssueManager.UserClass.Reporter));
    }

    public void testCreateUserTwoClassesSet() throws TicketException{
        Set<IssueManager.UserClass> defClasses = new HashSet<>();
        defClasses.add(IssueManager.UserClass.Reporter);
        defClasses.add(IssueManager.UserClass.Maintainer);
        tm.createUser("omega", defClasses);
        
        Set<IssueManager.UserClass> classes = tm.getUserClasses("omega");
        
        assertNotNull(classes);
        
        assertEquals("Expected exactly one user class",2,classes.size());
        
        assertTrue("Expected Reported class",classes.contains(IssueManager.UserClass.Reporter));
    }

    
    
    public void testCreateUserNoClass() throws TicketException{
        try{
            tm.createUser("omega");
            fail("Exception expected because no user class was specified");
        }catch(TicketException e){
            // OK
        }
    }

    public void testCreateUserNoClassSet() throws TicketException{
        try{
            Set<IssueManager.UserClass> defClasses = new HashSet<>();
            tm.createUser("omega", defClasses);
            fail("Exception expected because no user class was specified");
        }catch(TicketException e){
            // OK
        }
    }

    public void testCreateUserDuplicate() throws TicketException{
        tm.createUser("omega",IssueManager.UserClass.Maintainer);
        try{
            tm.createUser("omega",IssueManager.UserClass.Maintainer);
            fail("Exception expected because username was duplicate");
        }catch(TicketException e){
            // OK
        }
    }

    public void testCreateUserDuplicateSet() throws TicketException{
        Set<IssueManager.UserClass> defClasses = new HashSet<>();
        defClasses.add(IssueManager.UserClass.Reporter);
        tm.createUser("omega", defClasses);
        try{
            tm.createUser("omega", defClasses);
            fail("Exception expected because username was duplicate");
        }catch(TicketException e){
            // OK
        }
    }

}
