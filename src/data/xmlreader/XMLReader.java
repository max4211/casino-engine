package data.xmlreader;

import engine.dealer.Card;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class XMLReader implements XMLGeneratorInterface, XMLParseInterface {

    private static Document myDocument;

    public XMLReader(File file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    public XMLReader(String file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }

    @Override
    public List<Card> getDeck() {
        return null;
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
        return null;
    }

    @Override
    public void savePreferences(String fileName) {

    }
}
