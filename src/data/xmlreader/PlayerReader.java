package data.xmlreader;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.Map;

public class PlayerReader implements PlayerReaderInterface {

    private static Document myDocument;

    private static final String PLAYER_TAG = "Player";
    private static final String NAME_TAG = "Name";
    private static final String BANKROLL_TAG = "Bankroll";

    public PlayerReader(File file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(file);
        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }

    public PlayerReader(String file) throws IOException, SAXException, ParserConfigurationException {
        this.myDocument = XMLGeneratorInterface.createDocument(new File(file));
        XMLParseInterface.traverseXML(myDocument.getDocumentElement());
    }



    @Override
    public Map<String, Double> getPlayers() {
        return null;
    }
}
