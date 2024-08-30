package ticketing;

import java.util.*;

public class Component {
    private final String name;
    private Map<String,Component> componentsMap = new HashMap<>();
    private Component parent;
    
    public Component(String name,Component parent) {
        this.name = name;
        this.parent=parent;
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
    public Component getParent() {
        return parent;
    }

}
