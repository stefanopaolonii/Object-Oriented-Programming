package test.additional;

import utility.Utility;
import utility.UtilityException;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class TestR4 {
    private Utility com;
    private String pdp;
    private String meter;
    private String pdp2;
    private String meter2;
    private String user;
    private String user2;
    private String contract;
    private String contract2;
    private static final String DATE1 = "2024-04-18";
    private static final String DATE2 = "2024-04-28";
    private static final String DATE_BETWEEN = "2024-04-23";
    private static final String DATE_AFTER = "2024-05-03";
    private static final String DATE_BEFORE = "2024-04-10";

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
        user2 = com.addUser("PIVA123456789", "ACME", "Via Roma 1, Roma", "info@acme.com");

        contract = com.signContract(user, pdp);
        contract2 = com.signContract(user2, pdp2);

        com.addReading(contract, meter, DATE1, 100.0);
        com.addReading(contract, meter, DATE2, 120.0);
        com.addReading(contract2, meter2, DATE2, 120.0);
    }

    @Test
    public void testEstimateReading() throws UtilityException {
        double reading = com.getEstimatedReading(contract, DATE_BETWEEN);
        assertEquals("Wrong estimated reading", 110.0, reading, 0.01);
    }

    @Test
    public void testEstimateReadingActual() throws UtilityException {
        double reading = com.getEstimatedReading(contract, DATE2);
        assertEquals("Wrong estimated reading", 120.0, reading, 0.01);
    }


    @Test
    public void testEstimateReadingExtra() throws UtilityException {
        double reading = com.getEstimatedReading(contract, DATE_AFTER);
        assertEquals("Wrong estimated reading", 130.0, reading, 0.01);
    }

    @Test
    public void testEstimateReadingPreFirst() {
        assertThrows("Exception expected when date is before first reading",
                UtilityException.class,
                () -> com.getEstimatedReading(contract, DATE_BEFORE));
    }

    @Test
    public void testEstimateReadingOneAvailableOnly() {
        assertThrows("At least two readsings required to estimate",
                UtilityException.class,
                () -> com.getEstimatedReading(contract2, DATE_AFTER));
    }

    @Test
    public void testConsumption() throws UtilityException {
        double consumption = com.getConsumption(contract, DATE_BETWEEN, DATE_AFTER);

        assertEquals("Wrong consumption", 20.0, consumption, 0.01);
    }

    @Test
    public void testBreakdown() throws UtilityException {
        com.addReading(contract, meter, "2023-12-15", 10.0);
        com.addReading(contract, meter, "2023-02-15", 70.0);
        com.addReading(contract, meter, "2023-03-15", 85.0);
        // 2024-04-18  = 100

        List<String> breakdown = com.getBillBreakdown(contract, 1, 3, 2024);

        assertNotNull("Missing breakdown", breakdown);
        System.out.println(breakdown);
        assertEquals("Wrong breakdown", 3, breakdown.size());
        Pattern p = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})[ .]+(\\d{4}-\\d{2}-\\d{2})\\s*:\\s*(\\d+\\.\\d+)\\s*->\\s*(\\d+\\.\\d+)\\s*=\\s*(\\d+\\.\\d+)");

        int m=1;
        for(String b: breakdown) {
            Matcher matcher = p.matcher(b);
            assertTrue("Wrong breakdown format", matcher.matches());
            assertEquals("Wrong month", m++, Integer.parseInt(matcher.group(1).substring(5,7)));
            assertEquals("Wrong month", m, Integer.parseInt(matcher.group(2).substring(5,7)));
            double start = Double.parseDouble(matcher.group(3));
            double end = Double.parseDouble(matcher.group(4));
            double cons = Double.parseDouble(matcher.group(5));
            assertEquals("Wrong consumption", end-start, cons, 0.01);
        }


    }
}
