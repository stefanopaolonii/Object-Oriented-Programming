package social;

import java.util.*;

public class Account {
    private final String id;
    private final String name;
    private final String surname;
    private Map<String,Account> friendsMap= new HashMap<>();
    private Map<String,Post> postsMap= new HashMap<>();
    public Account(String id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Map<String, Account> getFriendsMap() {
        return friendsMap;
    }
    public Map<String, Post> getPostsMap() {
        return postsMap;
    }
    public void addFriend(String id,Account account){
        friendsMap.put(id, account);
    }
    public void addPost(String id,Post post){
        postsMap.put(id, post);
    }
    @Override
    public String toString(){
        return id+" "+name+" "+surname;
    }
}
