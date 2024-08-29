package sports;

public class Rating {
    public Rating(Product product, String userName, int numStars, String comment) {
        this.product = product;
        this.userName = userName;
        this.numStars = numStars;
        this.comment = comment;
    }
    private final Product product;
    private final String userName;
    private final int numStars;
    private final String comment;
    public Product getProduct() {
        return product;
    }
    public String getUserName() {
        return userName;
    }
    public int getNumStars() {
        return numStars;
    }
    public String getComment() {
        return comment;
    }
    @Override
    public String toString(){
        return numStars+":"+comment;
    }
}
