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

public class XMLReader implements XMLGeneratorInterface, XMLParseInterface {

    private static Document myDocument;
    private static final int ZERO = 0;
    private static final String XML_EXTENSION = ".xml";
    private static final String DECK_DIRECTORY = "src/data/deck/";

    private static final String PLAYER_TAG = "Player";
    private static final String NAME_TAG = "Name";
    private static final String BANKROLL_TAG = "Bankroll";

    private static final String DECK_TAG = "Deck";
    private static final String QUANTITY_TAG = "Quantity";
    private static final String DECK_TYPE = "Type";

    private static final String CARD_TAG = "Card";
    private static final String SUIT_TAG = "Suit";
    private static final String VALUE_TAG = "Value";


    public XMLReader(File file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    public XMLReader(String file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    @Override
    public List<Pair> getDeck() {
        NodeList deckNodeList = myDocument.getElementsByTagName(DECK_TAG);
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
        return null;
    }

    @Override
    public List<String> getLosingHands() {
        return null;
    }

    @Override
    public Map<String, List> getActions() {
        return null;
    }

    @Override
    public Map<String, Double> getPlayers() {
        Map<String, Double> map = new HashMap<>();
        NodeList playersNodeList = myDocument.getElementsByTagName(PLAYER_TAG);
        for (int index = 0; index < playersNodeList.getLength(); index ++) {
            Node playerNode = playersNodeList.item(index);
            Element playerElement = (Element) playerNode;
            String name = getElement(playerElement, NAME_TAG);
            double roll = Double.parseDouble(getElement(playerElement, BANKROLL_TAG));
            map.put(name, roll);
        }
        return map;
    }

    @Override
    public void savePreferences(String fileName) {

    }

    private String getElement(Element e, String tag) {
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
}
