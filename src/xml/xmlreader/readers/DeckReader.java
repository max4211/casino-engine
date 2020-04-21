package xml.xmlreader.readers;

import Utility.StringPair;
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

public class DeckReader implements DeckReaderInterface {

    private static Document myDocument;
    private static final int ZERO = 0;

    private static final String QUANTITY_TAG = "Quantity";
    private static final String TYPE_TAG = "Type";

    private static final String CARD_TAG = "Card";
    private static final String SUIT_TAG = "Suit";
    private static final String VALUE_TAG = "Value";

    public DeckReader(File file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(file);
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    public DeckReader(String file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(new File(file));
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    @Override
    public List<StringPair> getDeck() {
        int quantity = Integer.parseInt(XMLParseInterface.getSingleTag(this.myDocument, QUANTITY_TAG));
        return copyDeck(parseDeck(), quantity);
    }

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
        List<StringPair> list = new ArrayList<>();
        NodeList cardNodeList = this.myDocument.getElementsByTagName(CARD_TAG);
        for (int index = 0; index < cardNodeList.getLength(); index ++) {
            Node cardNode = cardNodeList.item(index);
            Element cardElement = (Element) cardNode;
            String suit = XMLParseInterface.getElement(cardElement, SUIT_TAG);
            String value = XMLParseInterface.getElement(cardElement, VALUE_TAG);
            list.add(new StringPair(suit, value));
        }
        return list;
    }

}
