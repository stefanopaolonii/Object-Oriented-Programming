package delivery;

import java.util.*;
import delivery.Delivery.Status;

public class Order {
    private final int code;
    private Status status;
    private String customerName;
    private Restaurant restaurant;
    private int deliveryTime;
    private int deliveryDistance;
    private Map<String,Integer> dishesMap= new HashMap<>();
    public Order(int code, String customerName, Restaurant restaurant, int deliveryTime, int deliveryDistance) {
        this.code = code;
        this.customerName = customerName;
        this.restaurant = restaurant;
        this.deliveryTime = deliveryTime;
        this.deliveryDistance = deliveryDistance;
        this.status=Status.PENDING;
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
    public Restaurant getRestaurant() {
        return restaurant;
    }
    public int getDeliveryTime() {
        return deliveryTime;
    }
    public int getDeliveryDistance() {
        return deliveryDistance;
    }
    public Map<String, Integer> getDishesMap() {
        return dishesMap;
    }
    public void addDish(String dish,int quantity){
        dishesMap.put(dish, quantity);
    }
    public void setStatus(Status status){
        this.status=status;
    }
}
