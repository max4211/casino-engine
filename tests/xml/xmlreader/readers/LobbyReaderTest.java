package xml.xmlreader.readers;

import exceptions.GeneralXMLException;
import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class LobbyReaderTest {

    private static final String NAME_TAG = "Name";
    private static final String ICON_TAG = "Icon";

    @Test
    void getBundleArguments() throws GeneralXMLException {
        final String filename = "data/xml/lobbyview/lobbyview_v1.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<Map<String, String>> bundleList = reader.getBundleArguments();
        Map<String, String> bjMap = bundleList.get(0);
        Map<String, String> customMap = bundleList.get(1);
        assertEquals("Blackjack", bjMap.get(NAME_TAG));
        assertEquals("BlackJack4.png", bjMap.get(ICON_TAG));
    }

    @Test
    void getFileArguments() throws GeneralXMLException {
        final String filename = "data/xml/lobbyview/lobbyview_v1.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<List<File>> bundleList = reader.getFileTags();
        List<File> bjFiles = bundleList.get(0);
        assertEquals(new File("data/xml/good/DECK_standard.xml"), bjFiles.get(0));
        assertEquals(new File("data/xml/good/GAME_blackjacksimple.xml"), bjFiles.get(1));
        assertEquals(new File("data/xml/good/HANDS_blackjack.xml"), bjFiles.get(2));
        assertEquals(new File("data/xml/good/PLAYERS_three.xml"), bjFiles.get(3));
        assertEquals(new File("data/xml/good/VIEW_basicview.xml"), bjFiles.get(4));
    }

    @Test
    void getLobbyLanguages() throws GeneralXMLException {
        final String filename = "data/xml/lobbyview/lobbyview_v1.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<String> result = reader.getLobbyLanguages();
        List<String> expected = new ArrayList<String>(List.of(
                "English", "Spanish", "French"));
        assertEquals(expected, result);
    }
}