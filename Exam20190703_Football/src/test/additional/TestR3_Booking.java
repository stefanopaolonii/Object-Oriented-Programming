package test.additional;

import static org.junit.Assert.*;

import java.time.LocalTime;

import org.junit.Before;
import org.junit.Test;

import futsal.Fields;
import futsal.Fields.Features;
import futsal.FutsalException;

public class TestR3_Booking {
    private static final String OPENING = "10:30";
    private static final String CLOSING = "23:30";
    private Fields fields;
    private int p1;
    private int p2;
    private int p3;
    
    @Before
    public void setUp() throws FutsalException {
        fields = new Fields();

        fields.defineFields( new Features(false,false, false), 
                new Features(true,true, false), 
                new Features(true,false,true), 
                new Features(true,true,true) 
               ); //fields

        fields.setOpeningTime(OPENING);
        fields.setClosingTime(CLOSING);

        p1=fields.newAssociate("Genny", "Sava", "3334445566");
        p2=fields.newAssociate("Remo", "Williams", "3337778899");
        p3=fields.newAssociate("Ugo", "Ughi", "3331112233");

    }

    @Test
    public void testBooking() throws FutsalException {
        fields.bookField(1, p1, "12:30");
        assertTrue("Field 1 should be booked at 12:30", fields.isBooked(1, "12:30"));
    }


    @Test
    public void testBooking2() throws FutsalException {
        fields.bookField(1, p2, OPENING);
        assertTrue("Field 1 should be booked at " + OPENING,fields.isBooked(1, OPENING));
    }

    @Test
    public void testBooking3() throws FutsalException {
        String time = LocalTime.parse(CLOSING).minusMinutes(60).toString();
        fields.bookField(1, p3, time);
        assertTrue("Field 1 should be booked at " + CLOSING, fields.isBooked(1, time));
    }

    @Test
    public void testBookingFail() throws FutsalException {
    	assertThrows("The booking time minutes should be aligned to the opening time minutes",
    	   FutsalException.class,
    	            ()->fields.bookField(1, p1, "14:45") );
    }

    @Test
    public void testBookingFail2() {
    	assertThrows("The booking of a non existent filed should not be possible",
    	    	   FutsalException.class,
    	    	   ()->fields.bookField(99, p1, "14:30") );
    }

    @Test
    public void testBookingFail3()  {
    	assertThrows("The booking by a non existent associate should not be possible",
    	    	   FutsalException.class,
    	    	   ()->fields.bookField(1, p3+99, "14:30") );
    }


}
