package pl.codecool.xmlparser.ruleparser;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import pl.codecool.repository.RuleRepository;
import pl.codecool.xmlparser.XMLParser;

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
                System.out.println("Question:\t" + ruleElement.getFirstChild().getNextSibling().getTextContent());
                NodeList selectionList = ruleElement.getElementsByTagName("Selection");
                for (int selection = 0; selection < selectionList.getLength(); selection++) {
                    Node selectionNode = selectionList.item(selection);
                    if (selectionNode.getNodeType() == Node.ELEMENT_NODE) {
                        Element selectionElement = (Element) selectionNode;
                        System.out.println("Sel val:\t" + selectionElement.getAttribute("value"));
                        System.out.println("Ans val:\t" + selectionElement.getFirstChild().getNextSibling().getAttributes().getNamedItem("value").getNodeValue());
                    }
                }
            }
        }
    }
}
