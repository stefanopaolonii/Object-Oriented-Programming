package transactions;

public class Transaction {
    private final String id;
    private final Carrier carrier;
    private RequestOffer request;
    private RequestOffer offer;
    private int rating=0;
    public Transaction(String id, Carrier carrier, RequestOffer request, RequestOffer offer) {
        this.id = id;
        this.carrier = carrier;
        this.request = request;
        this.offer = offer;
    }
    public String getId() {
        return id;
    }
    public Carrier getCarrier() {
        return carrier;
    }
    public RequestOffer getRequest() {
        return request;
    }
    public RequestOffer getOffer() {
        return offer;
    }
    public int getRating() {
        return rating;
    }
    public void setRating(int rating) {
        this.rating = rating;
    }
}
