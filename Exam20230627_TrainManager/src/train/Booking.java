package train;

public class Booking {
    private String bookingcode;
    private final String ssn;
    private final String name;
    private final String surname;
    private final String begin;
    private final String end;
    private final String car;
    private final String seat;
    private boolean checked;
    public Booking(String bookingcode, String ssn, String name, String surname, String begin, String end, String car,
            String seat) {
        this.bookingcode = bookingcode;
        this.ssn = ssn;
        this.name = name;
        this.surname = surname;
        this.begin = begin;
        this.end = end;
        this.car = car;
        this.seat = seat;
        this.checked=false;
    }
    public String getBookingcode() {
        return bookingcode;
    }
    public String getSsn() {
        return ssn;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getBegin() {
        return begin;
    }
    public String getEnd() {
        return end;
    }
    public String getCar() {
        return car;
    }
    public String getSeat() {
        return seat;
    }
    public String getTrip(){
        return begin+"-"+end;
    }
    public boolean isChecked() {
        return checked;
    }
    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
