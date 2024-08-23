package restaurantChain;

import java.util.*;

public class Order {
    private final String name;
    private List<Menu> menusList= new ArrayList<>();
    private boolean payed;
    public Order(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public List<Menu> getMenusList() {
        return menusList;
    }
    public boolean isPayed() {
        return payed;
    }
    public void addManu(Menu menu){
        menusList.add(menu);
    }
    public void setPayed(boolean payed) {
        this.payed = payed;
    }
}
