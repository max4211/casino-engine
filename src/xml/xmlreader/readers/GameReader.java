package xml.xmlreader.readers;

import exceptions.GeneralXMLException;
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

/**
 * XML reader for Game XML files
 * @author Max Smith
 */
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

    /**
     * Create the reader from a file
     * @param file is the file to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public GameReader(File file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    /**
     * Create the reader from string
     * @param file is the string of path to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public GameReader(String file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    /**
     * Method that translates the XML Payout Odds tag to a Map
     * Each entry in the Map is a String detailing the Hand Type and a Double indicating its payoff odds
     * If key is not found, assume 1:1 payout odds.
     *
     * @return a Map containing the Payout Odds for every type of winning bet that is not 1:1 odds
     */
    @Override
    public Map<String, Double> getPayoutOdds() {
        return null;
    }

    /**
     * Fetch entry bet, all casino games start with such a bet and this is a differentiating factor driven by data
     * @return a string containing the entry bet type (e.g. Generic or Specific)
     */
    @Override
    public String getEntryBet() {
        return myDocument.getElementsByTagName(ENTRY_TAG).item(ZERO).getTextContent();
    }

    /**
     * Fetch dealer opening action, all casino games start with dealer action
     * @return a pair containing action type and quantity
     */
    @Override
    public List<StringPair> getDealerAction() {
        NodeList dealerActionNodeList = XMLParseInterface.getNodeList(myDocument, DEALERACTION_TAG);
        return XMLParseInterface.parseStringPair(dealerActionNodeList, TYPE_TAG, QUANTITY_TAG);
    }

    /**
     * Fetch ingame player actions possible
     * @return a List of strings containing all possible player actions
     */
    @Override
    public Collection<String> getPlayerAction() {
        return XMLParseInterface.getXMLCollection(myDocument, PLAYERACTION_TAG, NAME_TAG);
    }

    /**
     * Get the competition parameters for this type of game
     * @return
     */
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

    /**
     * Get table limits to help organize bet structure
     * @return table minimum
     */
    @Override
    public double getTableMin() {
        return Double.parseDouble(myDocument.getElementsByTagName(TABLE_MIN_TAG).item(ZERO).getTextContent());
    }

    /**
     * Get table limits to help organize bet structure
     * @return table maximum
     */
    @Override
    public double getTableMax() {
        return Double.parseDouble(myDocument.getElementsByTagName(TABLE_MAX_TAG).item(ZERO).getTextContent());
    }

    /**
     * Fetch the card showing policy for players
     * @return card show policy
     */
    @Override
    public String getCardShow() {
        return myDocument.getElementsByTagName(CARDSHOW_TAG).item(ZERO).getTextContent();
    }

    /**
     * Fetch the goal of the game (per round)
     * @return goal of game
     */
    @Override
    public String getGoal() {
        return myDocument.getElementsByTagName(GOAL_TAG).item(ZERO).getTextContent();
    }

    private File findDeckFile(String fileName) {
        String pathName = DECK_DIRECTORY + fileName.toLowerCase() + XML_EXTENSION;
        return new File(pathName);
    }

}
