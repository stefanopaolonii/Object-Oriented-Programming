package fit;

import java.util.*;

public class Lesson {
    private String gymname;
    private String activity;
    private int maxattendees;
    private String slot;
    private Set<String> allowedinstructorsSet= new HashSet<>();
    private List<Customer> customersList= new ArrayList<>();
    private String instructors;

    public Lesson(String gymname, String activity, int maxattendees,String slot,String...allowedinstructors) {
        this.gymname = gymname;
        this.activity = activity;
        this.maxattendees = maxattendees;
        this.slot=slot;
        this.allowedinstructorsSet.addAll(Arrays.asList(allowedinstructors));
    }

    public String getGymname() {
        return gymname;
    }
    public String getActivity() {
        return activity;
    }
    public int getMaxattendees() {
        return maxattendees;
    }
    public String getSlot() {
        return slot;
    }
    public Set<String> getAllowedinstructorsSet() {
        return allowedinstructorsSet;
    }
    public List<Customer> getCustomersList() {
        return customersList;
    }
    public String getInstructors() {
        return instructors;
    } 
    public void addCustomer(Customer customer){
        customersList.add(customer);
    }
    public void setInstructors(String instructors) {
        this.instructors = instructors;
    }
}
