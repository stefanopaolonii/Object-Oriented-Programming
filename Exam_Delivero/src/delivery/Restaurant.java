package delivery;

import java.util.*;

public class Restaurant {
    private final String name;
    private final String category;
    private Map<String,Dish> dishesMap= new HashMap<>();
    private List<Integer> ratingsList= new ArrayList<>();
    public Restaurant(String name, String category) {
        this.name = name;
        this.category = category;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public Map<String, Dish> getDishesMap() {
        return dishesMap;
    }
    public List<Integer> getRatingsList() {
        return ratingsList;
    }
    public void addDish(String dishname,double price){
        dishesMap.put(dishname, new Dish(dishname, price));
    }
}
