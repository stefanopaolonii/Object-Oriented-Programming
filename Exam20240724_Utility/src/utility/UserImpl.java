package utility;



public class UserImpl implements User{
    private final String id;
    private final Type type;
    private final String name;
    private final String surname;
    private final String address;
    private final String email;
    private final String taxcode;
    private final String vat;
    private final String companyname;
    
    public UserImpl(String taxcode,String id, String name, String surname, String address, String email) {
        this.taxcode=taxcode;
        this.id = id;
        this.type = Type.RESIDENTIAL;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.email = email;
        this.vat=null;
        this.companyname=null;
    }
    public UserImpl(String vat,String id, String companyname,String address, String email) {
        this.vat=vat;
        this.id = id;
        this.type = Type.BUSINESS;
        this.companyname=companyname;
        this.address = address;
        this.email = email;
        this.name=null;
        this.surname=null;
        this.taxcode=null;
    }
    @Override
    public String getAddress() {
        return address;
    }
    @Override
    public String getCF() {
        return taxcode;
    }
    @Override
    public String getEmail() {
        return email;
    }
    @Override
    public String getId() {
        return id;
    }
    @Override
    public String getName() {
        return name;
    }
    @Override
    public String getSurname() {
        return surname;
    }
    @Override
    public Type getType() {
        return type;
    } 
    @Override
    public String getCompanyName() {
        return companyname;
    }
    @Override
    public String getVat() {
        return vat;
    } 
}
