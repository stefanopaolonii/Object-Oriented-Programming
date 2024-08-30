package timesheet;

public class Worker {
    private final String id;
    private final String name;
    private final String surname;
    private Profile profile;
    public Worker(String id, String name, String surname, Profile profile) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profile = profile;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getSurname() {
        return surname;
    }
    public Profile getProfile() {
        return profile;
    }
}
