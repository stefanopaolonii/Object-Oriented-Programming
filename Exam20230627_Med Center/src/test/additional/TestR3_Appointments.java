package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import med.MedException;
import med.MedManager;

public class TestR3_Appointments {

	private static final String DOC_ID = "FD845";
	private static final String SPECIALITY = "Pediatry";
	private static final String DATE = "2023-06-28";
	private MedManager mgr;
	
	@Before
	public void setUp() throws MedException {
		mgr = new MedManager();
		mgr.addSpecialities(SPECIALITY);
		
		mgr.addDoctor(DOC_ID,"Mary","White",SPECIALITY);

		mgr.addDailySchedule(DOC_ID, DATE, "10:30", "13:00", 30);
		
		mgr.addDoctor("ABC98", "Mick", "Jag", SPECIALITY);
		mgr.addDailySchedule("ABC98", DATE, "08:00", "11:00", 20);

	}

	@Test
	public void testAppointment() throws MedException {
		String ssn = "GVNBNC80B14F219K";
		String a = mgr.setAppointment(ssn,"Giovanni","Bianchi",DOC_ID, DATE,"10:30-11:00");
		
		assertEquals(DOC_ID, mgr.getAppointmentDoctor(a));
		assertEquals(ssn, mgr.getAppointmentPatient(a));
		assertEquals("10:30", mgr.getAppointmentTime(a));
		assertEquals(DATE, mgr.getAppointmentDate(a));
	}

	@Test
	public void testAppointmentBadCode() {
		assertThrows("Invalid doc id for appointment not detected",
				MedException.class,
				()->mgr.setAppointment("GVNBNC80B14F219K","Giovanni","Bianchi", "INV4L10", DATE,"10:30-11:00"));
		
	}

	@Test
	public void testAppointmentBadDate() {
		assertThrows("Wrong date for appointment not detected",
				MedException.class,
				()->mgr.setAppointment("GVNBNC80B14F219K","Giovanni","Bianchi", DOC_ID, "2023-06-01","10:30-11:00"));
		
	}
	
	@Test
	public void testAppointmentBadSlot() {
		assertThrows("Wrong slot for appointment not detected",
				MedException.class,
				()->mgr.setAppointment("GVNBNC80B14F219K","Giovanni","Bianchi", DOC_ID, DATE,"10:40-11:00"));
		
	}

	@Test
	public void testAppointmentBadSlot2() {
		assertThrows("Wrong slot for appointment not detected",
				MedException.class,
				()->mgr.setAppointment("GVNBNC80B14F219K","Giovanni","Bianchi", DOC_ID, DATE,"10:30-11:30"));
		
	}

	@Test
	public void testListAppointments() throws MedException {
		String ssn = "GVNBNC80B14F219K";
		mgr.setAppointment(ssn,"Giovanni","Bianchi", DOC_ID, DATE, "10:30-11:00");
		
		mgr.setAppointment("ANOTHE84P90I174","Filippo","Neri", DOC_ID, DATE, "11:00-11:30");

		Collection<String> appointments = mgr.listAppointments(DOC_ID, DATE);
		assertNotNull("Missing appointments", appointments);
		assertEquals("Wrong number of appointments", 2,appointments.size());
		assertTrue("Could not find appointment with " + ssn, appointments.contains("10:30="+ssn));

	}

	@Test
	public void testListAppointments2() throws MedException {
		String ssn = "GVNBNC80B14F219K";
		mgr.setAppointment(ssn,"Giovanni","Bianchi", DOC_ID, DATE, "10:30-11:00");
		
		mgr.setAppointment("ANOTHE84P90I174","Filippo","Neri", DOC_ID, DATE, "11:00-11:30");
		
		mgr.addDailySchedule(DOC_ID, "2023-07-13", "10:00", "11:00", 20);
		mgr.setAppointment(ssn,"Giovanni","Bianchi", DOC_ID, "2023-07-13", "10:20-10:40");

		Collection<String> appointments = mgr.listAppointments(DOC_ID, "2023-07-13");
		assertNotNull("Missing appointments", appointments);
		assertEquals("Wrong number of appointments", 1,appointments.size());
		assertTrue("Could not find appointment with " + ssn, appointments.contains("10:20="+ssn));

	}

}
