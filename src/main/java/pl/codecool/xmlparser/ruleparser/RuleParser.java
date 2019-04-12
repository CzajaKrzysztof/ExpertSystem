package pl.codecool.xmlparser.ruleparser;

import java.util.ArrayList;
import java.util.Arrays;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.codecool.repository.RuleRepository;
import pl.codecool.storedata.Answer;
import pl.codecool.storedata.Question;
import pl.codecool.value.MultipleValue;
import pl.codecool.value.SingleValue;
import pl.codecool.value.Value;
import pl.codecool.xmlparser.XMLParser;


public class RuleParser extends XMLParser {
    RuleRepository ruleRepository;

    public RuleParser() {
        ruleRepository = new RuleRepository();
        loadXmlDocument("Rule.xml");
        parseRules(doc);
    }

    public RuleRepository getRuleRepository() {
        return ruleRepository;
    }

    private void parseRules(Document doc) {
        NodeList ruleList = getDoc().getElementsByTagName("Rule");
        for (int rule = 0; rule < ruleList.getLength(); rule++) {
            Node ruleNode = ruleList.item(rule);
            if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element ruleElement = (Element) ruleNode;
                Question question = parseQuestion(ruleElement);
                ruleRepository.addQuestion(question);
            }
        }
    }

    private Question parseQuestion(Element ruleElement) {
        String ruleID = ruleElement.getAttribute("id");
        String questionString = ruleElement.getFirstChild().getNextSibling().getTextContent();
        Answer answer = parseAnswer(ruleElement);
        Question question = new Question(ruleID, questionString, answer);
        return question;
    }

    private Answer parseAnswer(Element ruleElement) {
        Answer answer = new Answer();
        NodeList selectionList = ruleElement.getElementsByTagName("Selection");
        for (int selection = 0; selection < selectionList.getLength(); selection++) {
            Node selectionNode = selectionList.item(selection);
            if (selectionNode.getNodeType() == Node.ELEMENT_NODE) {
                Element selectionElement = (Element) selectionNode;
                Value value = parseValue(selectionElement);
                answer.addValue(value);
            }
        }
        return answer;
    }

    private Value parseValue(Element selectionElement) {
        boolean selectionBoolean = Boolean.valueOf(selectionElement.getAttribute("value"));
        String answerValue = selectionElement.getFirstChild().getNextSibling().getAttributes().getNamedItem("value").getNodeValue();
        String[] answerArray = answerValue.split(",");
        if (answerArray.length > 1) {
            Value value = new MultipleValue(new ArrayList<>(Arrays.asList(answerArray)), selectionBoolean);
            return value;
        } else {
            Value value = new SingleValue(answerValue, selectionBoolean);
            return value;
        }
    }
}
