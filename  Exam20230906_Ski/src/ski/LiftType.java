package ski;

public class LiftType {
    private final String code;
    private final String category;
    private int capacity;
    public LiftType(String code, String category, int capacity) {
        this.code = code;
        this.category = category;
        this.capacity = capacity;
    }
    public String getCode() {
        return code;
    }
    public String getCategory() {
        return category;
    }
    public int getCapacity() {
        return capacity;
    }
    
}
