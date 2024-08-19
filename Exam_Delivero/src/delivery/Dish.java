package delivery;

public class Dish {
    private final String name;
    private double price;
    public Dish(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
}
