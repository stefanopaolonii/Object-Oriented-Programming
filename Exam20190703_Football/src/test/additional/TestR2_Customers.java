package test.additional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import futsal.Fields;
import futsal.FutsalException;

public class TestR2_Customers {
    private Fields fields;
    
    @Before
    public void setUp() {
        fields = new Fields();
    }

    @Test
    public void testAssocGetters() throws FutsalException {
        int p1 = fields.newAssociate("Genny", "Sava", "3334445566");
        
        assertEquals("Wrong associate name", "Genny",fields.getFirst(p1));
        assertEquals("Wrong associate surnname","Sava",fields.getLast(p1));
        assertEquals("Wrong associate mobile","3334445566",fields.getPhone(p1));
    }
    
    @Test
    public void testUniqueCode() {
        int p1 = fields.newAssociate("Genny", "Sava", "3334445566");
        int p2 = fields.newAssociate("Remo", "Williams", "3337778899");

        assertTrue("Associate codes should be unique", p1!=p2);
    }
    
    @Test
    public void testPartiesCount() {
        fields.newAssociate("Genny", "Sava", "3334445566");
        fields.newAssociate("Remo", "Williams", "3337778899");
        fields.newAssociate("Ugo", "Ughi", "3331112233");
        
        assertEquals("Wrong associate number", 3,fields.countAssociates());
    }

    @Test
    public void testPartyGettersExc1() {
        int p1 = fields.newAssociate("Genny", "Sava", "3334445566");
        
        assertThrows("Invalid associate code should trigger exception",
           FutsalException.class,
                    ()->fields.getFirst(p1+99) );
    }

    @Test
    public void testPartyGettersExc2() {
        int p1 = fields.newAssociate("Genny", "Sava", "3334445566");
        
        assertThrows("Invalid associate code should trigger exception",
           FutsalException.class,
                    ()->fields.getLast(p1-99) );
    }

}
