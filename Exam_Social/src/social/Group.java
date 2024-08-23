package social;

import java.util.*;

public class Group {
    private String name;
    private Map<String,Account> accountsMap= new HashMap<>();
    public Group(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
    public Map<String, Account> getAccountsMap() {
        return accountsMap;
    }
    public void addAccount(String id,Account account){
        accountsMap.put(id, account);
    }
}
