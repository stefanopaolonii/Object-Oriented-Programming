package discounts;

public class Product {
    private String code;
    public String getCode() {
        return code;
    }

    private double price;
    private Category category;

    public Category getCategory() {
        return category;
    }

    public Product(String code, double price, Category category) {
        this.code = code;
        this.price = price;
        this.category=category;
    }
    
    public double getPrice(){
        return price;
    }

    public double getDiscount(){
        return ((100-category.getDiscount())*price)/ (double)100;
    }
}
