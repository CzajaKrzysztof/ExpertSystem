package pl.codecool.storedata;

import pl.codecool.value.Value;

import java.util.ArrayList;
import java.util.List;

public class Answer {
    List<Value> valueList;

    public Answer() {
        valueList = new ArrayList<>();
    }

    public boolean evaluateAnswerByInput(String input) {
        for (Value value: valueList) {
            for (String entry : value.getInputPattern()) {
                if (input.equals(entry)) {
                    return true;
                }
            }
        }
        return false;
    }

    public void addValue(Value value) {
        valueList.add(value);
    }
}
