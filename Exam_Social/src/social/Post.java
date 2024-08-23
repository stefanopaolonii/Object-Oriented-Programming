package social;

public class Post {
    private final String id;
    private final String account;
    private String text;
    public Post(String id, String account, String text) {
        this.id = id;
        this.account = account;
        this.text = text;
    }
    public String getId() {
        return id;
    }
    public String getAccount() {
        return account;
    }
    public String getText() {
        return text;
    }
}
