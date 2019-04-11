package pl.codecool.value;

public class SingleValue extends Value{

    public SingleValue(String pattern, boolean selectionType) {
        patterns.add(pattern);
        this.selectionType = selectionType;
    }
}
