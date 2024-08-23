package train;

public class Car {
    private final String id;
    private final int rows;
    private final char lastSeat;
    private String klass;
    public Car(String id, int rows, char lastSeat, String klass) {
        this.id = id;
        this.rows = rows;
        this.lastSeat = lastSeat;
        this.klass = klass;
    }
    public String getId() {
        return id;
    }
    public int getRows() {
        return rows;
    }
    public char getLastSeat() {
        return lastSeat;
    }
    public String getKlass() {
        return klass;
    }
    public int getNseats(){
        return rows*(1+(lastSeat-'A'));
    }
}
