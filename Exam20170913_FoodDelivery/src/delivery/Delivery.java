package delivery;

import java.util.*;
import java.util.Map.Entry;
import java.util.stream.Collectors;
public class Delivery {
    
    public static enum OrderStatus { NEW, CONFIRMED, PREPARATION, ON_DELIVERY, DELIVERED } 
    private Map<Integer,Customer> customersMap= new HashMap<>();
    private List<Item> itemsList= new ArrayList<>();
    private Map<Integer,Order> ordersMap= new HashMap<>();
    private int customerCounter=0;
    private int orderCounter=0;
    /**
     * Creates a new customer entry and returns the corresponding unique ID.
     * 
     * The ID for the first customer must be 1.
     * 
     * 
     * @param name name of the customer
     * @param address customer address
     * @param phone customer phone number
     * @param email customer email
     * @return unique customer ID (positive integer)
     */
    public int newCustomer(String name, String address, String phone, String email) throws DeliveryException {
        if(customersMap.values().stream().map(Customer::getEmail).collect(Collectors.toList()).contains(email)) throw new DeliveryException();
        customerCounter++;
        customersMap.put(customerCounter, new Customer(customerCounter, name, address, phone, email));
        return customerCounter;
    }
    
    /**
     * Returns a string description of the customer in the form:
     * <pre>
     * "NAME, ADDRESS, PHONE, EMAIL"
     * </pre>
     * 
     * @param customerId
     * @return customer description string
     */
    public String customerInfo(int customerId){
        if(!customersMap.containsKey(customerId)) return null;
        return customersMap.get(customerId).toString();
    }
    
    /**
     * Returns the list of customers, sorted by name
     * 
     */
    public List<String> listCustomers(){
        return customersMap.values().stream().sorted(Comparator.comparing(Customer::getName)).map(Customer::toString).collect(Collectors.toList());
    }
    
    /**
     * Add a new item to the delivery service menu
     * 
     * @param description description of the item (e.g. "Pizza Margherita", "Bunet")
     * @param price price of the item (e.g. 5 EUR)
     * @param category category of the item (e.g. "Main dish", "Dessert")
     * @param prepTime estimate preparation time in minutes
     */
    public void newMenuItem(String description, double price, String category, int prepTime){
        itemsList.add(new Item(description, category, price, prepTime));
    }
    
    /**
     * Search for items matching the search string.
     * The items are sorted by category first and then by description.
     * 
     * The format of the items is:
     * <pre>
     * "[CATEGORY] DESCRIPTION : PRICE"
     * </pre>
     * 
     * @param search search string
     * @return list of matching items
     */
    public List<String> findItem(String search){
        return itemsList.stream().filter(item->item.getDescription().contains(search)).sorted(Comparator.comparing(Item::getCategory).thenComparing(Item::getDescription)).map(Item::toString).collect(Collectors.toList());
    }
    
    /**
     * Creates a new order for the given customer.
     * Returns the unique id of the order, a progressive
     * integer number starting at 1.
     * 
     * @param customerId
     * @return order id
     */
    public int newOrder(int customerId){
        orderCounter++;
        ordersMap.put(orderCounter, new Order(orderCounter, customersMap.get(customerId)));
        return orderCounter;
    }
    
    /**
     * Add a new item to the order with the given quantity.
     * 
     * If the same item is already present in the order is adds the
     * given quantity to the current quantity.
     * 
     * The method returns the final total quantity of the item in the order. 
     * 
     * The item is found through the search string as in {@link #findItem}.
     * If none or more than one item is matched, then an exception is thrown.
     * 
     * @param orderId the id of the order
     * @param search the item search string
     * @param qty the quantity of the item to be added
     * @return the final quantity of the item in the order
     * @throws DeliveryException in case of non unique match or invalid order ID
     */
    public int addItem(int orderId, String search, int qty) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        if(findItem(search).size()>1) throw new DeliveryException();
        Item searchedItem= itemsList.stream().filter(item->item.getDescription().contains(search)).findFirst().orElse(null);
        if(searchedItem==null) throw new DeliveryException();
        ordersMap.get(orderId).addItem(searchedItem, qty);
        return ordersMap.get(orderId).getItemsMap().get(searchedItem);
    }
    
    /**
     * Show the items of the order using the following format
     * <pre>
     * "DESCRIPTION, QUANTITY"
     * </pre>
     * 
     * @param orderId the order ID
     * @return the list of items in the order
     * @throws DeliveryException when the order ID in invalid
     */
    public List<String> showOrder(int orderId) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        return ordersMap.get(orderId).getItemsMap().entrySet().stream().sorted(Comparator.comparingInt(entry->entry.getValue())).map(entry->entry.getKey().getDescription()+", "+entry.getValue()).collect(Collectors.toList());
    }
    
    /**
     * Retrieves the total amount of the order.
     * 
     * @param orderId the order ID
     * @return the total amount of the order
     * @throws DeliveryException when the order ID in invalid
     */
    public double totalOrder(int orderId) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        return ordersMap.get(orderId).getAmount();
    }
    
    /**
     * Retrieves the status of an order
     * 
     * @param orderId the order ID
     * @return the current status of the order
     * @throws DeliveryException when the id is invalid
     */
    public OrderStatus getStatus(int orderId) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        return ordersMap.get(orderId).getStatus();
    }
    
    /**
     * Confirm the order. The status goes from {@code NEW} to {@code CONFIRMED}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>start-up delay (conventionally 5 min)
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code NEW}
     */
    public int confirm(int orderId) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        if(ordersMap.get(orderId).getStatus()!=OrderStatus.NEW) throw new DeliveryException();
        ordersMap.get(orderId).setStatus(OrderStatus.CONFIRMED);
        return 5+ordersMap.get(orderId).getItemsMap().entrySet().stream().map(entry->entry.getKey().getPrepTime()).max(Comparator.comparingInt(Integer::intValue)).orElse(0)+15;
    }

    /**
     * Start the order preparation. The status goes from {@code CONFIRMED} to {@code PREPARATION}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>preparation time (max of all item preparation time)
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code CONFIRMED}
     */
    public int start(int orderId) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        if(ordersMap.get(orderId).getStatus()!=OrderStatus.CONFIRMED) throw new DeliveryException();
        ordersMap.get(orderId).setStatus(OrderStatus.PREPARATION);
        return ordersMap.get(orderId).getItemsMap().entrySet().stream().map(entry->entry.getKey().getPrepTime()).max(Comparator.comparingInt(Integer::intValue)).orElse(0)+15;
    }

    /**
     * Begins the order delivery. The status goes from {@code PREPARATION} to {@code ON_DELIVERY}
     * 
     * Returns the delivery time estimate computed as the sum of:
     * <ul>
     * <li>transportation time (conventionally 15 min)
     * </ul>
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code PREPARATION}
     */
    public int deliver(int orderId) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        if(ordersMap.get(orderId).getStatus()!=OrderStatus.PREPARATION) throw new DeliveryException();
        ordersMap.get(orderId).setStatus(OrderStatus.ON_DELIVERY);
        return 15;
    }
    
    /**
     * Complete the delivery. The status goes from {@code ON_DELIVERY} to {@code DELIVERED}
     * 
     * 
     * @param orderId the order id
     * @return delivery time estimate in minutes
     * @throws DeliveryException when order ID invalid to status not {@code ON_DELIVERY}
     */
    public void complete(int orderId) throws DeliveryException {
        if(!ordersMap.containsKey(orderId)) throw new DeliveryException();
        if(ordersMap.get(orderId).getStatus()!=OrderStatus.ON_DELIVERY) throw new DeliveryException();
        ordersMap.get(orderId).setStatus(OrderStatus.DELIVERED);
    }
    
    /**
     * Retrieves the total amount for all orders of a customer.
     * 
     * @param customerId the customer ID
     * @return total amount
     */
    public double totalCustomer(int customerId){
        if(!customersMap.containsKey(customerId)) return -1.0;
        return ordersMap.values().stream().filter(order->order.getCustomer().getId()==customerId).mapToDouble(Order::getAmount).sum();
    }
    
    /**
     * Computes the best customers by total amount of orders.
     *  
     * @return the classification
     */
    public SortedMap<Double,List<String>> bestCustomers(){
        return customersMap.values().stream().collect(Collectors.groupingBy(customer->totalCustomer(customer.getId()),()-> new TreeMap<>(Collections.reverseOrder()),Collectors.mapping(Customer::toString, Collectors.toList())));
    }
    


   /**
    * Computes the best items by total amount of orders.
    *  
    * @return the classification
   */
   public List<String> bestItems(){
      return itemsList.stream().collect(Collectors.toMap(item->item,item->ordersMap.values().stream().filter(order->order.getItemsMap().containsKey(item)).mapToDouble(order->order.getItemsMap().get(item)*item.getPrice()).sum())).entrySet().stream().sorted(Comparator.comparingDouble(entry->((Entry<Item, Integer>) entry).getValue()).reversed().thenComparing(entry->((Entry<Item, Integer>) entry).getKey().getDescription())).map(entry->entry.getKey().toString()).collect(Collectors.toList());
   }

   /**
   * Computes the most popular items by total quantity ordered.
   *  
   * @return the classification
   */
   public List<String> popularItems(){
    return itemsList.stream().collect(Collectors.toMap(item->item,item->ordersMap.values().stream().filter(order->order.getItemsMap().containsKey(item)).mapToDouble(order->order.getItemsMap().get(item)).sum())).entrySet().stream().sorted(Comparator.comparingDouble(entry->((Entry<Item, Integer>) entry).getValue()).reversed().thenComparing(entry->((Entry<Item, Integer>) entry).getKey().getDescription())).map(entry->entry.getKey().toString()).collect(Collectors.toList());
   }

}
