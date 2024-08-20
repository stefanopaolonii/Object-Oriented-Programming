package ski;

public class Slope {
    private final String name;
    private String difficulty;
    private Lift lift;
    public Slope(String name, String difficulty, Lift lift) {
        this.name = name;
        this.difficulty = difficulty;
        this.lift = lift;
    }
    public String getName() {
        return name;
    }
    public String getDifficulty() {
        return difficulty;
    }
    public Lift getLift() {
        return lift;
    }
    
}
