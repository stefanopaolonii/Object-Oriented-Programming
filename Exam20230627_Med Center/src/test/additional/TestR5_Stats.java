package test.additional;

import static org.junit.Assert.*;

import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import med.MedException;
import med.MedManager;

public class TestR5_Stats {

	private static final String SSN2 = "ANOTHE84P90I174";
	private static final String DOC_ID = "FD845";
	private static final String SPECIALITY = "Pediatry";
	private static final String DATE = "2023-06-28";
	private final static String[] SPECIALITIES = {"Ecography","Orthopedy","Cardiology"};
	private final static String SSN = "GVNBNC80B14F219K";

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

		mgr.setAppointment(SSN,"Giovanni","Bianchi", DOC_ID, DATE, "10:30-11:00");
		
		mgr.setAppointment(SSN2,"Filippo","Neri", DOC_ID, DATE, "11:00-11:30");
		
		mgr.setAppointment(SSN,"Giovanni","Bianchi", DOC_ID, "2023-07-13", "10:20-10:40");
		
		mgr.setCurrentDate(DATE);
		
		mgr.accept(SSN2);
		mgr.accept(SSN);
		

	}

	@Test
	public void testNoShow() {

		double noShow = mgr.showRate(DOC_ID, DATE);
		assertEquals(1.0, noShow, 0.001);

	}

	@Test
	public void testCompleteness() {

		Map<String,Double> completeness = mgr.scheduleCompleteness();
		assertNotNull("Missing completeness data", completeness);
		assertEquals("Wrong number of doctors", 5, completeness.size());
		assertEquals("Wrong completeness for doctor " + DOC_ID, 3.0/8.0, completeness.get(DOC_ID), 0.001);

	}

}
