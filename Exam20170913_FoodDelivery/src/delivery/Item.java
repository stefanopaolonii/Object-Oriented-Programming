package delivery;

public class Item {
    private String description;
    private String category;
    private double price;
    private int prepTime;
    public Item(String description, String category, double price, int prepTime) {
        this.description = description;
        this.category = category;
        this.price = price;
        this.prepTime = prepTime;
    }
    public String getDescription() {
        return description;
    }
    public String getCategory() {
        return category;
    }
    public double getPrice() {
        return price;
    }
    public int getPrepTime() {
        return prepTime;
    }
    @Override
    public String toString(){
        return "["+category+"] "+description+" : "+String.format("%.2f", price);
    }
}
