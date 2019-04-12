package pl.codecool.repository;

import pl.codecool.storedata.Fact;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FactRepository {
    private List<Fact> facts;
    Iterator<Fact> factIterator;

    public FactRepository() {
        facts = new ArrayList<>();
        factIterator = new FactIterator();
    }

    public void addFact(Fact fact) {
        facts.add(fact);
    }

    public Iterator<Fact> getFactIterator() {
        return factIterator;
    }

    private class FactIterator implements Iterator<Fact> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return (index < facts.size());
        }

        @Override
        public Fact next() {
            return facts.get(index++);
        }
    }
}
