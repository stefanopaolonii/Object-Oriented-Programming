package discounts;

import java.util.*;

public class Purchase {
    private final int id;
    private int cardid;
    private Map<Product,Integer> productsMap= new HashMap<>();
    
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
    public double getTotal(){
        return productsMap.entrySet().stream().mapToDouble(entry->entry.getKey().getPrice()*entry.getValue()).sum();
    }
    public double getDiscount(){
        if(this.cardid==0) return 0.0;
        return productsMap.entrySet().stream().mapToDouble(entry->{double dpercentage=entry.getKey().getCategory().getDiscount()/(double)100; return entry.getKey().getPrice()*entry.getValue()*dpercentage;}).sum();
    }
}
