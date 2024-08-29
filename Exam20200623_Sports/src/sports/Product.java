package sports;

public class Product {
    private final String name;
    private final String activity;
    private final String category;
    public Product(String name, String activity, Category category) {
        this.name = name;
        this.activity = activity;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public String getActivity() {
        return activity;
    }
    public Category getCategory() {
        return category;
    }
}
