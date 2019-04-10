package pl.codecool.repository;

import pl.codecool.storedata.Question;

import java.util.*;

public class RuleRepository {
    Map<String, Question> ruleRepository;

    public RuleRepository() {
        ruleRepository = new TreeMap<>();
    }

    public Iterator<Question> getIterator() {
        return new QuestionIterator();
    }

    public class QuestionIterator implements Iterator {
//        int index = 0;
//        Object[] keys = ruleRepository.keySet().toArray();
        Iterator<String> keyIterator = ruleRepository.keySet().iterator();

        @Override
        public boolean hasNext() {
//            if (index < keys.length) {
//                return true;
//            }
//            return false;
            return keyIterator.hasNext();
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
//                return ruleRepository.get(keys[index++]);
                return ruleRepository.get(keyIterator.next());
            }
            return null;
        }
    }
}
