package discounts;
import java.util.*;

public class Discounts {
	private int cardCounter=0;
	private Map<Integer,Card> cardsMap= new TreeMap<>();
	private Map<String,Product> productsMap= new HashMap<>();
	private Map<String,Category> categoriesMap= new HashMap<>();
	private Map<Integer,Purchase> purchasesMap= new TreeMap<>();
	private int purchaseCounter=0;
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
		productsMap.put(productId, new Product(categoriesMap.get(categoryId), productId, price));
	}
	
	public double getPrice(String productId) 
			throws DiscountsException {
        if(!productsMap.containsKey(productId)) throw new DiscountsException();
		return productsMap.get(productId).getPrice();
	}

	public int getAveragePrice(String categoryId) throws DiscountsException {
        if(!categoriesMap.containsKey(categoryId)) throw new DiscountsException();
		return (int) Math.round(productsMap.values().stream().filter(product->product.getCategory().getName().equals(categoryId)).mapToDouble(Product::getPrice).average().orElse(0.0));
	}
	
	//R3
	public void setDiscount(String categoryId, int percentage) throws DiscountsException {
		if(!categoriesMap.containsKey(categoryId)) throw new DiscountsException();
		if(percentage<0 || percentage>50) throw new DiscountsException();
		categoriesMap.get(categoryId).setPercentage(percentage);
	}

	public int getDiscount(String categoryId) {
        return categoriesMap.get(categoryId).getPercentage();
	}

	//R4
	public int addPurchase(int cardId, String... items) throws DiscountsException {
        for(String item:items){
			String[] parts= item.split(":");
			if(!productsMap.containsKey(parts[0])) throw new DiscountsException();
		}
		Purchase newPurchase= new Purchase(cardsMap.get(cardId),++purchaseCounter);
		Arrays.asList(items).stream().forEach(item->{String[] parts=item.split(":"); newPurchase.addProduct(productsMap.get(parts[0]), Integer.parseInt(parts[1]));});
		purchasesMap.put(purchaseCounter, newPurchase);
		return purchaseCounter;
	}

	public int addPurchase(String... items) throws DiscountsException {
        return addPurchase(0, items);
	}
	
	public double getAmount(int purchaseCode) {
        return purchasesMap.get(purchaseCode).getTotalPrice();
	}
	
	public double getDiscount(int purchaseCode)  {
		return purchasesMap.get(purchaseCode).getTotatDiscount();
	}
	
	public int getNofUnits(int purchaseCode) {
        return (int) purchasesMap.get(purchaseCode).getProductsMap().entrySet().stream().mapToInt(entry->entry.getValue()).sum();
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
