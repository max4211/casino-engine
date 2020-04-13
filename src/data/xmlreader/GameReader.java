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

    private static final String WINNINGHAND_TAG = "WinningHand";
    private static final String LOSINGHAND_TAG = "LosingHand";
    private static final String HAND_TAG = "Hand";

    private static final String ENTRY_TAG = "EntryBet";
    private static final String DEALERACTION_TAG = "DealerAction";
    private static final String PLAYERACTION_TAG = "PlayerAction";
    private static final String TYPE_TAG = "Type";

    private static final String COMPETITION_TAG = "Competition";

    public GameReader(File file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(file);
        traverseXML(myDocument.getDocumentElement());
    }

    public GameReader(String file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(new File(file));
        traverseXML(myDocument.getDocumentElement());
    }

    @Override
    public List<Pair> getDeck() {
//        Element deckElement = this.myDocument.getElementById(DECK_TAG);
        NodeList deckNodeList = getNodeList(DECK_TAG);
        Element deckElement = (Element) deckNodeList.item(ZERO);
        String type = getElement(deckElement, DECK_TYPE);
        int quantity = Integer.parseInt(getElement(deckElement, QUANTITY_TAG));
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
    public List<String> getWinningHands() {
        return getXMLList(WINNINGHAND_TAG, NAME_TAG);
    }

    @Override
    public List<String> getLosingHands() {
        return getXMLList(LOSINGHAND_TAG, NAME_TAG);
    }

    @Override
    public String getEntryBet() {
        return myDocument.getElementsByTagName(ENTRY_TAG).item(ZERO).getTextContent();
    }

    @Override
    public Pair getDealerAction() {
        Node dealerActionNodeList = getNodeList(DEALERACTION_TAG).item(ZERO);
        Element dealerActionElement = (Element) dealerActionNodeList;
        String type = getElement(dealerActionElement, TYPE_TAG);
        String quantity = getElement(dealerActionElement, QUANTITY_TAG);
        return new Pair(type, quantity);
    }

    @Override
    public List<String> getPlayerAction() {
        return getXMLList(PLAYERACTION_TAG, NAME_TAG);
    }

    @Override
    public void savePreferences(String fileName) {

    }

    @Override
    public String getCompetition() {
        return myDocument.getElementsByTagName(COMPETITION_TAG).item(ZERO).getTextContent();
    }

    private NodeList getNodeList(String tag) {
        return myDocument.getElementsByTagName(tag);
    }

    private String getElement(Element e, String tag) {
        System.out.println("e.toString(): " + e.toString());
        return e.getElementsByTagName(tag).item(ZERO).getTextContent();
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
            String suit = getElement(cardElement, SUIT_TAG);
            String value = getElement(cardElement, VALUE_TAG);
            list.add(new Pair(suit, value));
        }
        return list;
    }

    private List<String> getXMLList(String metatag, String subtag) {
        List<String> list = new ArrayList<>();
        NodeList handNodeList = myDocument.getElementsByTagName(metatag);
        for (int index = 0; index < handNodeList.getLength(); index ++) {
            Node handNode = handNodeList.item(index);
            Element handElement = (Element) handNode;
            String name = getElement(handElement, subtag);
            list.add(name);
        }
        return list;
    }

    private void traverseXML(Node node) {
        System.out.println(node.getNodeName());
        NodeList nodeList = node.getChildNodes();
        for (int i = 0; i < nodeList.getLength(); i ++) {
            Node curNode = nodeList.item(i);
            if (curNode.getNodeType() == Node.ELEMENT_NODE)
                traverseXML(curNode);
        }
    }

}
