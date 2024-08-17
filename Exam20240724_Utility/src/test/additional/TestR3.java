package test.additional;

import utility.Utility;
import utility.UtilityException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Map;

public class TestR3 {

    private Utility com;
    private String pdp;
    private String meter;
    private String pdp2;
    private String meter2;
    private String user;
    private String contract;

    @Before
    public void setUp() throws UtilityException {
        // add the missing code
        com = new Utility();
        pdp = com.defineServicePoint(
                "Torino",
                "Corso Duca degli Abruzzi 24",
                45.06285921607177,
                7.662542050883375
        );
        meter = com.addMeter("1234", "brand", "model", "m^3");
        com.installMeter(pdp, meter);

        pdp2 = com.defineServicePoint(
                "Roma",
                "Via Andrea Doria 8",
                41.910378010247044,
                12.453756339313216
        );
        meter2 = com.addMeter("5678", "brand", "model", "m^3");
        com.installMeter(pdp2, meter2);

        user = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");

        contract = com.signContract(user, pdp);
    }

    @Test
    public void testReading() throws UtilityException {
        // add the missing code
        com.addReading(contract, meter, "2024-04-28", 100.0);

        Map<String, Double> readings = com.getReadings(contract);
        assertNotNull("Missing readings", readings);
        assertEquals("No reading recorded", 1, readings.size() );
    }

    @Test
    public void testReading2() throws UtilityException {
        // add the missing code
        com.addReading(contract, meter, "2024-04-18", 100.0);
        com.addReading(contract, meter, "2024-04-28", 120.0);

        Map<String, Double> readings = com.getReadings(contract);
        assertNotNull("Missing readings", readings);
        assertEquals("No reading recorded", 2, readings.size() );
        assertTrue("Missing reading for first date", readings.containsKey("2024-04-18"));
        assertTrue("Missing reading for second date", readings.containsKey("2024-04-28"));
    }

    @Test
    public void testReadingMismatch() {
        // add the missing code
        assertThrows("Exception expected when contract and meter do not match",
                UtilityException.class,
                () -> com.addReading(contract, meter2, "2024-04-28", 100.0));
    }

    @Test
    public void testLastReading() throws UtilityException {
        com.addReading(contract, meter, "2024-04-18", 100.0);
        com.addReading(contract, meter, "2024-04-28", 120.0);
        com.addReading(contract, meter, "2024-05-29", 180.0);

        double latest = com.getLatestReading(contract);
        assertEquals("Wrong latest reading", 180, latest, 0.01);
    }

    @Test
    public void testLastReading2() throws UtilityException {
        com.addReading(contract, meter, "2024-05-29", 180.0);
        com.addReading(contract, meter, "2024-04-18", 100.0);
        com.addReading(contract, meter, "2024-04-28", 120.0);

        double latest = com.getLatestReading(contract);
        assertEquals("Wrong latest reading", 180, latest, 0.01);
    }

}
