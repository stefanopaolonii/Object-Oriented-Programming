package elective;

import java.util.*;

public class Course {
    private final String name;
    private int availablePositions;
    private List<Student> studentsList= new ArrayList<>();
    public Course(String name, int availablePositions) {
        this.name = name;
        this.availablePositions = availablePositions;
    }
    public String getName() {
        return name;
    }
    public int getAvailablePosition() {
        return availablePositions;
    }
    public List<Student> getStudent() {
        return studentsList;
    }

    public void addStudent(Student student){
        studentsList.add(student);
    }
}
