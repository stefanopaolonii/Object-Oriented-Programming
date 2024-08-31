package transactions;

import transactions.TransactionManager.Type;

public class RequestOffer {
    private final Type type;
    private final String id;
    private final String place;
    private final String productid;
    public RequestOffer(Type type, String id, String place, String productid) {
        this.type = type;
        this.id = id;
        this.place = place;
        this.productid = productid;
    }
    public Type getType() {
        return type;
    }
    public String getId() {
        return id;
    }
    public String getPlace() {
        return place;
    }
    public String getProductid() {
        return productid;
    }
}
