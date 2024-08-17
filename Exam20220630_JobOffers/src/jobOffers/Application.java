package jobOffers;

import jobOffers.JobOffers.Status;

public class Application {
    private final String candidate;
    private final String position;
    private Status status;
    public Application(String candidate, String position) {
        this.candidate = candidate;
        this.position = position;
        this.status=Status.SUBMITTED;
    }

    public String getCandidate() {
        return candidate;
    }

    public String getPosition() {
        return position;
    }
    
}
