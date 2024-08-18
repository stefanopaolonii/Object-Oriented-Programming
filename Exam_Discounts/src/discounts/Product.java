package discounts;

public class Product {
    private final String category;
    private final String id;
    private double price;
    public Product(String category, String id, double price) {
        this.category = category;
        this.id = id;
        this.price = price;
    }
    public String getCategory() {
        return category;
    }
    public String getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
}
