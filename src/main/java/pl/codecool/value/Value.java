package pl.codecool.value;

import java.util.ArrayList;
import java.util.List;

public abstract class Value {
    List<String> patterns = new ArrayList<>();
    boolean selectionType;

    public List<String> getInputPattern() {
        return patterns;
    }

    public boolean getSelectionType() {
        return selectionType;
    }
}
