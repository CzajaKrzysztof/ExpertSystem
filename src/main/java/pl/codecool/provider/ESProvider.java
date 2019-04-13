package pl.codecool.provider;

import pl.codecool.repository.FactRepository;
import pl.codecool.repository.RuleRepository;
import pl.codecool.storedata.Fact;
import pl.codecool.storedata.Question;
import pl.codecool.xmlparser.factparser.FactParser;
import pl.codecool.xmlparser.ruleparser.RuleParser;

import java.util.*;

public class ESProvider {
    RuleRepository ruleRepository;
    FactRepository factRepository;
    Map<String, Boolean> userAnswers;
    Scanner scanner;

    public ESProvider(FactParser factParser, RuleParser ruleParser) {
        ruleRepository = ruleParser.getRuleRepository();
        factRepository = factParser.getFactRepository();
        scanner = new Scanner(System.in);
    }

    public void collectAnswers(){
        userAnswers = new LinkedHashMap<>();
        Iterator<Question> ruleIterator = ruleRepository.getIterator();
        while (ruleIterator.hasNext()) {
            Question question = (Question) ruleIterator.next();
            askQuestion(question);
        }
    }

    private void askQuestion(Question question) {
        boolean isAnswerCorrect = false;
        while (!isAnswerCorrect) {
            System.out.println(question.getQuestion() + "?: ");
            String userInput = scanner.nextLine();
            try {
                boolean evaluation = question.getEvaluatedAnswer(userInput);
                userAnswers.put(question.getId(), evaluation);
                isAnswerCorrect = true;
            } catch (IllegalArgumentException e) {
                System.out.println("No answer by your input");
            }
        }
    }

    public boolean getAnswerByQuestion(String questionId) {
        return false;
    }

    public String evaluate() {
        String factDesctiption = "";
        Iterator<Fact> factIterator = factRepository.getFactIterator();
        while (factIterator.hasNext()) {
            Fact fact = factIterator.next();
            if (compareFactAndUserAnswer(fact)) {
                factDesctiption = fact.getDescription();
            }
        }
        return factDesctiption;
    }

    private boolean compareFactAndUserAnswer(Fact fact) {
        boolean result = false;
        Set<String> evalIDs= fact.getIdSet();
        for (String evalID: evalIDs) {
            boolean evalValue = fact.getValueById(evalID);
            boolean userValue = userAnswers.get(evalID);
            if (evalValue == userValue) {
                result = true;
            } else {
                return false;
            }
        }
        return result;
    }
}
