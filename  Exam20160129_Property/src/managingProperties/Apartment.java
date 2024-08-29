package managingProperties;

public class Apartment {
    private final String building;
    private final int number;
    private final String id;
    public Apartment(String building, int number, String id) {
        this.building = building;
        this.number = number;
        this.id = id;
    }
    public String getBuilding() {
        return building;
    }
    public int getNumber() {
        return number;
    }
    public String getId() {
        return id;
    }
}
