package warehouse;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Warehouse {
	private Map<String,Product> productsMap= new HashMap<>();
	private Map<String,Supplier> suppliersMap= new HashMap<>();
	private Map<String,Order> ordersMap= new HashMap<>();
	private int orderCounter=1;
	
	public Warehouse() {
	}

	public Product newProduct(String code, String description){
		productsMap.put(code, new Product(code, description));
		return productsMap.get(code);
	}
	
	public Collection<Product> products(){
		return productsMap.values().stream().collect(Collectors.toList());
	}

	public Product findProduct(String code){
		return productsMap.get(code);
	}

	public Supplier newSupplier(String code, String name){
		suppliersMap.put(code, new Supplier(code, name));
		return suppliersMap.get(code);
	}
	
	public Supplier findSupplier(String code){
		return suppliersMap.get(code);
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {
		if(!supp.supplies().contains(prod)) throw new InvalidSupplier();
		String code=String.format("ORD%d", orderCounter++);
		Order newOrder= new Order(code, prod, quantity, supp);
		ordersMap.put(code, newOrder);
		prod.addOrder(newOrder);
		supp.addOrder(newOrder);
		return newOrder;
	}

	public Order findOrder(String code){
		return ordersMap.get(code);
	}
	
	public List<Order> pendingOrders(){
		return ordersMap.values().stream().filter(order->!order.delivered()).sorted(Comparator.comparing((Order order)->order.getCode())).collect(Collectors.toList());
	}

	public Map<String,List<Order>> ordersByProduct(){
	    return null;
	}
	
	public Map<String,Long> orderNBySupplier(){
	    return null;
	}
	
	public List<String> countDeliveredByProduct(){
	    return null;
	}
}
