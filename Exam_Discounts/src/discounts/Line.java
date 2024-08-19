package discounts;

public class Line {
    private Product product;
    private int nOfunit;

    public Line(Product product, int nOfunit) {
        this.product = product;
        this.nOfunit = nOfunit;
    }

    public double getAmount(){
        return product.getPrice()*nOfunit;
    }
    
    public double getDiscount(){
        return product.getDiscount()*nOfunit;
    }

    public int getNofUnits(){
        return nOfunit;
    }
}
