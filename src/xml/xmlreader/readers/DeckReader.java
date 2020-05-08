package xml.xmlreader.readers;

import Utility.StringPair;
import exceptions.GeneralXMLException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.xmlreader.interfaces.DeckReaderInterface;
import xml.xmlreader.interfaces.XMLGeneratorInterface;
import xml.xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Input a Deck file, then open up series of getters to access information internal to the file
 */
public class DeckReader implements DeckReaderInterface {

    private static Document myDocument;

    private static final String QUANTITY_TAG = "Quantity";
    private static final String TYPE_TAG = "Type";

    private static final String CARD_TAG = "Card";
    private static final String SUIT_TAG = "Suit";
    private static final String VALUE_TAG = "Value";

    /**
     * Create the reader from a file
     * @param file is the file to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public DeckReader(File file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    /**
     * Create the reader from string
     * @param file is the string of path to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public DeckReader(String file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    /**
     * Method that translates the XML Deck tag to a List of Pair objects holding the Suit (String) and Value (Integer) for each Card.
     * Called in CardDistribution module to assemble the deck.
     *
     * @return a List with each Pair entry containing the suit and value of a card in the deck.
     */
    @Override
    public List<StringPair> getDeck() {
        int quantity = Integer.parseInt(XMLParseInterface.getSingleTag(myDocument, QUANTITY_TAG));
        return copyDeck(parseDeck(), quantity);
    }

    /**
     * Returns the type of deck, facilitates reflection within the GameConstructor to enable new game types (e.g. Craps/Roulettte)
     * @return the type of deck
     */
    @Override
    public String getType() {
        return XMLParseInterface.getSingleTag(myDocument, TYPE_TAG);
    }

    private List<StringPair> copyDeck(List<StringPair> deck, int quantity) {
        List<StringPair> list = new ArrayList<>();
        for (StringPair sp: deck) {
            for (int i = 0; i < quantity; i ++)
                list.add(sp);
        }
        return list;
    }

    private List<StringPair> parseDeck() {
        NodeList cardNodeList = myDocument.getElementsByTagName(CARD_TAG);
        return XMLParseInterface.parseStringPair(cardNodeList, SUIT_TAG, VALUE_TAG);
    }

}
