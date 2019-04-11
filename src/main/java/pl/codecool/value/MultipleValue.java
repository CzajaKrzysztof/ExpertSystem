package pl.codecool.value;

import java.util.List;

public class MultipleValue extends Value{

    public MultipleValue(List<String> patternList, boolean selectionType) {
        patterns.addAll(patternList);
        this.selectionType = selectionType;
    }
}
