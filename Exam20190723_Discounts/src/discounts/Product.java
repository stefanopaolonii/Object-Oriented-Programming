package discounts;

public class Product {
    private final String id;
    private Category category;
    private double price;
    public Product(String id, Category category, double price) {
        this.id = id;
        this.category = category;
        this.price = price;
    }
    public String getId() {
        return id;
    }
    public Category getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
}
