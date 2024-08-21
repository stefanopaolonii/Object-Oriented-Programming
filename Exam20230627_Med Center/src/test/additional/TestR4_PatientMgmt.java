package test.additional;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import med.MedException;
import med.MedManager;

public class TestR4_PatientMgmt {

	private static final String SSN2 = "ANOTHE84P90I174";
	private static final String DOC_ID = "FD845";
	private static final String SPECIALITY = "Pediatry";
	private static final String DATE = "2023-06-28";
	private final static String[] SPECIALITIES = {"Ecography","Orthopedy","Cardiology"};
	private final static String SSN = "GVNBNC80B14F219K";
	private String a1;

	private MedManager mgr;
	
	@Before
	public void setUp() throws MedException {
		mgr = new MedManager();
		mgr.addSpecialities(SPECIALITY);
		mgr.addSpecialities(SPECIALITIES);
		
		mgr.addDoctor(DOC_ID,"Mary","White",SPECIALITY);

		String code = "XD345";
		mgr.addDoctor(code,"John","Smith","Cardiology");
		mgr.addDoctor("AH876","Jane","Black","Cardiology");
		mgr.addDoctor("OK358","Jack","Bones","Orthopedy");
		mgr.addDoctor("ABC98", "Mick", "Jag", SPECIALITY);


		mgr.addDailySchedule(DOC_ID, DATE, "10:30", "13:00", 30);
		mgr.addDailySchedule(DOC_ID, "2023-07-13", "10:00", "11:00", 20);
		
		mgr.addDailySchedule("ABC98", DATE, "08:00", "11:00", 20);

		a1 = mgr.setAppointment(SSN,"Giovanni","Bianchi", DOC_ID, DATE, "10:30-11:00");
		
		mgr.setAppointment(SSN2,"Filippo","Neri", DOC_ID, DATE, "11:00-11:30");
		
		mgr.setAppointment(SSN,"Giovanni","Bianchi", DOC_ID, "2023-07-13", "10:20-10:40");

	}

	@Test
	public void testCurrentDate() {

		assertEquals(2, mgr.setCurrentDate(DATE));
		
		assertEquals(1, mgr.setCurrentDate("2023-07-13"));

	}

	@Test
	public void testNextAppointments() {

		mgr.setCurrentDate(DATE);
		
		mgr.accept(SSN);
		
		String nextApp = mgr.nextAppointment(DOC_ID);
		assertNotNull("Missing appointments of accepted patients", nextApp);
		assertEquals("Wrong next appointment for the day", a1,nextApp);

	}

	@Test
	public void testNextAppointments2() {

		mgr.setCurrentDate(DATE);
		
		mgr.accept(SSN2);
		mgr.accept(SSN);

		String nextApp = mgr.nextAppointment(DOC_ID);
		assertNotNull("Missing appointments of accepted patients", nextApp);
		assertEquals("Wrong next appointment for the day", a1,nextApp);

	}

	@Test
	public void testNextAppointmentsComplete() throws MedException {

		mgr.setCurrentDate(DATE);
		
		mgr.accept(SSN2);
		mgr.accept(SSN);
		
		String nextApp = mgr.nextAppointment(DOC_ID);
		assertNotNull("Missing appointments of accepted patients", nextApp);
		
		mgr.completeAppointment(DOC_ID, nextApp);

		String secondApp = mgr.nextAppointment(DOC_ID);
		assertNotNull(secondApp);
		assertNotEquals(nextApp,secondApp);

		mgr.completeAppointment(DOC_ID, secondApp);
		
		assertNull("There should be no further appointments", mgr.nextAppointment(DOC_ID));
	}

	@Test
	public void testCompleteBadDoc() {

		mgr.setCurrentDate(DATE);

		mgr.accept(SSN2);
		String nextApp = mgr.nextAppointment(DOC_ID);

		assertThrows("Complete with wrong doctor id not detected",
		MedException.class,
				()->mgr.completeAppointment("N0N3X", nextApp));
	}

	@Test
	public void testCompleteBadApp() {

		mgr.setCurrentDate(DATE);

		mgr.accept(SSN2);

		assertThrows("Complete with invalid app id not detected",
				MedException.class,
				()->mgr.completeAppointment(DOC_ID, "N04PP"));
	}


	@Test
	public void testCompleteNotAccepted() {

		mgr.setCurrentDate(DATE);

		assertThrows("Complete with patient not yet accepted not detected",
				MedException.class,
				()->mgr.completeAppointment(DOC_ID, a1));
	}

}
