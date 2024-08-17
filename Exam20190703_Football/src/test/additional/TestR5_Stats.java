package test.additional;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import futsal.Fields;
import futsal.FutsalException;
import static test.additional.TestR4_FieldOccupation.*;

public class TestR5_Stats {
    private Fields fields;
    private int p1;
    private int p2;
    private int p3;
    
    @Before
    public void setUp() throws FutsalException {
        fields = new Fields();

        fields.defineFields( 
        		f_out, 
        		f_in_heat, 
        		f_in_ac, 
        		f_in_heat_ac
               ); //fields

        fields.setOpeningTime(OPENING);
        fields.setClosingTime(CLOSING);

        p1 = fields.newAssociate("Genny", "Sava", "3334445566");
        p2 = fields.newAssociate("Remo", "Williams", "3337778899");
        p3 = fields.newAssociate("Ugo", "Ughi", "3331112233");
        fields.newAssociate("Ivo", "Uva", "3336665544");
        
        fields.bookField(2, p1, "13:30");
        fields.bookField(3, p2, "21:30");
        fields.bookField(4, p3, "20:30");
        fields.bookField(4, p2, "22:30");
        fields.bookField(2, p1, "14:30");
        fields.bookField(2, p1, "22:30");

    }

    @Test
    public void testServedAssociates() {
        assertEquals("Wrong number of served associates",
        		    3,fields.countServedAssociates());
    }

    @Test
    public void testTurnover() {
        Map<Integer,Long> tom = fields.fieldTurnover();
        assertNotNull("Missing field turnover", tom);
        assertEquals("Wrong number of turonver elements", 
        		    4,tom.size());
        assertEquals(0L,tom.get(1).longValue());
        assertEquals(3L,tom.get(2).longValue());
        assertEquals(1L,tom.get(3).longValue());
        assertEquals(2L,tom.get(4).longValue());
    }
    
    @Test
    public void testOccupation() {
        assertEquals("Wrong occupation",
        		    0.115,fields.occupation(),0.001);
    }
    


}
