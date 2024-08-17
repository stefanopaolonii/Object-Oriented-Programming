package test.additional;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import futsal.FieldOption;
import futsal.Fields;
import futsal.FutsalException;
import futsal.Fields.Features;

public class TestR4_FieldOccupation {
    static final String CLOSING = "23:30";
    static final String OPENING = "10:30";
    static final Features f_out = new Features(false,false,false);
    static final Features f_in_heat = new Features(true,true,false);
    static final Features f_in_ac = new Features(true,false,true);
    static final Features f_in_heat_ac = new Features(true,true,true);

    
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
        
        fields.bookField(2, p1, "13:30");
        fields.bookField(3, p2, "21:30");
        fields.bookField(4, p3, "20:30");
        fields.bookField(4, p2, "22:30");
        fields.bookField(2, p1, "14:30");
        fields.bookField(2, p1, "22:30");
    }


    @Test
    public void testOccupation() {
    	int[] occupations = {-1, 0, 3, 1, 2};
    	for(int field=1; field<5; ++field) {
    		assertEquals("Wrong occupation for field " + field,
    				    occupations[field], fields.getOccupation(field));
    	}
    }
    
    @Test
    public void testOptions1() {
        List<FieldOption> options = 
        		fields.findOptions("19:30",
        					   new Features(false,false,false));
        
        assertNotNull("Missing field options", options);
        assertEquals("Wrong number of available options", 
        		    4,options.size());
        FieldOption first= options.get(0);
        assertEquals("Wrong first option", 2,first.getField());
        assertEquals(3,first.getOccupation());
        FieldOption last= options.get(3);
        assertEquals("Wrong last option",1,last.getField());
        assertEquals(0,last.getOccupation());
    }
    
    @Test
    public void testOptions2() {
        List<FieldOption> options = 
        		fields.findOptions("19:30",f_out);
        
        assertNotNull("Missing field options", options);
        assertEquals("Wrong number of available options",
        		    4,options.size());
    }

    @Test
    public void testOptions3() {
        List<FieldOption> options = fields.findOptions("19:30",new Features(true,true,false));
        
        assertNotNull("Missing field options", options);
        assertEquals("Wrong number of available options",
        		    2,options.size());
    }

    @Test
    public void testOptions4() {
        List<FieldOption> options = fields.findOptions("19:30",new Features(true,false,true));
        
        assertNotNull("Missing field options", options);
        assertEquals("Wrong number of available options",
        		    2,options.size());
    }
    
    @Test
    public void testOptions5() {
        List<FieldOption> options = fields.findOptions("19:30",new Features(true,true,true));
        
        assertNotNull("Missing field options", options);
        assertEquals("Wrong number of available options",
        		    1,options.size());
        
        assertEquals("Wrong first option",
        		    4,options.get(0).getField());
    }

}
