package warehouse;

import java.util.*;

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
	public List<Supplier> suppliers(){
		return null;
	}
	public List<Order> pendingOrders(){
			return null;
	}
}
