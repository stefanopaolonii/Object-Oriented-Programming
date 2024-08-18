package discounts;

public class Category {
    private final String name;
    private int percentage;
    public Category(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public int getPercentage() {
        return percentage;
    }
    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }
}
