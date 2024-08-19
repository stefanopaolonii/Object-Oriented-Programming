package project;

public class Preference {
    private String email;
    private String name;
    private String surname;
    private String reviewId;
    private Slot slot;
    public Preference(String email, String name, String surname, String reviewId, Slot slot) {
        this.email = email;
        this.name = name;
        this.surname = surname;
        this.reviewId = reviewId;
        this.slot = slot;
    }
    public String getEmail() {
        return email;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public String getReviewId() {
        return reviewId;
    }
    public Slot getSlot() {
        return slot;
    }
}
