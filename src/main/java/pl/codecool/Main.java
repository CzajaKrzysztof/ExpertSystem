package pl.codecool;

import pl.codecool.provider.ESProvider;
import pl.codecool.xmlparser.factparser.FactParser;
import pl.codecool.xmlparser.ruleparser.RuleParser;

public class Main {
    public static void main(String[] args) {
        ESProvider provider = new ESProvider(new FactParser(), new RuleParser());
        provider.collectAnswers();
        String answer = provider.evaluate();
        System.out.println(answer);
    }
}
