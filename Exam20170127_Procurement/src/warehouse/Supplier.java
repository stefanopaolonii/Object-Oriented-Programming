package warehouse;

import java.util.*;
import java.util.stream.Collectors;

public class Supplier {
	private final String code;
	private final String name;
	private Map<String,Product> productsMap= new HashMap<>();
	private Map<String,Order> ordersMap=new HashMap<>();
	
	public Supplier(String code, String name) {
		this.code = code;
		this.name = name;
	}

	public String getCodice(){
		return code;
	}
	public String getNome(){
		return name;
	}
	public void newSupply(Product product){
		productsMap.put(product.getCode(), product);
		product.addSupplier(this);
	}
	public List<Product> supplies(){
		return productsMap.values().stream().sorted(Comparator.comparing(Product::getDescription)).collect(Collectors.toList());
	}
	public void addOrder(Order order){
		ordersMap.put(order.getCode(), order);
	}
	public double deliveryRate(){
		return ordersMap.values().stream().filter(order->order.delivered()).count()/(double) ordersMap.size();
	}
	@Override
	public boolean equals(Object o){
		if(o==this) return true;
		if(o==null || o.getClass()!=this.getClass()) return false;
		Supplier other =(Supplier) o;
		return this.getCodice().equals(other.getCodice());
	}
}
