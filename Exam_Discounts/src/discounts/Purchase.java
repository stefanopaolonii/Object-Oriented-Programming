package discounts;

import java.util.*;

public class Purchase {
    private  Card card;
    private Map<Product,Integer> productsMap= new HashMap<>();
    private final int code;
    private double totalAmount;
    private double totalDiscount;
    public Purchase(Card card, int code) {
        this.card = card;
        this.code = code;
    }
    public Card getCard() {
        return card;
    }
    public Map<Product, Integer> getProductsMap() {
        return productsMap;
    }
    public int getCode() {
        return code;
    }

    public void addProduct(Product product,int nofunits){
        productsMap.put(product, nofunits);
    double amount = nofunits * product.getPrice();
    if (card == null) {
        totalAmount += amount;
    } else {
        double discount = amount * product.getCategory().getPercentage() / 100.0;
        totalAmount += amount;
        totalDiscount += discount;
        System.out.println("Product: " + product.getId() + ", Amount: " + amount + ", Discount: " + discount + ", Total Discount: " + totalDiscount);
    }
}
    public double getTotalPrice(){
        if(card!=null) return productsMap.entrySet().stream().mapToDouble(entry->entry.getKey().getPrice()*entry.getValue()).sum();
        return productsMap.entrySet().stream().mapToDouble(entry->entry.getKey().getDiscountedPrice()*entry.getValue()).sum();
    }
    public double getTotatDiscount(){
        return productsMap.entrySet().stream().mapToDouble(entry->entry.getKey().getPrice()*entry.getValue()).sum()-getTotalPrice();
    }
}
