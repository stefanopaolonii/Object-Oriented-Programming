package warehouse;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public class Warehouse {
	
	public Product newProduct(String code, String description){
		// TODO: completare
		return null;
	}
	
	public Collection<Product> products(){
		// TODO: completare
		return null;
	}

	public Product findProduct(String code){
		// TODO: completare
		return null;
	}

	public Supplier newSupplier(String code, String name){
		// TODO: completare
		return null;
	}
	
	public Supplier findSupplier(String code){
		// TODO: completare
		return null;
	}

	public Order issueOrder(Product prod, int quantity, Supplier supp)
		throws InvalidSupplier {
		// TODO: completare
		return null;
	}

	public Order findOrder(String code){
		// TODO: completare
		return null;
	}
	
	public List<Order> pendingOrders(){
		// TODO: completare
		return null;
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
