package utility;

import java.util.Optional;

public class MeterImpl implements Meter{
    private String id;
    private String sn;
    private String brand;
    private String model;
    private String unit;
    private ServicePoint servicepoint;
    public MeterImpl(String id, String sn, String brand, String model, String unit) {
        this.id = id;
        this.sn = sn;
        this.brand = brand;
        this.model = model;
        this.unit = unit;
        this.servicepoint=null;
    }
    @Override
    public String getBrand() {
        return brand;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getModel() {
        return model;
    }
    @Override
    public String getSN() {
        return sn;
    }
    @Override
    public Optional<ServicePoint> getServicePoint() {
        return Optional.ofNullable(servicepoint);
    }
    @Override
    public String getUnit() {
        return unit;
    }

    @Override
    public void setServicePoint(ServicePoint servicepoint){
        this.servicepoint=servicepoint;
    }
}
