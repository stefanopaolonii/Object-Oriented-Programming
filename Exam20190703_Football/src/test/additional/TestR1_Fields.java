package test.additional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import futsal.Fields;
import futsal.FutsalException;
import futsal.Fields.Features;

public class TestR1_Fields {
    private Fields company;
    
    @Before
    public void setUp() {
        company = new Fields();
    }

    @Test
    public void testFields() throws FutsalException {
        company.defineFields(new Features(false,false,false), 
                new Features(true,true,false), 
                new Features(true,false,true), 
                new Features(true,true,true) 
               );
        
        assertEquals("Wrong number of fields", 4,company.countFields());
        assertEquals("Wrong number of indoor fields", 3,company.countIndoor());
    }
    
    @Test
    public void testFieldsWrong() {
    	assertThrows("Outdoor field cannot have heating", FutsalException.class,
        ()->company.defineFields(new Features(false,false,false), 
                new Features(false,true,false)) );
    }

    @Test
    public void testFieldsWrong2() throws FutsalException {
    	assertThrows("Outdoor field cannot have air conditioning", FutsalException.class,
        ()->company.defineFields(new Features(false,false,false), 
                new Features(false,false,true)) );
    }
    
    @Test
    public void testOpening() {
        company.setOpeningTime("19:30");
        assertEquals("Wrong opening time", "19:30",company.getOpeningTime());
    }

    @Test
    public void testClosing() {
        company.setClosingTime("23:30");
        assertEquals("Wrong closing time","23:30",company.getClosingTime());
    }
}
