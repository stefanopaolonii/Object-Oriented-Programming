package utility;

import java.util.List;

/**
 * Represents a utility contract
 */
public interface Contract {
    /**
     * Unique ID of the contract
     */
    String getId();
    /**
     * User who signed contract
     */
    User getUser();
    /**
     * Service point bound to the contract
     */
    ServicePoint getServicePoint();

    void addReading(String mcode,String date,double value);

    List<Reading> getReadings();
}
