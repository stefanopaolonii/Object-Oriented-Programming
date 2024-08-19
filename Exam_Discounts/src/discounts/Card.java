package discounts;

public class Card {
    private String holder;
    private int code;

    public Card(String holder, int code) {
        this.holder = holder;
        this.code = code;
    }

    public String getHolder(){
        return holder;
    }

    public int getCode(){
        return code;
    }
}
