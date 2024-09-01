package warehouse;

import java.util.*;
import java.util.stream.Collectors;

public class Product {
	private final String code;
	private final String description;
	private int quantity;
	private Map<String,Supplier> suppliersMap=new HashMap<>();
	private Map<Integer,Order> ordersMap=new HashMap<>();
	
	public Product(String code, String description) {
		this.code = code;
		this.description = description;
		this.quantity=0;
	}

	public String getCode(){
		return code;
	}
	public String getDescription(){
		return description;
	}
	public void setQuantity(int quantity){
		this.quantity=quantity;
	}
	public void decreaseQuantity(){
		this.quantity--;
	}
	public int getQuantity(){	
		return quantity;
	}
	public void addSupplier(Supplier supplier){
		suppliersMap.put(supplier.getCodice(), supplier);
	}
	public List<Supplier> suppliers(){
		return suppliersMap.values().stream().sorted(Comparator.comparing(Supplier::getNome)).collect(Collectors.toList());
	}
	public List<Order> pendingOrders(){
			return null;
	}
}
