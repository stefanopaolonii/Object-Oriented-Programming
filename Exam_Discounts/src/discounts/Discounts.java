package discounts;
import java.util.*;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import javax.sound.sampled.Line;

import org.junit.platform.engine.support.discovery.SelectorResolver.Match;

public class Discounts {
	private int cardId=0;
	private int purchaseId=0;
	private Map<Integer,Card> cards = new TreeMap<>();
	private Map<String,Product> products = new TreeMap<>();
	private Map<String,Category> categories = new TreeMap<>();
	private Map<Integer,Purchase> purchases = new TreeMap<>();
	
	//R1
	public int issueCard(String name) {
	    Card card= new Card(name, ++cardId);
		cards.put(cardId, card);
		return cardId;
	}
	
    public String cardHolder(int cardN) {
        return cards.get(cardN).getHolder();
    }
    

	public int nOfCards() {
	       return cards.size();

	}
	
	//R2
	public void addProduct(String categoryId, String productId, double price) 
			throws DiscountsException {
				if(products.containsKey(productId)){
					throw new DiscountsException("Product Id already exists");
				}
				Category c=categories.computeIfAbsent(categoryId,Category::new);

				Product p = new Product(productId, price, c);
				products.put(productId, p);
	}
	
	public double getPrice(String productId) 
			throws DiscountsException {
        Product product = products.get(productId);
		if(product==null){
			throw new DiscountsException("Product not exists");
		}
		return product.getPrice();
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
        if(!categories.containsKey(categoryId)) throw new DiscountsException();
		return (int) Math.round(products.values().stream().filter(product->product.getCategory().getCode().equals(categoryId)).mapToDouble(Product::getPrice).average().orElse(0.0));
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
		Category c = categories.get(categoryId);
		if(c==null){
			throw new DiscountsException("Category not exists");
		}

		if(percentage<0 || percentage>50){
			throw new DiscountsException("Invalid discount percentage");
		}
		c.setDiscount(percentage);
	}

	public int getDiscount(String categoryId) {
        return categories.get(categoryId).getDiscount();
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
        Purchase purchase = new Purchase(++purchaseId,cards.get(cardId));
		for(String item :items){
			String parts[]=item.split(":");
			Product p= products.get(parts[0].trim());
			if(p==null){
				throw new DiscountsException("Product not exists");
			}
			purchase.addLine(p,Integer.parseInt(parts[1].trim()));
		}
		purchases.put(purchaseId, purchase);
		return purchaseId;

	}

	public int addPurchase(String... items) throws DiscountsException {
        return addPurchase(0, items);
	}
	
	public double getAmount(int purchaseCode) {
        return purchases.get(purchaseCode).getAmount();
	}
	
	public double getDiscount(int purchaseCode)  {
        return purchases.get(purchaseCode).getDiscount();
	}
	
	public int getNofUnits(int purchaseCode) {
        return purchases.get(purchaseCode).getNofUnits();
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
        return null;
	}
	
	public SortedMap<Integer, Double> totalPurchasePerCard() {
		return purchases
			.values()
			.stream()
			.filter(p -> p.hasCard())
			.collect(Collectors.groupingBy(p -> p.getCard().getCode(),TreeMap:: new,Collectors.summingDouble(p->p.getAmount())));
	}
	
	public int totalPurchaseWithoutCard() {
        return -1;
	}
	
	public SortedMap<Integer, Double> totalDiscountPerCard() {
        return null;
	}


}
