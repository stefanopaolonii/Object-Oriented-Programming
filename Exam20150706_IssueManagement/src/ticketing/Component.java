package ticketing;

import java.util.*;

public class Component {
    private final String name;
    private Map<String,Component> componentsMap = new HashMap<>();
    
    public Component(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public Map<String, Component> getComponentsMap() {
        return componentsMap;
    }
    public void addSubcomponent(Component component){
        componentsMap.put(component.getName(), component);
    }
}
