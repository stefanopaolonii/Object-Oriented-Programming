package test.example;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Before;
import org.junit.Test;

import utility.Meter;
import utility.Point;
import utility.ServicePoint;
import utility.User;
import utility.Utility;
import utility.UtilityException;

public class TestApp {

    private Utility com;
    private String pdp1;
    private String mid;

    private static final String MUNICIPALITY = "Torino";
    private static final double LATITUDE = 45.06285921607177;
    private static final double LONGITUDE = 7.662542050883375;
    private static final String SN = "MI134987";
    private static final String MODEL = "Simple meter";

    @Before
    public void setUp(){
        com = new Utility();
        pdp1 = com.defineServicePoint(MUNICIPALITY,"Corso Duca degli Abruzzi 24",LATITUDE, LONGITUDE);
        mid = com.addMeter(SN,"UniMeters",MODEL, "m^3");
    }

    @Test
    public void testR1(){
        assertNotNull("Missing service point id", pdp1);

        ServicePoint sp = com.getServicePoint(pdp1);
        assertNotNull("Missing service point " + pdp1, sp);
        assertEquals(MUNICIPALITY, sp.getMunicipality());

        Point position = sp.getPosition();
        assertNotNull(position);
        assertEquals(LONGITUDE, position.lon, 0.00001);
        assertEquals(LATITUDE, position.lat, 0.00001);


        assertNotNull("Missing meter ID", mid);

        Meter m = com.getMeter(mid);
        assertNotNull("Missing meter " + mid, m);
        assertEquals(SN, m.getSN());
        assertEquals(MODEL, m.getModel());

        com.installMeter(pdp1, mid);

        Optional<Meter> om = sp.getMeter();
        assertTrue("Missing meter", om.isPresent());
        m = om.get();
        assertEquals(mid, m.getId());

        Optional<ServicePoint> osp = m.getServicePoint();
        assertTrue("Missing service point", osp.isPresent());
        assertEquals(pdp1, osp.get().getId());
    }

    @Test
    public void testR2() throws UtilityException{

        com.installMeter(pdp1, mid);

        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");

        assertNotNull("Missing user id", u);
        User user = com.getUser(u);
        assertEquals("Wrong id prefix for user ID", "U", u.substring(0,1));
        assertEquals("Wrong user CF", "DRGMRA47P03H501B", user.getCF());
        assertEquals("Wrong user email", "mario.draghi@eu.eu", user.getEmail());

        String c = com.signContract(u, pdp1);

        assertNotNull("Missing contract id", c);
        assertEquals("Wrong id prefix for contract ID", "C", c.substring(0,1));
    }

    @Test
    public void testR3() throws UtilityException{

        com.installMeter(pdp1, mid);

        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        String contract = com.signContract(u, pdp1);

        com.addReading(contract, mid, "2024-04-18", 100.0);
        com.addReading(contract, mid, "2024-04-28", 120.0);
        com.addReading(contract, mid, "2024-05-29", 180.0);

                Map<String, Double> readings = com.getReadings(contract);
        assertNotNull("Missing readings", readings);
        assertEquals("No reading recorded", 3, readings.size() );

        double latest = com.getLatestReading(contract);
        assertEquals("Wrong latest reading", 180, latest, 0.01);
    }

    @Test
    public void testR4() throws UtilityException{

        com.installMeter(pdp1, mid);

        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        String contract = com.signContract(u, pdp1);

        com.addReading(contract, mid, "2024-04-18", 100.0);
        com.addReading(contract, mid, "2024-04-28", 120.0);
        com.addReading(contract, mid, "2024-05-29", 180.0);

        double reading = com.getEstimatedReading(contract, "2024-04-23");
        assertEquals("Wrong estimated reading", 110.0, reading, 0.01);

        double consumption = com.getConsumption(contract, "2024-04-20", "2024-05-28");
        assertEquals("Wrong consumption", 74.0, consumption, 0.1);

        List<String> breakdown = com.getBillBreakdown(contract, 5, 5, 2024);

        assertNotNull("Missing breakdown", breakdown);
        assertEquals("Wrong breakdown", 1, breakdown.size());
        Pattern p = Pattern.compile("(\\d{4}-\\d{2}-\\d{2})[ .]+(\\d{4}-\\d{2}-\\d{2})\\s*:\\s*(\\d+\\.\\d+)\\s*->\\s*(\\d+\\.\\d+)\\s*=\\s*(\\d+\\.\\d+)");

        Matcher matcher = p.matcher(breakdown.get(0));
        assertTrue("Wrong breakdown format", matcher.matches());
    }
}
