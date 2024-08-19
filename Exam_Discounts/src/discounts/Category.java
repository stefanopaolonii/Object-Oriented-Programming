package discounts;

import java.util.*;

public class Category {
    private String code;
    private int discount;

    public Category(String code){
        this.code=code;
    }
    public void setDiscount(int discount){
        this.discount=discount;
    }
    public int getDiscount(){
        return discount;
    }
    public String getCode() {
        return code;
    }
}
