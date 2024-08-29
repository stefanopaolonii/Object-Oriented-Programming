package sports;

import java.util.Arrays;
import java.util.List;

public class Category {
    private final String name;
    private List<String> linkedActivities;
    public Category(String name, String... linkedActivities) {
        this.name = name;
        this.linkedActivities = Arrays.asList(linkedActivities);
    }
    public String getName() {
        return name;
    }
    public List<String> getLinkedActivities() {
        return linkedActivities;
    }
}
