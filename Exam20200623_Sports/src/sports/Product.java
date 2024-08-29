package sports;

import java.util.*;

public class Product {
    private final String name;
    private final String activity;
    private final Category category;
    private List<Rating> ratingList= new ArrayList<>();
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
    public List<Rating> getRatingList() {
        return ratingList;
    }
    public void addRating(Rating rating){
        ratingList.add(rating);
    }
}
