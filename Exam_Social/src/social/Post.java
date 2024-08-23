package social;

public class Post {
    private final String id;
    private final Account account;
    private String text;
    private long TimeMillis;
    public Post(String id, Account account, String text,long time) {
        this.id = id;
        this.account = account;
        this.text = text;
        this.TimeMillis=time;
    }
    public String getId() {
        return id;
    }
    public Account getAccount() {
        return account;
    }
    public String getText() {
        return text;
    }
    public long getTimeMillis() {
        return TimeMillis;
    }
}
