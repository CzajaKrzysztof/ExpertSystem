package pl.codecool.repository;

import pl.codecool.storedata.Question;
import java.util.*;

public class RuleRepository {
    private Map<String, Question> ruleRepository;
    private Iterator<Question> questionIterator;

    public RuleRepository() {
        ruleRepository = new LinkedHashMap<>();
        questionIterator = new QuestionIterator();
    }

    public Iterator<Question> getIterator() {
        return questionIterator;
    }

    public void addQuestion(Question question) {
        ruleRepository.put(question.getId(), question);
    }

    public class QuestionIterator implements Iterator {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < ruleRepository.size();
        }

        @Override
        public Question next() {
            if (this.hasNext()) {
                return (Question) ruleRepository.values().toArray()[index++];
            }
            return null;
        }
    }
}
