package managingProperties;

import managingProperties.PropertyManager.Status;


public class Request {
    private final int id;
    private Owner owner;
    private Apartment apartment;
    private Profession profession;
    private Status status;
    private String professionalId;
    private int amount;
    public Request(int id, Owner owner, Apartment apartment, Profession profession) {
        this.id = id;
        this.owner = owner;
        this.apartment = apartment;
        this.profession = profession;
        this.status=Status.PENDING;
    }
    public int getId() {
        return id;
    }
    public Owner getOwner() {
        return owner;
    }
    public Apartment getApartment() {
        return apartment;
    }
    public Profession getProfession() {
        return profession;
    }
    public Status getStatus() {
        return status;
    }
    public String getProfessionalId() {
        return professionalId;
    }
    public int getAmount() {
        return amount;
    }
    public void setProfessionalId(String professionalId) {
        this.professionalId = professionalId;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
