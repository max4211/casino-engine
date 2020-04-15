package xmlreader.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import Utility.StringPair;
import xmlreader.interfaces.GameReaderInterface;
import xmlreader.interfaces.XMLGeneratorInterface;
import xmlreader.interfaces.XMLParseInterface;

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
        this.myDocument = XMLGeneratorInterface.createDocument(file);
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    public GameReader(String file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(new File(file));
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
    public StringPair getDealerAction() {
        Node dealerActionNodeList = XMLParseInterface.getNodeList(myDocument, DEALERACTION_TAG).item(ZERO);
        Element dealerActionElement = (Element) dealerActionNodeList;
        String type = XMLParseInterface.getElement(dealerActionElement, TYPE_TAG);
        String quantity = XMLParseInterface.getElement(dealerActionElement, QUANTITY_TAG);
        return new StringPair(type, quantity);
    }

    @Override
    public Collection<String> getPlayerAction() {
        return XMLParseInterface.getXMLList(myDocument, PLAYERACTION_TAG, NAME_TAG);
    }

    @Override
    public void savePreferences(String fileName) {

    }

    @Override
    public String getCompetition() {
        return myDocument.getElementsByTagName(COMPETITION_TAG).item(ZERO).getTextContent();
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
