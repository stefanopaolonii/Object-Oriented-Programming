package trail;

public class Delegate {
    private final String name;
    private final String surname;
    private final String ssn;
    
    public Delegate(String name, String surname, String ssn) {
        this.name = name;
        this.surname = surname;
        this.ssn = ssn;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getSsn() {
        return ssn;
    }

    
}
