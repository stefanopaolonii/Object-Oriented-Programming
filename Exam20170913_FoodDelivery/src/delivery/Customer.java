package delivery;

public class Customer {
    private final int id;
    private final String name;
    private final String address;
    private final String phone;
    private final String email;
    public Customer(int id, String name, String address, String phone, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.email = email;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public String getPhone() {
        return phone;
    }
    public String getEmail() {
        return email;
    }
    @Override
    public String toString(){
        return name+", "+address+", "+phone+", "+email;
    }
    
}
