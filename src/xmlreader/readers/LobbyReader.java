package xmlreader.readers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xmlreader.interfaces.LobbyReaderInterface;
import xmlreader.interfaces.XMLGeneratorInterface;
import xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class LobbyReader implements LobbyReaderInterface  {

    private static Document myDocument;

    private static final String STYLESHEET_TAG = "Stylesheet";

    private static final String NAME_TAG = "Name";
    private static final String ICON_TAG = "Icon";
    private static final String Bundle = "Bundle";

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


    public String getName() {
        return XMLParseInterface.getSingleTag(myDocument, NAME_TAG);
    }

    @Override
    public String getStylesheet() {
        return null;
    }

    @Override
    public List<Map<String, String>> getIconArgumetns() {
        return null;
    }
}
