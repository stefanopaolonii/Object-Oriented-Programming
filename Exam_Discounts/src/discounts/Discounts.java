package discounts;
import java.util.*;

public class Discounts {
	private int cardCounter=0;
	private Map<Integer,Card> cardsMap= new TreeMap<>();
	private Map<String,Product> productsMap= new HashMap<>();
	private Map<String,Category> categoriesMap= new HashMap<>();
	//R1
	public int issueCard(String name) {
		cardsMap.put(cardCounter, new Card(name, ++cardCounter));
	    return cardCounter;
	}
	
    public String cardHolder(int cardN) {
        return cardsMap.get(cardN).getName();
    }
    

	public int nOfCards() {
	       return cardsMap.size();
	}
	
	//R2
	public void addProduct(String categoryId, String productId, double price) 
			throws DiscountsException {
		if(productsMap.containsKey(productId)) throw new DiscountsException();
		if(!categoriesMap.containsKey(categoryId)) categoriesMap.put(categoryId, new Category(categoryId));
		productsMap.put(productId, new Product(categoryId, productId, price));
	}
	
	public double getPrice(String productId) 
			throws DiscountsException {
        if(!productsMap.containsKey(productId)) throw new DiscountsException();
		return productsMap.get(productId).getPrice();
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
        if(!categoriesMap.containsKey(categoryId)) throw new DiscountsException();
		return (int) Math.round(productsMap.values().stream().filter(product->product.getCategory().equals(categoryId)).mapToDouble(Product::getPrice).average().orElse(0.0));
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
	}

	public int getDiscount(String categoryId) {
        return -1;
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
        return -1;
	}

	public int addPurchase(String... items) throws DiscountsException {
        return -1;
	}
	
	public double getAmount(int purchaseCode) {
        return -1.0;
	}
	
	public double getDiscount(int purchaseCode)  {
        return -1.0;
	}
	
	public int getNofUnits(int purchaseCode) {
        return -1;
	}
	
	//R5
	public SortedMap<Integer, List<String>> productIdsPerNofUnits() {
        return null;
	}
	
	public SortedMap<Integer, Double> totalPurchasePerCard() {
        return null;
	}
	
	public int totalPurchaseWithoutCard() {
        return -1;
	}
	
	public SortedMap<Integer, Double> totalDiscountPerCard() {
        return null;
	}


}
