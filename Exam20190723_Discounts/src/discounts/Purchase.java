package discounts;

import java.util.*;

public class Purchase {
    private final int id;
    private int cardid;
    private Map<Product,Integer> productsMap= new HashMap<>();
    public Purchase(int id) {
        this.id = id;
    }
    public Purchase(int id, int cardid) {
        this.id = id;
        this.cardid = cardid;
    }
    public int getId() {
        return id;
    }
    public int getCardid() {
        return cardid;
    }
    public Map<Product, Integer> getProductsMap() {
        return productsMap;
    }
    public void addProduct(Product product,int unit){
        productsMap.put(product,unit);
    }
}
