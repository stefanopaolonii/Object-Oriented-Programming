package test.additional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import utility.Meter;
import utility.Point;
import utility.ServicePoint;
import utility.Utility;
import java.util.Collection;
import java.util.Optional;

public class TestR1 {

    private Utility com;

    @Before
    public void setUp(){
        com = new Utility();
    }

    @Test
    public void testPdP(){
        String pdp1 = com.defineServicePoint("Torino","Corso Duca degli Abruzzi 24",45.06285921607177, 7.662542050883375);

        assertNotNull("Missing service point id", pdp1);
        assertEquals("Wrong id prefix for service point ID", "SP", pdp1.substring(0,2));

    }

    @Test
    public void testPdPUnique(){
        String pdp1 = com.defineServicePoint("Torino","Corso Duca degli Abruzzi 24",45.06285921607177, 7.662542050883375);

        assertNotNull("Missing service point id", pdp1);

        String pdp2 = com.defineServicePoint("Torino","Corso Duca degli Abruzzi 24",45.06285921607177, 7.662542050883375);
        assertNotEquals("Service point id not unique", pdp1, pdp2);
    }

    @Test
    public void testPdPs(){
        String pdp1 = com.defineServicePoint("Torino","Corso Duca degli Abruzzi 24",45.06285921607177, 7.662542050883375);

        assertNotNull("Missing service point id", pdp1);

        String pdp2 = com.defineServicePoint("Torino","Corso Duca degli Abruzzi 24",45.06285921607177, 7.662542050883375);
        assertNotNull("Missing service point id", pdp2);

        Collection<String> sps = com.getServicePoints();
        assertNotNull("Missing service points", sps);
        assertEquals("Wrong number of service points", 2, sps.size());
        assertTrue("Missing service point " + pdp1, sps.contains(pdp1));
        assertTrue("Missing service point " + pdp2, sps.contains(pdp2));
    }

    @Test
    public void testGetPdP(){
        String municipality = "Torino";
        String address = "Corso Duca degli Abruzzi 24";
        double lat = 45.06285921607177;
        double lon = 7.662542050883375;
        String pdp1 = com.defineServicePoint(municipality,address,lat, lon);

        assertNotNull("Missing service point id", pdp1);

        ServicePoint sp = com.getServicePoint(pdp1);
        assertNotNull("Missing service point " + pdp1, sp);

        assertEquals(municipality, sp.getMunicipality());
        assertEquals(address, sp.getAddress());
        Point position = sp.getPosition();
        assertNotNull(position);
        assertEquals(lon, position.lon, 0.00001);
        assertEquals(lat, position.lat, 0.00001);
    }


    @Test
    public void testMeter(){
        String mid = com.addMeter("MI134987","UniMeters","Simple meter", "m^3");

        assertNotNull("Missing meter ID", mid);
        assertEquals("Wrong id prefix for meter ID", "MT", mid.substring(0,2));
    }

    @Test
    public void testGetMeter(){
        String sn = "MI134987";
        String brand = "UniMeters";
        String model = "Simple meter";
        String unit = "m^3";
        String mid = com.addMeter(sn,brand,model, unit);

        assertNotNull("Missing meter ID", mid);
        Meter m = com.getMeter(mid);
        assertNotNull("Missing meter " + mid, m);
        assertEquals(sn, m.getSN());
        assertEquals(brand, m.getBrand());
        assertEquals(model, m.getModel());
        assertEquals(unit, m.getUnit());
    }

    @Test
    public void testMeterUnique(){
        String mid1 = com.addMeter("MI134987","UniMeters","Simple meter", "m^3");
        String mid2 = com.addMeter("KJ1343J87","KoreJ","Super meter", "m^3");

        assertNotNull("Missing meter ID", mid1);
        assertNotNull("Missing meter ID", mid2);
        assertNotEquals("Meter id not unique",mid1, mid2);
    }

    @Test
    public void testInstallMeter(){
        String pdp1 = com.defineServicePoint("Torino","Corso Duca degli Abruzzi 24",45.06285921607177, 7.662542050883375);
        String mid1 = com.addMeter("MI134987","UniMeters","Simple meter", "m^3");

        com.installMeter(pdp1, mid1);

        ServicePoint sp = com.getServicePoint(pdp1);
        Optional<Meter> om = sp.getMeter();
        assertTrue("Missing meter", om.isPresent());
        Meter m = om.get();
        assertEquals(mid1, m.getId());

        Optional<ServicePoint> osp = m.getServicePoint();
        assertTrue("Missing service point", osp.isPresent());
        assertEquals(pdp1, osp.get().getId());
    }

    @Test
    public void testNotInstalledMeter(){
        String pdp1 = com.defineServicePoint("Torino","Corso Duca degli Abruzzi 24",45.06285921607177, 7.662542050883375);

        ServicePoint sp = com.getServicePoint(pdp1);
        Optional<Meter> om = sp.getMeter();
        assertTrue("Meter installed", om.isEmpty());
    }

    @Test
    public void testNotInstalledServicePoint(){
        String mid1 = com.addMeter("MI134987","UniMeters","Simple meter", "m^3");

        Meter m = com.getMeter(mid1);
        Optional<ServicePoint> osp = m.getServicePoint();
        assertTrue("Service point installed", osp.isEmpty());
    }
}
