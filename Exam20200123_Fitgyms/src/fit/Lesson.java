package fit;

import java.util.*;

public class Lesson {
    private String gymname;
    private String activity;
    private int maxattendees;
    private Set<String> slotsSet= new HashSet<>();
    private Set<String> allowedinstructorsSet= new HashSet<>();
    private List<Customer> customersList= new ArrayList<>();
    private String instructors;

    public Lesson(String gymname, String activity, int maxattendees,String slots,String...allowedinstructors) {
        this.gymname = gymname;
        this.activity = activity;
        this.maxattendees = maxattendees;
        this.slotsSet.addAll(Arrays.asList(slots.split(",")));
        this.allowedinstructorsSet.addAll(allowedinstructorsSet);
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
    public Set<String> getSlotsSet() {
        return slotsSet;
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
