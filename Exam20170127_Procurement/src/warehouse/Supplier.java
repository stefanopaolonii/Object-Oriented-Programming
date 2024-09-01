package warehouse;

import java.util.*;

public class Supplier {
	private final String code;
	private final String name;
	private Map<String,Product> productsMap= new HashMap<>();
	
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
	}
	public List<Product> supplies(){
		return null;
	}
}
