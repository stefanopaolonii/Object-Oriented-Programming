package discounts;

public class Card {
    private final String name;
    private final int id;
    public Card(String name, int id) {
        this.name = name;
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    
}
