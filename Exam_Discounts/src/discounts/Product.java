package discounts;

public class Product {
    private Category category;
    private final String id;
    private double price;
    public Product(Category category, String id, double price) {
        this.category = category;
        this.id = id;
        this.price = price;
    }
    public Category getCategory() {
        return category;
    }
    public String getId() {
        return id;
    }
    public double getPrice() {
        return price;
    }
    public double getDiscountedPrice(){
        return (price*(100-category.getPercentage()))/100;
    }
}
