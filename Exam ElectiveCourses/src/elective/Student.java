package elective;

import java.util.*;

public class Student {
    private final String id;
    private double avarage;
    private List<String> request;
    public Student(String id, double avarage) {
        this.id = id;
        this.avarage = avarage;
    }
    public String getId() {
        return id;
    }
    public double getAvarage() {
        return avarage;
    }
    public List<String> getRequest() {
        return request;
    }
    public void setAvarage(double avarage) {
        this.avarage = avarage;
    }
}
