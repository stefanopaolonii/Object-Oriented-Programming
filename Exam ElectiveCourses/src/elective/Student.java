package elective;

import java.util.*;

public class Student {
    private final String id;
    private double avarage;
    private List<Course> requests = new ArrayList<>();
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
    public List<Course> getRequests() {
        return requests;
    }
    public void addRequests(Course course){
        requests.add(course);
    }
    public void setAvarage(double avarage) {
        this.avarage = avarage;
    }
}
