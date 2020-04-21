package xml.xmlreader.readers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xml.xmlreader.interfaces.HandReaderInterface;
import xml.xmlreader.interfaces.XMLGeneratorInterface;
import xml.xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Collection;

public class HandReader implements HandReaderInterface {

    private static Document myDocument;

    private static final String NAME_TAG = "Name";

    private static final String WINNINGHAND_TAG = "WinningHand";
    private static final String LOSINGHAND_TAG = "LosingHand";
    private static final String HAND_TAG = "Hand";


    public HandReader(File file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(file);
    }

    public HandReader(String file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(new File(file));
//        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    @Override
    public Collection<String> getWinningHands() {
        return XMLParseInterface.getXMLCollection(myDocument, WINNINGHAND_TAG, NAME_TAG);
    }

    @Override
    public Collection<String> getLosingHands() {
        return XMLParseInterface.getXMLCollection(myDocument, LOSINGHAND_TAG, NAME_TAG);
    }
}