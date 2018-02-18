package products;

import kitchen.annotations.ThisCodeSmells;

@ThisCodeSmells()
public enum Packaging {
    CELLOPHANE("cellophane"),
    TETRAPAC("tetrapac"),
    PLASTIC("plastic");

    private String value;

    Packaging(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
