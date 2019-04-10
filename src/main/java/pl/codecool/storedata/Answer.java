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
            
        }
    }

    public void addValue(Value value) {
        valueList.add(value);
    }
}
