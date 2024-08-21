package tvseriesdb;

public class Actor {
    private final String name;
    private final String surname;
    private String nationality;
    public Actor(String name, String surname, String nationality) {
        this.name = name;
        this.surname = surname;
        this.nationality = nationality;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getNationality() {
        return nationality;
    }
}
