package restaurantChain;

public class Menu {
    private String name;
    private double price;
    public Menu(String name, double price) {
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
