package books;

public class Chapter {
    private String title;
    private int numPages;
    public Chapter(String title, int numPages) {
        this.title = title;
        this.numPages = numPages;
    }
    public String getTitle() {
        return title;
    }
    public int getNumPages() {
        return numPages;
    }
    
}
