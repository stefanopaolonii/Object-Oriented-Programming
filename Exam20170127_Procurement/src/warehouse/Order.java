package warehouse;

public class Order {
	private final String code;
	private Product product;
	private int quantity;
	private Supplier supplier;
	private boolean delivered;

	public Order(String code, Product product, int quantity, Supplier supplier) {
		this.code = code;
		this.product = product;
		this.quantity = quantity;
		this.supplier = supplier;
		this.delivered=false;
	}

	public String getCode(){
		return code;
	}
	public boolean delivered(){
		return delivered;
	}
	public void setDelivered() throws MultipleDelivery {
		if(delivered) throw new MultipleDelivery();
		this.delivered=true;
	}
	public String toString(){
		return "Order "+code+" for "+quantity+" of "+product.getCode()+" : "+product.getDescription()+" from "+supplier.getNome();
	}
	public int getQuantity() {
		return quantity;
	}
}
