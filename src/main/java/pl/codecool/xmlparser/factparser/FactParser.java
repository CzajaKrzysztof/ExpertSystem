package pl.codecool.xmlparser.factparser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import pl.codecool.repository.FactRepository;
import pl.codecool.storedata.Fact;
import pl.codecool.xmlparser.XMLParser;

public class FactParser extends XMLParser {
    private FactRepository factRepository;

    public FactParser() {
        factRepository = new FactRepository();
        loadXmlDocument("src/xml/Fact.xml");
        parseFacts(doc);
    }

    public FactRepository getFactRepository() {
        return factRepository;
    }

    private void parseFacts(Document doc) {
        NodeList factList = doc.getElementsByTagName("Fact");
        for (int i = 0; i < factList.getLength(); i++) {
            Node factNode = factList.item(i);
            if (factNode.getNodeType() == Node.ELEMENT_NODE) {
                Element factElement = (Element) factNode;
                Fact fact = parseFact(factElement);
                factRepository.addFact(fact);
            }
        }
    }

    private Fact parseFact(Element factElement) {
        String factID = factElement.getAttribute("id");
        String description = parseDescription(factElement);
        Fact fact = new Fact(factID, description);
        parseFactEval(factElement, fact);
        return fact;
    }

    private String parseDescription(Element factElement) {
        Node descriptionNode = factElement.getElementsByTagName("Description").item(0);
        Element descriptionElement = (Element) descriptionNode;
        String description = descriptionElement.getAttribute("value");
        return description;
    }

    private void parseFactEval(Element factElement, Fact fact) {
        NodeList evalList = factElement.getElementsByTagName("Eval");
        for (int j = 0; j < evalList.getLength(); j++) {
            Node evalNode = evalList.item(j);
            if (evalNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eval = (Element) evalNode;
                parseSingleEval(eval, fact);
            }
        }
    }

    private void parseSingleEval(Element eval, Fact fact) {
        String evalID = eval.getAttribute("id");
        boolean value = Boolean.valueOf(eval.getTextContent());
        fact.setFactValueById(evalID, value);
    }
}
