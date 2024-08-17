package utility;
public interface User {

    enum Type {
        RESIDENTIAL, BUSINESS
    }
    // add the missing methods

    Type getType();
    String getId();
    String getCF();
    String getName();
    String getSurname();
    String getAddress();
    String getEmail();
    String getCompanyName();
    String getVat();
}
