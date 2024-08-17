package jobOffers;

import java.util.*;

public class Candidate {
    private final String name;
    private List<String> candidateskills= new ArrayList<>();
    public Candidate(String name, String... skills) {
        this.name = name;
        this.candidateskills = Arrays.asList(skills);
    }
    public String getName() {
        return name;
    }
    public List<String> getCandidateskills() {
        return candidateskills;
    }
    
}
