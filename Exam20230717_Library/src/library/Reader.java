package library;

public class Reader {
    private String name;
    private String surname;
    private String id;
    private boolean inrental;
    public Reader(String name, String surname, String id) {
        this.name = name;
        this.surname = surname;
        this.id = id;
        this.inrental=false;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getId() {
        return id;
    }
    public boolean isInrental() {
        return inrental;
    }
    @Override
    public String toString(){
        return name+" "+surname;
    }
    public void setInrental(boolean inrental) {
        this.inrental = inrental;
    }
}
