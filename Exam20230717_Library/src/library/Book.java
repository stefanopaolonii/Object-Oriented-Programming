package library;

public class Book {
    private String title;
    private String id;
    private boolean inrental;
    public Book(String title, String id) {
        this.title = title;
        this.id = id;
        this.inrental=false;
    }
    public String getTitle() {
        return title;
    }
    public String getId() {
        return id;
    }
    public boolean isInrental() {
        return inrental;
    }
    public void setInrental(boolean inrental) {
        this.inrental = inrental;
    }
    
}
