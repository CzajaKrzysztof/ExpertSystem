package pl.codecool.storedata;

import pl.codecool.value.Value;
import java.util.ArrayList;
import java.util.List;

public class Answer {
    List<Value> valueList;

    public Answer() {
        valueList = new ArrayList<>();
    }

    public boolean evaluateAnswerByInput(String input) throws IllegalArgumentException {
        for (Value value: valueList) {
            for (String entry : value.getInputPattern()) {
                if (input.equals(entry)) {
                    return value.getSelectionType();
                }
            }
        }
        throw new IllegalArgumentException();
    }

    public void addValue(Value value) {
        valueList.add(value);
    }
}
