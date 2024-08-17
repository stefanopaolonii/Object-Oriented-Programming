package test.additional;

import utility.ServicePoint;
import utility.User;
import utility.Utility;
import utility.UtilityException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collection;

public class TestR2 {
    private Utility com;
    private String pdp;
    private String meter;
    private String pdp2;
    private String meter2;

    @Before
    public void setUp(){
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
    }

    @Test
    public void testUser(){
        // add the missing code
        String u = com.addUser("CF", "name", "surname", "address", "email");

        assertNotNull("Missing user id", u);
        assertEquals("Wrong id prefix for user ID", "U", u.substring(0,1));
    }

    @Test
    public void testUserUnique(){
        // add the missing code
        String u1 = com.addUser("CF1", "name", "surname", "address", "email1");
        String u2 = com.addUser("CF2", "name", "surname", "address", "email2");

        assertNotNull("Missing user id", u1);
        assertNotNull("Missing user id", u2);
    }

    @Test
    public void testUserGet(){
        // add the missing code
        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");

        assertNotNull("Missing user id", u);
        User user = com.getUser(u);
        assertEquals("Wrong id prefix for user ID", "U", u.substring(0,1));
        assertEquals("Wrong user CF", "DRGMRA47P03H501B", user.getCF());
        assertEquals("Wrong user name", "Mario", user.getName());
        assertEquals("Wrong user surname", "Draghi", user.getSurname());
        assertEquals("Wrong user address", "Via Roma 1, Roma", user.getAddress());
        assertEquals("Wrong user email", "mario.draghi@eu.eu", user.getEmail());
    }

    @Test
    public void  testGetUsers() {
        // add the missing code
        String u1 = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        String u2 = com.addUser("PIVA123456789", "ACME", null, "Via Roma 1, Roma", "info@acme.com");

        Collection<String> users = com.getUsers();

        assertNotNull("Missing users", users);
        assertEquals("Wrong number of users", 2, users.size());
        assertTrue("Missing users from the list", users.containsAll(Arrays.asList(u1,u2)) );
    }


    @Test
    public void testUserType() {
        // add the missing code
        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");

        assertNotNull("Missing user id", u);
        User user = com.getUser(u);
        assertEquals("Wrong user type", User.Type.RESIDENTIAL, user.getType());
    }

    @Test
    public void testUserTypeBusiness() {
        // add the missing code
        String u = com.addUser("PIVA123456789", "ACME", "Via Roma 1, Roma", "info@acme.com");
        assertNotNull("Missing user id", u);
        User user = com.getUser(u);
        assertEquals("Wrong user type", User.Type.BUSINESS, user.getType());
    }

    @Test
    public void testContract() throws UtilityException {
        // add the missing code
        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        String c = com.signContract(u, pdp);

        assertNotNull("Missing contract id", c);
        assertEquals("Wrong id prefix for contract ID", "C", c.substring(0,1));
    }

    @Test
    public void testContractWrongUser() {
        // add the missing code
        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        assertThrows("Invalid user for contract should throw exception",
                     UtilityException.class,
                     ()->com.signContract(u+"X", pdp));
    }

    @Test
    public void testContractWrongPdp() {
        // add the missing code
        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        assertThrows("Invalid service point for contract should throw exception",
                     UtilityException.class,
                     ()->com.signContract(u, "X"+pdp));
    }

    @Test
    public void testContractGet() throws UtilityException {
        // add the missing code
        String u = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        String c = com.signContract(u, pdp);
        User user = com.getUser(u);
        ServicePoint sp = com.getServicePoint(pdp);

        assertNotNull("Missing contract id", c);
        assertEquals("Wrong id prefix for contract ID", "C", c.substring(0,1));
        assertEquals("Wrong contract user", user, com.getContract(c).getUser());
        assertEquals("Wrong contract service point", sp, com.getContract(c).getServicePoint());
    }

    @Test
    public void  testContractCodeUnique() throws UtilityException {
        // add the missing code
        String u1 = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        String u2 = com.addUser("PIVA123456789", "ACME", null, "Via Roma 1, Roma", "info@acme.com");
        String c1 = com.signContract(u1, pdp);
        String c2 = com.signContract(u2, pdp2);

        assertNotNull("Missing contract id", c1);
        assertNotNull("Missing contract id", c2);

        assertNotEquals("Contract code not unique", c1, c2);
    }

    @Test
    public void testContractMeter() {
        // check exception when service point has no meter
        String u1 = com.addUser("DRGMRA47P03H501B", "Mario", "Draghi", "Via Roma 1, Roma", "mario.draghi@eu.eu");
        String pdp3 = com.defineServicePoint(
                "Torino",
                "Corso Duca degli Abruzzi 109",
                45.06285921607177,
                7.662735050883375
        );
        assertThrows("Should throw exception if not meter connected to service point ",
                UtilityException.class, () -> com.signContract(u1, pdp3));
    }

}
