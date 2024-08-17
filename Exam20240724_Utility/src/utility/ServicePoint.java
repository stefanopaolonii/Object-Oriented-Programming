package utility;

import java.util.Optional;

/**
 * Represents a service point throught which 
 * the service is provisioned.
 */
public interface ServicePoint {

    /**
     * Retrieves the unique ID of the SP
     * @return the id
     */
    public String getId();

    /**
     * Retrieves the minicipality where the SP is located
     * @return the municipality name
     */
    public String getMunicipality();

    /**
     * Retrieves the address where the SP is located
     * @return the address
     */
    public String getAddress();

    /**
     * Retrieves the position.
     * The `x` and `y` attributes correspond
     * to longitude and latitude respectively.
     * @return the position
     */
    public Point getPosition();

    /**
     * Retrieves the meter attached to the service point.
     * If not meter is attached an empty {@link Optional} is returned
     * @return the attached meter, empty if none is present
     */
    public Optional<Meter> getMeter();

    public void setMeter(Meter meter);
}
