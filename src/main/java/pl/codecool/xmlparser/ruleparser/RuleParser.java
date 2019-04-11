package pl.codecool.xmlparser.ruleparser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.codecool.repository.RuleRepository;
import pl.codecool.storedata.Answer;
import pl.codecool.value.MultipleValue;
import pl.codecool.value.SingleValue;
import pl.codecool.value.Value;
import pl.codecool.xmlparser.XMLParser;

import java.util.ArrayList;
import java.util.Arrays;

public class RuleParser extends XMLParser {
    RuleRepository ruleRepository;

    public RuleParser() {
        getRuleRepository();
    }

    private RuleRepository getRuleRepository() {
        NodeList ruleList = getDoc().getElementsByTagName("Rule");
        for (int rule = 0; rule < ruleList.getLength(); rule++) {
            Node ruleNode = ruleList.item(rule);
            if (ruleNode.getNodeType() == Node.ELEMENT_NODE) {
                Element ruleElement = (Element) ruleNode;
                String ruleID = ruleElement.getAttribute("id");
                String question = ruleElement.getFirstChild().getNextSibling().getTextContent();
                NodeList selectionList = ruleElement.getElementsByTagName("Selection");
                Answer answer = new Answer();
                for (int selection = 0; selection < selectionList.getLength(); selection++) {
                    Node selectionNode = selectionList.item(selection);
                    if (selectionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element selectionElement = (Element) selectionNode;
                        String selectionValue = selectionElement.getAttribute("value");
                        boolean selectionBoolean = Boolean.valueOf(selectionValue);
                        String answerValue = selectionElement.getFirstChild().getNextSibling().getAttributes().getNamedItem("value").getNodeValue();
                        String[] answerArray = answerValue.split(",");
                        if (answerArray.length > 1) {
                            Value value = new MultipleValue(new ArrayList<String>(Arrays.asList(answerArray)), selectionBoolean);
                            answer.addValue(value);
                        } else {
                            Value value = new SingleValue(answerValue, selectionBoolean);
                            answer.addValue(value);
                        }
                    }
                }
                System.out.println("ID:\t" + ruleID);
                System.out.println("Q:\t" + question);
            }
        }
    }
}
