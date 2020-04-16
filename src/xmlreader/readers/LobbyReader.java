package xmlreader.readers;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xmlreader.interfaces.LobbyReaderInterface;
import xmlreader.interfaces.XMLGeneratorInterface;
import xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LobbyReader implements LobbyReaderInterface  {

    private static Document myDocument;

    private static final String STYLESHEET_TAG = "Stylesheet";

    private static final String BUNDLE_TAG = "Bundle";
    private static final String NAME_TAG = "Name";
    private static final String TYPE_TAG = "Type";
    private static final String ICON_TAG = "Icon";


    private static final String DECK_TAG = "Deck";
    private static final String GAME_TAG = "Game";
    private static final String HAND_TAG = "Hands";
    private static final String PLAYER_TAG = "Players";
    private static final String VIEW_TAG = "View";

    public LobbyReader(File file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    public LobbyReader(String file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    @Override
    public String getStylesheet() {
        return XMLParseInterface.getSingleTag(myDocument, STYLESHEET_TAG);
    }

    @Override
    public List<Map<String, String>> getBundleArguments() {
        List<Map<String, String>> list = new ArrayList<>();
        NodeList nodeList = XMLParseInterface.getNodeList(myDocument, BUNDLE_TAG);
        for (int index = 0; index < nodeList.getLength(); index ++) {
            Node node = nodeList.item(index);
            list.add(createMapFromNode(node));
        }
        return list;
    }

    private Map<String, String> createMapFromNode(Node n) {
        Map<String, String> map = new HashMap<>();
        map.put(NAME_TAG, getElementByTag(n, NAME_TAG));
        map.put(ICON_TAG, getElementByTag(n, ICON_TAG));
        map.put(TYPE_TAG, getElementByTag(n, TYPE_TAG));
        try {
            map.put(DECK_TAG, getElementByTag(n, DECK_TAG));
            map.put(GAME_TAG, getElementByTag(n, GAME_TAG));
            map.put(HAND_TAG, getElementByTag(n, HAND_TAG));
            map.put(PLAYER_TAG, getElementByTag(n, PLAYER_TAG));
            map.put(VIEW_TAG, getElementByTag(n, VIEW_TAG));
        } catch (Exception e) {
            System.out.println("did not have files, expected behavior");
        }
        return map;
    }

    private String getElementByTag(Node n, String tag) {
        return XMLParseInterface.getElement(n, tag);
    }

}
