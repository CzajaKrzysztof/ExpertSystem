package pl.codecool.provider;

import pl.codecool.xmlparser.factparser.FactParser;
import pl.codecool.xmlparser.ruleparser.RuleParser;

public class ESProvider {
    FactParser factParser;
    RuleParser ruleParser;

    public ESProvider(FactParser factParser, RuleParser ruleParser) {
        this.factParser = factParser;
        this.ruleParser = ruleParser;
    }

    public void collectAnswers(){

    }

    public boolean getAnswerByQuestion(String questionId) {
        return false;
    }

    public String evaluate() {

    }
}
