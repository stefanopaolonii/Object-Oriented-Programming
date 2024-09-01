package delivery;

import java.util.*;

import delivery.Delivery.OrderStatus;

public class Order {
    private final int id;
    private OrderStatus status;
    private Customer customer;
    private Map<Item,Integer> itemsMap= new HashMap<>();

    public Order(int id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.status=OrderStatus.NEW;
    }

    public int getId() {
        return id;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public Customer getCustomer() {
        return customer;
    }
    public Map<Item, Integer> getItemsMap() {
        return itemsMap;
    }
    public void setStatus(OrderStatus status) {
        this.status = status;
    }
    public void addItem(Item item,int quantity){
        if(!itemsMap.containsKey(item)) itemsMap.put(item,quantity);
        else itemsMap.put(item, itemsMap.get(item)+quantity);
    }
    public double getAmount(){
        return itemsMap.entrySet().stream().mapToDouble(entry->entry.getKey().getPrice()*entry.getValue()).sum();
    }
}
