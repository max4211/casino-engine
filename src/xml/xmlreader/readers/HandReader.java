package xml.xmlreader.readers;

import Utility.handbundle.HandBundle;
import exceptions.GeneralXMLException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import xml.xmlreader.interfaces.HandReaderInterface;
import xml.xmlreader.interfaces.XMLGeneratorInterface;
import xml.xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * XML reader for hand files with specific getters
 * @author Max Smith
 */
public class HandReader implements HandReaderInterface {

    private static Document myDocument;

    private static final String NAME_TAG = "Name";
    private static final String PARAMS_TAG = "Parameters";
    private static final String MULTIPLIER_TAG = "Multiplier";
    private static final String VIEWNAME_TAG = "ViewName";

    private static final List<String> ALL_TAGS = new ArrayList<String>(List.of(
            NAME_TAG, PARAMS_TAG, MULTIPLIER_TAG, VIEWNAME_TAG
    ));

    private static final String WINNINGHAND_TAG = "WinningHand";
    private static final String LOSINGHAND_TAG = "LosingHand";
    private static final String CARDSINHAND_TAG = "CardsInHand";

    /**
     * Create the reader from string
     * @param file is the file to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public HandReader(File file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    /**
     * Create the reader from string
     * @param file is the string of path to make the reader from
     * @throws GeneralXMLException if the GeneratorInterface cannot parse the file (non xml)
     */
    public HandReader(String file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    /**
     * Translates the XML tag for Winning Java hands into a List of String objects naming each Hand
     * The first entry is the best hand (trumps all) and the last entry is the worst hand (can still win, but loses to every entry before it)
     *
     * @return a List of HandBundles containing the names of all Hands that can win in a given game
     */
    @Override
    public List<HandBundle> getWinningHands() {
        return parseBundle(WINNINGHAND_TAG);
    }

    /**
     * Translates the XML tag for Losing Java jands into naming each Hand
     * Any Hand that is classified as an entry in this list is automatically removed from the game and Garbage Collected by the Table
     *
     * @return a List of HandBundles containing the names of all Hands that can lose in a given game
     */
    @Override
    public List<HandBundle> getLosingHands() {
        return parseBundle(LOSINGHAND_TAG);
    }

    /**
     * Parse CardsInHand tag from XML file
     * @return the appropriate cards in hand tag value
     */
    @Override
    public int getCardsInHand() {
        try {
            return Integer.parseInt(XMLParseInterface.getSingleTag(myDocument, CARDSINHAND_TAG));
        } catch (NullPointerException e) {
            return Integer.MAX_VALUE;
        }
    }

    private List<HandBundle> parseBundle(String handStatusTag) {
        List<HandBundle> list = new ArrayList<>();
        NodeList winningHands = XMLParseInterface.getNodeList(myDocument, handStatusTag);
        for (int index = 0; index < winningHands.getLength(); index ++) {
            Node n = winningHands.item(index);
            List<String> bundleParams = new ArrayList<>();
            for (String tag: ALL_TAGS) {
                String s;
                try {
                    s = XMLParseInterface.getElement(n, tag);
                } catch (Exception e) {
                    s = "";
                }
                bundleParams.add(s);
            }
            list.add(new HandBundle(bundleParams));
        }
        return list;
    }
}
