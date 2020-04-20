package xmlreader.readers;

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
    private static final String TYPE_TAG = "Type";
    private static final String ICON_TAG = "Icon";

    private static final String DECK_TAG = "Deck";
    private static final String GAME_TAG = "Game";
    private static final String HAND_TAG = "Hands";
    private static final String PLAYER_TAG = "Players";
    private static final String VIEW_TAG = "View";

    @Test
    void getBundleArguments() throws ParserConfigurationException, SAXException, IOException {
        final String filename = "data/xml/lobbyview/lobbyview_v1.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<Map<String, String>> bundleList = reader.getBundleArguments();
        Map<String, String> bjMap = bundleList.get(0);
        Map<String, String> customMap = bundleList.get(1);
        assertEquals("Blackjack", bjMap.get(NAME_TAG));
        assertEquals("Standard", bjMap.get(TYPE_TAG));
        assertEquals("blackJackIcon.jpg", bjMap.get(ICON_TAG));

        assertEquals("Custom Game", customMap.get(NAME_TAG));
        assertEquals("Custom", customMap.get(TYPE_TAG));
        assertEquals("QuestionMark.jpg", customMap.get(ICON_TAG));
        assertEquals(null, customMap.get(VIEW_TAG));
    }

    @Test
    void getFileArguments() throws ParserConfigurationException, SAXException, IOException {
        final String filename = "data/xml/lobbyview/lobbyview_v1.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<Map<String, File>> bundleList = reader.getFileTags();
        Map<String, File> bjMap = bundleList.get(0);
        Map<String, File> customMap = bundleList.get(1);
        assertEquals(new File("data/xml/deck/standard.xml"), bjMap.get(DECK_TAG));
        assertEquals(new File("data/xml/game/blackjackGame_v2.xml"), bjMap.get(GAME_TAG));
        assertEquals(new File("data/xml/hands/hands.xml"), bjMap.get(HAND_TAG));
        assertEquals(new File("data/xml/players/players.xml"), bjMap.get(PLAYER_TAG));
        assertEquals(new File("data/xml/view/view.xml"), bjMap.get(VIEW_TAG));

        assertEquals(null, customMap.get(VIEW_TAG));
        assertEquals(new File("data/xml/deck/guy.xml"), customMap.get(DECK_TAG));

    }

    @Test
    void getLobbyStylesheet() throws ParserConfigurationException, SAXException, IOException {
        final String filename = "data/xml/lobbyview/lobbyview_v1.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<String> result = reader.getLobbyStylesheet();
        List<String> expected = new ArrayList<String>(List.of("" +
                "sunruse.css", "ice.css"));
        for (int i = 0; i < result.size(); i ++)
            assertEquals(expected.get(i), result.get(i));
    }

    @Test
    void getLobbyLanguages() throws ParserConfigurationException, SAXException, IOException {
        final String filename = "data/xml/lobbyview/lobbyview_v1.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<String> result = reader.getLobbyLanguages();
        List<String> expected = new ArrayList<String>(List.of("" +
                "English", "Spanish"));
        for (int i = 0; i < result.size(); i ++)
            assertEquals(expected.get(i), result.get(i));
    }
}