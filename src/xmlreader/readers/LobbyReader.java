package xmlreader.readers;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import xmlreader.interfaces.LobbyReaderInterface;
import xmlreader.interfaces.XMLGeneratorInterface;
import xmlreader.interfaces.XMLParseInterface;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class LobbyReader implements LobbyReaderInterface  {

    private static Document myDocument;
    private static final int ZERO = 0;

    private static final String NAME_TAG = "Name";
    private static final String ICON_TAG = "Icon";

    private static final String DECK_TAG = "DeckFile";
    private static final String GAME_TAG = "GameFile";
    private static final String HAND_TAG = "HandFile";
    private static final String PLAYER_TAG = "PlayerFIle";
    private static final String VIEW_TAG = "ViewFile";

    public LobbyReader(File file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(file);
    }

    public LobbyReader(String file) throws IOException, SAXException, ParserConfigurationException {
        myDocument = XMLGeneratorInterface.createDocument(new File(file));
    }


    @Override
    public String getName() {
        return XMLParseInterface.getSingleTag(myDocument, NAME_TAG);
    }

    @Override
    public String getIcon() {
        return XMLParseInterface.getSingleTag(myDocument, ICON_TAG);
    }

    @Override
    public String getDeckFile() {
        return XMLParseInterface.getSingleTag(myDocument, DECK_TAG);
    }

    @Override
    public String getGameFile() {
        return XMLParseInterface.getSingleTag(myDocument, GAME_TAG);
    }

    @Override
    public String getHandFile() {
        return XMLParseInterface.getSingleTag(myDocument, HAND_TAG);
    }

    @Override
    public String getPlayerFile() {
        return XMLParseInterface.getSingleTag(myDocument, PLAYER_TAG);
    }

    @Override
    public String getViewFile() {
        return XMLParseInterface.getSingleTag(myDocument, VIEW_TAG);
    }
}
