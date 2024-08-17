package utility;

import java.util.Optional;

public class ServicePointImpl implements ServicePoint {
    private String id;
    private String municipality;
    private String address;
    private Point position;
    private Meter meter;
    
    public ServicePointImpl(String id, String municipality, String address, Point position) {
        this.id = id;
        this.municipality = municipality;
        this.address = address;
        this.position = position;
        this.meter=null;
    }

    @Override
    public String getAddress() {
        return address;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public Optional<Meter> getMeter() {
        return Optional.ofNullable(meter);
    }
    @Override
    public String getMunicipality() {
        return municipality;
    }
    @Override
    public Point getPosition() {
        return position;
    }
    @Override
    public void setMeter(Meter meter) {
        this.meter = meter;
    }

}
