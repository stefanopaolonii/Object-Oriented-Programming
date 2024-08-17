package futsal;

import futsal.Fields.Features;

public class Field {
    private final int id;
    private Features feature;
    public Field(int id, Features feature) {
        this.id = id;
        this.feature = feature;
    }
    public int getId() {
        return id;
    }
    public Features getFeature() {
        return feature;
    }
    
}
