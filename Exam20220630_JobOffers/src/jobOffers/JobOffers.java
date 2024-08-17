package jobOffers; 
import java.util.*;


public class JobOffers  {

//R1
	public int addSkills (String... skills) {
		return -1;
	}
	
	public int addPosition (String position, String... skillLevels) throws JOException {
		return -1;
	}
	
//R2	
	public int addCandidate (String name, String... skills) throws JOException {
		return -1;
	}
	
	public List<String> addApplications (String candidate, String... positions) throws JOException {
		return null;
	} 
	
	public TreeMap<String, List<String>> getCandidatesForPositions() {
		return null;
	}
	
	
//R3
	public int addConsultant (String name, String... skills) throws JOException {
		return -1;
	}
	
	public Integer addRatings (String consultant, String candidate, String... skillRatings)  throws JOException {
		return -1;
	}
	
//R4
	public List<String> discardApplications() {
		return null;
	}
	
	 
	public List<String> getEligibleCandidates(String position) {
		return null;
	}
	

	
}

		
