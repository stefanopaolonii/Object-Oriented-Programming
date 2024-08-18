package elective;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Manages elective courses enrollment.
 * 
 *
 */
public class ElectiveManager {
    Map<String,Course> coursesMap = new TreeMap<>();
    Map<String,Student> studentsMap= new TreeMap<>();
    public enum Status {CREATED,ADMITTED,NOTADMITTED};
    /**
     * Define a new course offer.
     * A course is characterized by a name and a number of available positions.
     * 
     * @param name : the label for the request type
     * @param availablePositions : the number of available positions
     */
    public void addCourse(String name, int availablePositions) {
        coursesMap.put(name,new Course(name, availablePositions));
    }
    
    /**
     * Returns a list of all defined courses
     * @return
     */
    public SortedSet<String> getCourses(){
        return (SortedSet<String>) coursesMap.keySet();
    }
    
    /**
     * Adds a new student info.
     * 
     * @param id : the id of the student
     * @param gradeAverage : the grade average
     */
    public void loadStudent(String id, double gradeAverage){
        if(studentsMap.containsKey(id)) studentsMap.get(id).setAvarage(gradeAverage);
        else studentsMap.put(id, new Student(id, gradeAverage));
    }

    /**
     * Lists all the students.
     * 
     * @return : list of students ids.
     */
    public Collection<String> getStudents(){
        return studentsMap.keySet().stream().collect(Collectors.toList());
    }
    
    /**
     * Lists all the students with grade average in the interval.
     * 
     * @param inf : lower bound of the interval (inclusive)
     * @param sup : upper bound of the interval (inclusive)
     * @return : list of students ids.
     */
    public Collection<String> getStudents(double inf, double sup){
        return studentsMap.values().stream().filter(student->student.getAvarage()>=inf && student.getAvarage()<=sup).map(Student::getId).collect(Collectors.toList());
    }


    /**
     * Adds a new enrollment request of a student for a set of courses.
     * <p>
     * The request accepts a list of course names listed in order of priority.
     * The first in the list is the preferred one, i.e. the student's first choice.
     * 
     * @param id : the id of the student
     * @param selectedCourses : a list of of requested courses, in order of decreasing priority
     * 
     * @return : number of courses the user expressed a preference for
     * 
     * @throws ElectiveException : if the number of selected course is not in [1,3] or the id has not been defined.
     */
    public int requestEnroll(String id, List<String> courses)  throws ElectiveException {
        if(courses.size()<1 || courses.size()>3) throw new ElectiveException();
        if(!studentsMap.containsKey(id)) throw new ElectiveException();
        if(courses.stream().anyMatch(course-> !coursesMap.keySet().contains(course))) throw new ElectiveException();
        courses.stream().forEach(coursename-> studentsMap.get(id).addRequests(coursesMap.get(coursename)));
        return courses.size();
    }
    
    /**
     * Returns the number of students that selected each course.
     * <p>
     * Since each course can be selected as 1st, 2nd, or 3rd choice,
     * the method reports three numbers corresponding to the
     * number of students that selected the course as i-th choice. 
     * <p>
     * In case of a course with no requests at all
     * the method reports three zeros.
     * <p>
     * 
     * @return the map of list of number of requests per course
     */
    public Map<String,List<Long>> numberRequests(){
        return coursesMap.values().stream().collect(Collectors.toMap(Course::getName, course-> {List<Long> counts= new ArrayList<>(Arrays.asList(0L,0L,0L)); for(Student s: studentsMap.values()) for(int i=0;i<3;i++) if(i<s.getRequests().size() && s.getRequests().get(i).getName().equals(course.getName())) counts.set(i, counts.get(i)+1);return counts;}));
    }
    

    /**
     * Make the definitive class assignments based on the grade averages and preferences.
     * <p>
     * Student with higher grade averages are assigned to first option courses while they fit
     * otherwise they are assigned to second and then third option courses.
     * <p>
     *  
     * @return the number of students that could not be assigned to one of the selected courses.
     */
    public long makeClasses() {
        studentsMap.values().stream().sorted(Comparator.comparingDouble(Student::getAvarage).reversed()).forEach(student->{List<Course> requests=student.getRequests(); boolean flag=true; for(Course rCourse : requests) {if(rCourse.getStudent().size()<rCourse.getAvailablePosition()){rCourse.addStudent(student); flag=false;break;}} if(flag) student.setStatus(Status.NOTADMITTED); else student.setStatus(Status.ADMITTED);;});
        return studentsMap.values().stream().filter(student->student.getStatus()!=Status.ADMITTED).count();
   
    }
    
    
    /**
     * Returns the students assigned to each course.
     * 
     * @return the map course name vs. student id list.
     */
    public Map<String,List<String>> getAssignments(){
        return coursesMap.values().stream().collect(Collectors.toMap(Course::getName,course->course.getStudent().stream().sorted(Comparator.comparingDouble(Student::getAvarage)).map(Student::getId).collect(Collectors.toList())));
    }
    
    
    /**
     * Adds a new notification listener for the announcements
     * issues by this course manager.
     * 
     * @param listener : the new notification listener
     */
    public void addNotifier(Notifier listener) {
        
    }
    
    /**
     * Computes the success rate w.r.t. to first 
     * (second, third) choice.
     * 
     * @param choice : the number of choice to consider.
     * @return the success rate (number between 0.0 and 1.0)
     */
    public double successRate(int choice){
        return -1;
    }

    
    /**
     * Returns the students not assigned to any course.
     * 
     * @return the student id list.
     */
    public List<String> getNotAssigned(){
        return null;
    }
    
    
}
