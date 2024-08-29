package managingProperties;

import java.util.*;

public class Profession {
    private final String name;
    private List<String> professionalList= new ArrayList<>();
    public Profession(String name,String...professionals) {
        this.name = name;
        this.professionalList = Arrays.asList(professionals);
    }
    public String getName() {
        return name;
    }
    public List<String> getProfessionalList() {
        return professionalList;
    }
}
