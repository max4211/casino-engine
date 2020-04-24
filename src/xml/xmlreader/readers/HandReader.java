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


    public HandReader(File file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    public HandReader(String file) throws GeneralXMLException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    @Override
    public List<HandBundle> getWinningHands() {
        return parseBundle(WINNINGHAND_TAG);
    }

    @Override
    public List<HandBundle> getLosingHands() {
        return parseBundle(LOSINGHAND_TAG);
    }

    @Override
    public int getCardsInHand() {
        return Integer.parseInt(XMLParseInterface.getSingleTag(myDocument, CARDSINHAND_TAG));
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
