package discounts;

public class Category {
    private final String id;
    private int discount=-1;
    public Category(String id) {
        this.id = id;
    }
    public String getId() {
        return id;
    }
    public int getDiscount() {
        return discount;
    }
    
}
