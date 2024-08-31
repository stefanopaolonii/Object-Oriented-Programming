package transactions;

import transactions.TransactionManager.Type;

public class RequestOffer {
    private final Type type;
    private final String id;
    private final String place;
    private final Region region;
    private final String productid;
    public RequestOffer(Type type, String id, String place,Region region, String productid) {
        this.type = type;
        this.id = id;
        this.place = place;
        this.region=region;
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
    public Region getRegion() {
        return region;
    }
}
