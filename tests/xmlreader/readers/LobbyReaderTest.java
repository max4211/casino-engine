package xmlreader.readers;

import org.junit.jupiter.api.Test;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
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
    void getStylesheet() throws ParserConfigurationException, SAXException, IOException {
        final String filename = "data/xml/lobbyview/BlackJackLobby.xml";
        LobbyReader reader = new LobbyReader(filename);
        String result = reader.getStylesheet();
        String expected = "#7454c4";
        assertEquals(expected, result);
    }

    @Test
    void getBundleArguments() throws ParserConfigurationException, SAXException, IOException {
        final String filename = "data/xml/lobbyview/BlackJackLobby.xml";
        LobbyReader reader = new LobbyReader(filename);
        List<Map<String, String>> result = reader.getBundleArguments();
        Map<String, String> bjMap = result.get(0);
        Map<String, String> customMap = result.get(1);
        assertEquals("Blackjack", bjMap.get(NAME_TAG));
        assertEquals("Standard", bjMap.get(TYPE_TAG));
        assertEquals("blackJackIcon.jpg", bjMap.get(ICON_TAG));
        assertEquals("data/xml/deck/standard.xml", bjMap.get(DECK_TAG));
        assertEquals("data/xml/game/blackjackGame_v2.xml", bjMap.get(GAME_TAG));
        assertEquals("data/xml/hands/hands.xml", bjMap.get(HAND_TAG));
        assertEquals("data/xml/players/players.xml", bjMap.get(PLAYER_TAG));
        assertEquals("data/xml/view/view.xml", bjMap.get(VIEW_TAG));

        assertEquals("Custom Game", customMap.get(NAME_TAG));
        assertEquals("Custom", customMap.get(TYPE_TAG));
        assertEquals("QuestionMark.jpg", customMap.get(ICON_TAG));
        assertEquals(null, customMap.get(VIEW_TAG));
    }
}