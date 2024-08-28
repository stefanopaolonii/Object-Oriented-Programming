package vaccination;

import java.util.*;

public class Person {
    private final String ssn;
    private final String first;
    private final String last;
    private final int year;
    private boolean allocated;
    private String hubname;
    private int day;
    public Person(String ssn, String first, String last, int year) {
        this.ssn = ssn;
        this.first = first;
        this.last = last;
        this.year = year;
        this.allocated=false;
    }
    public String getSsn() {
        return ssn;
    }
    public String getFirst() {
        return first;
    }
    public String getLast() {
        return last;
    }
    public int getYear() {
        return year;
    }
    @Override
    public String toString(){
        return ssn+","+first+","+last;
    }
    public boolean isAllocated() {
        return allocated;
    }
    public void setAllocated(boolean allocated) {
        this.allocated = allocated;
    }
    public void allocate(String hubname,int day){
        this.hubname=hubname;
        this.day=day;
    }
}
