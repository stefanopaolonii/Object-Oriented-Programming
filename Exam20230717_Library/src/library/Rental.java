package library;

public class Rental {
    private Reader reader;
    private Book book;
    private String startDate;
    private String endDate;
    public Rental(Reader reader, Book book, String startDate) {
        this.reader = reader;
        this.book = book;
        this.startDate = startDate;
        this.endDate=null;
    }
    public Reader getReader() {
        return reader;
    }
    public Book getBook() {
        return book;
    }
    public String getStartDate() {
        return startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
