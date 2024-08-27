package trail;

public class Runner {
    private final int bibnumber;
    private final String name;
    private final String surname;
    
    public Runner(int bibnumber, String name, String surname) {
        this.bibnumber = bibnumber;
        this.name = name;
        this.surname = surname;
    }

    public int getBibNumber(){
        return bibnumber;
    }
    public String getName(){
        return name;
    }
    public String getSurname(){
        return surname;
    }

}
