package data.xmlreader;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameReader implements GameReaderInterface {

    private static Document myDocument;
    private static final int ZERO = 0;
    private static final String DECK_DIRECTORY = "src/data/deck/";
    private static final String XML_EXTENSION = ".xml";

    private static final String DECK_TAG = "Deck";
    private static final String QUANTITY_TAG = "Quantity";
    private static final String DECK_TYPE = "Type";

    private static final String CARD_TAG = "Card";
    private static final String SUIT_TAG = "Suit";
    private static final String VALUE_TAG = "Value";
    private static final String NAME_TAG = "Name";

    private static final String ENTRY_TAG = "EntryBet";
    private static final String DEALERACTION_TAG = "DealerAction";
    private static final String PLAYERACTION_TAG = "PlayerAction";
    private static final String TYPE_TAG = "Type";

    private static final String COMPETITION_TAG = "Competition";

    public GameReader(File file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(file);
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    public GameReader(String file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(new File(file));
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    @Override
    public List<Pair> getDeck() {
        NodeList deckNodeList = XMLParseInterface.getNodeList(myDocument, DECK_TAG);
        Element deckElement = (Element) deckNodeList.item(ZERO);
        String type = XMLParseInterface.getElement(deckElement, DECK_TYPE);
        int quantity = Integer.parseInt(XMLParseInterface.getElement(deckElement, QUANTITY_TAG));
        try {
            File deckFile = findDeckFile(type);
            Document deckXML = XMLGeneratorInterface.createDocument(deckFile);
            return parseDeck(deckXML);
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println("could not find deck file");
            return null;
        }
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
    public Pair getDealerAction() {
        Node dealerActionNodeList = XMLParseInterface.getNodeList(myDocument, DEALERACTION_TAG).item(ZERO);
        Element dealerActionElement = (Element) dealerActionNodeList;
        String type = XMLParseInterface.getElement(dealerActionElement, TYPE_TAG);
        String quantity = XMLParseInterface.getElement(dealerActionElement, QUANTITY_TAG);
        return new Pair(type, quantity);
    }

    @Override
    public List<String> getPlayerAction() {
        return XMLParseInterface.getXMLList(myDocument, PLAYERACTION_TAG, NAME_TAG);
    }

    @Override
    public void savePreferences(String fileName) {

    }

    @Override
    public String getCompetition() {
        return myDocument.getElementsByTagName(COMPETITION_TAG).item(ZERO).getTextContent();
    }

    private File findDeckFile(String fileName) {
        String pathName = DECK_DIRECTORY + fileName.toLowerCase() + XML_EXTENSION;
        return new File(pathName);
    }

    // TODO - refactor, very similar to parsePlayers method
    private List<Pair> parseDeck(Document d) {
        List<Pair> list = new ArrayList<>();
        NodeList cardNodeList = d.getElementsByTagName(CARD_TAG);
        for (int index = 0; index < cardNodeList.getLength(); index ++) {
            Node cardNode = cardNodeList.item(index);
            Element cardElement = (Element) cardNode;
            String suit = XMLParseInterface.getElement(cardElement, SUIT_TAG);
            String value = XMLParseInterface.getElement(cardElement, VALUE_TAG);
            list.add(new Pair(suit, value));
        }
        return list;
    }

}
