package utility;
import java.util.Optional;

/**
 * Represents a meter that can be attached to a service point
 */
public interface Meter {

    /**
     * Retrieves unique ID of the meter
     * @return the id
     */
    public String getId();
    
    /**
     * Retrieves the serial number of the meter
     * @return the SN
     */
    public String getSN();
    
    /**
     * Retrieves the meter brand
     * @return the brand
     */
    public String getBrand();
    
    /**
     * Retrieves the model of the meter
     * @return the model
     */
    public String getModel();
    
    /**
     * Retrieves the measurement unit of the meter
     * @return the unit of measure
     */
    public String getUnit();
    
    /**
     * Retrieves the service point to which the meter is attached.
     * IF the meter is not attached to any service point, the result will be empty,
     * i.e. the Optional.isEmpty() method will return `true`.
     * @return the service point, empty if not attached to any SP
     */
    public Optional<ServicePoint> getServicePoint();

    public void setServicePoint(ServicePoint servicepoint);
}
