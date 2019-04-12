package pl.codecool.storedata;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class Fact {
    private String id;
    private String description;
    private Map<String, Boolean> evals;

    public Fact(String id, String description) {
        evals = new LinkedHashMap<>();
        this.id = id;
        this.description = description;
    }

    public Set<String> getIdSet() {
        return evals.keySet();
    }

    public void setFactValueById(String id, boolean value) {
        evals.put(id, value);
    }

    public boolean getValueById(String id) {
        return evals.get(id);
    }

    public String getDescription() {
        return description;
    }
}
