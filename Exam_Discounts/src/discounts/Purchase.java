package discounts;

import java.util.ArrayList;
import java.util.List;

public class Purchase {
    private Card card;
    private int pId;
    private List<Line> lines= new ArrayList<>();
    public Purchase(int pId,Card card) {
        this.card = card;
        this.pId=pId;
    }

    public void addLine(Product p, int nofUnit){
        Line line = new Line(p, nofUnit);
        lines.add(line);
    }

    public double getAmount(){
        return lines
            .stream()
            .mapToDouble(Line::getAmount)
            .sum()-this.getDiscount();
    }

    public double getDiscount(){
        if(card==null){
            return 0.0;
        }
        return lines
            .stream()
            .mapToDouble(Line::getDiscount)
            .sum();
    }

    public int getNofUnits(){
        return lines
            .stream()
            .mapToInt(Line::getNofUnits)
            .sum();
    }

    public boolean hasCard(){
        if(card==null){
            return false;
        }
        return true;
    }

    public Card getCard(){
        return card;
    }
}
