package xml.xmlreader.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Utility.StringPair;
import xml.xmlreader.interfaces.GameReaderInterface;
import xml.xmlreader.interfaces.XMLGeneratorInterface;
import xml.xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class GameReader implements GameReaderInterface {

    private static Document myDocument;
    private static final int ZERO = 0;
    private static final String DECK_DIRECTORY = "src/data/deck/";
    private static final String XML_EXTENSION = ".xml";

    private static final String NAME_TAG = "Name";
    private static final String TEXT_NODE = "#text";

    private static final String ENTRY_TAG = "EntryBet";
    private static final String DEALERACTION_TAG = "DealerAction";
    private static final String PLAYERACTION_TAG = "PlayerAction";
    private static final String TYPE_TAG = "Type";
    private static final String QUANTITY_TAG = "Quantity";

    private static final String TABLE_MIN_TAG = "TableMin";
    private static final String TABLE_MAX_TAG = "TableMax";

    private static final String COMPETITION_TAG = "Competition";
    private static final String CARDSHOW_TAG = "CardShow";
    private static final String GOAL_TAG = "Goal";

    public GameReader(File file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(file);
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    public GameReader(String file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    @Override
    public Map<String, Double> getPayoutOdds() {
        return null;
    }

    @Override
    public String getEntryBet() {
        return myDocument.getElementsByTagName(ENTRY_TAG).item(ZERO).getTextContent();
    }

    @Override
    public List<StringPair> getDealerAction() {
        List<StringPair> list = new ArrayList<>();
        NodeList dealerActionNodeList = XMLParseInterface.getNodeList(myDocument, DEALERACTION_TAG);
        for (int index = 0; index < dealerActionNodeList.getLength(); index ++) {
            Node node = dealerActionNodeList.item(index);
            Element dealerActionElement = (Element) node;
            String type = XMLParseInterface.getElement(dealerActionElement, TYPE_TAG);
            String quantity = XMLParseInterface.getElement(dealerActionElement, QUANTITY_TAG);
            list.add(new StringPair(type, quantity));
        }
        return list;
    }

    @Override
    public Collection<String> getPlayerAction() {
        return XMLParseInterface.getXMLCollection(myDocument, PLAYERACTION_TAG, NAME_TAG);
    }

    @Override
    public Map<String, String> getCompetition() {
        Map<String, String> map = new HashMap<>();
        NodeList nodeList = XMLParseInterface.getNodeList(myDocument, COMPETITION_TAG);
        NodeList childNodes = nodeList.item(ZERO).getChildNodes();
        Node child = childNodes.item(ZERO);
        while (child.getNextSibling() != null) {
            child = child.getNextSibling();
            if (!child.getNodeName().equals(TEXT_NODE))
                map.put(child.getNodeName(), child.getTextContent());
        }
        return map;
    }

    @Override
    public double getTableMin() {
        return Double.parseDouble(myDocument.getElementsByTagName(TABLE_MIN_TAG).item(ZERO).getTextContent());
    }

    @Override
    public double getTableMax() {
        return Double.parseDouble(myDocument.getElementsByTagName(TABLE_MAX_TAG).item(ZERO).getTextContent());
    }

    @Override
    public String getCardShow() {
        return myDocument.getElementsByTagName(CARDSHOW_TAG).item(ZERO).getTextContent();
    }

    @Override
    public String getGoal() {
        return myDocument.getElementsByTagName(GOAL_TAG).item(ZERO).getTextContent();
    }

    private File findDeckFile(String fileName) {
        String pathName = DECK_DIRECTORY + fileName.toLowerCase() + XML_EXTENSION;
        return new File(pathName);
    }

}
