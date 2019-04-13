package pl.codecool.provider;

import com.sun.org.apache.xpath.internal.operations.Bool;
import pl.codecool.repository.FactRepository;
import pl.codecool.repository.RuleRepository;
import pl.codecool.storedata.Question;
import pl.codecool.xmlparser.factparser.FactParser;
import pl.codecool.xmlparser.ruleparser.RuleParser;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

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
        Iterator ruleIterator = ruleRepository.getIterator();
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

    }
}
