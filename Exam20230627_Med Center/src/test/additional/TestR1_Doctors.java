package test.additional;

import static org.junit.Assert.*;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;

import med.MedException;
import med.MedManager;

public class TestR1_Doctors {

	private MedManager mgr;
	
	@Before
	public void setUp() {
		mgr = new MedManager();
	}

	private final static String[] sp = {"Ecography","Orthopedy","Cardiology"};
	
	@Test
	public void testSpecialities() {
		mgr.addSpecialities(sp);
		
		Collection<String> specs = mgr.getSpecialities();
		
		assertNotNull("No specialities returned", specs);
		assertEquals("Wrong number of specialities", sp.length, specs.size());
		
		for(String s : sp) 
			assertTrue("Missing " + s + " in specialities", specs.contains(s));
		
	}

	@Test
	public void testSpecialitiesNoDup() {
		mgr.addSpecialities(sp);
		
		mgr.addSpecialities("Optician", sp[0]);
		
		Collection<String> specs = mgr.getSpecialities();
		
		assertNotNull("No specialities returned", specs);
		assertEquals("Wrong number of specialities", sp.length + 1, specs.size());
		
		for(String s : sp) 
			assertTrue("Missing " + s + " in specialities", specs.contains("Cardiology"));
		
	}
	
	@Test
	public void testDoctors() throws MedException {
		mgr.addSpecialities("Generics");
		
		String code = "XD345";
		mgr.addDoctor(code,"John","Smith","Generics");

		assertEquals("John",mgr.getDocName(code));
		assertEquals("Smith",mgr.getDocSurname(code));
	}

	@Test
	public void testDoctorsDupCode() throws MedException {
		mgr.addSpecialities("Generics");
		
		String code = "XD345";
		mgr.addDoctor(code,"John","Smith","Generics");

		assertThrows("Duplicate doctor id not detected",
				MedException.class,
				()-> mgr.addDoctor(code,"Mary","White","Generics"));
	}

	@Test
	public void testDoctorsBadSpec() throws MedException {
		mgr.addSpecialities("Generics");
		
		String code = "XD345";
		mgr.addDoctor(code,"John","Smith","Generics");

		assertThrows("Non existent doctor speciality not detected",
				MedException.class,
				()-> mgr.addDoctor("FD845","Mary","White","Pediatry"));
	}

	@Test
	public void testSpecialists() throws MedException {
		mgr.addSpecialities(sp);
		
		String code = "XD345";
		mgr.addDoctor(code,"John","Smith","Cardiology");
		mgr.addDoctor("AH876","Jane","Black","Cardiology");
		mgr.addDoctor("OK358","Jack","Bones","Orthopedy");

		Collection<String> cardiologists = mgr.getSpecialists("Cardiology");
		assertNotNull("Missing specialists", cardiologists);
		assertEquals("Wrong number of cardiologists", 2,cardiologists.size());
		assertTrue("Missing cardiologist: " + code, cardiologists.contains(code));
	}

}
