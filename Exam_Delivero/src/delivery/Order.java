package delivery;

import java.util.*;
import delivery.Delivery.Status;

public class Order {
    private final int code;
    private Status status;
    private String customerName;
    private String restaurantName;
    private int deliveryTime;
    private int deliveryDistance;
    private Map<Dish,Integer> dishesMap= new HashMap<>();
    public Order(int code, String customerName, String restaurantName, int deliveryTime, int deliveryDistance) {
        this.code = code;
        this.customerName = customerName;
        this.restaurantName = restaurantName;
        this.deliveryTime = deliveryTime;
        this.deliveryDistance = deliveryDistance;
        this.status=Status.SUBMITTED;
    }
    public int getCode() {
        return code;
    }
    public Status getStatus() {
        return status;
    }
    public String getCustomerName() {
        return customerName;
    }
    public String getRestaurantName() {
        return restaurantName;
    }
    public int getDeliveryTime() {
        return deliveryTime;
    }
    public int getDeliveryDistance() {
        return deliveryDistance;
    }
    public Map<Dish, Integer> getDishesMap() {
        return dishesMap;
    }
    public void addDish(Dish dish,int quantity){
        dishesMap.put(dish, quantity);
    }
}
