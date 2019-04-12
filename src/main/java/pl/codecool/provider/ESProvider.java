package pl.codecool.provider;

import pl.codecool.repository.FactRepository;
import pl.codecool.repository.RuleRepository;
import pl.codecool.xmlparser.factparser.FactParser;
import pl.codecool.xmlparser.ruleparser.RuleParser;

public class ESProvider {
    RuleRepository ruleRepository;
    FactRepository factRepository;
    public ESProvider(FactParser factParser, RuleParser ruleParser) {
        ruleRepository = ruleParser.getRuleRepository();
        factRepository = factParser.getFactRepository();
    }

    public void collectAnswers(){

    }

    public boolean getAnswerByQuestion(String questionId) {
        return false;
    }

    public String evaluate() {

    }
}
