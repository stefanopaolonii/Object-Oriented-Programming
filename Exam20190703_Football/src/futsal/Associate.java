package futsal;

public class Associate {
    private final int id;
    private final String first;
    private final String last;
    private String phoneNo;
    public Associate(int id,String first, String last, String phoneNo) {
        this.id=id;
        this.first = first;
        this.last = last;
        this.phoneNo = phoneNo;
    }
    public int getId() {
        return id;
    }
    public String getFirst() {
        return first;
    }
    public String getLast() {
        return last;
    }
    public String getPhoneNo() {
        return phoneNo;
    }
}
