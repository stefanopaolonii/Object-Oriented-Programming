package ski;

public class Lift {
    private final String name;
    private LiftType type;
    public Lift(String name, LiftType type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return name;
    }
    public LiftType getType() {
        return type;
    }
}
