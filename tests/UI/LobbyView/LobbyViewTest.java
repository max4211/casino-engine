package UI.LobbyView;

import javafx.scene.layout.FlowPane;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LobbyViewTest extends DukeApplicationTest {

    private FlowPane myLobbyPane;
    private I
    @BeforeEach
    public void setUp() throws Exception {
        launch(Main.class);
        myLobbyPane = new FxRobot().lookup("#full-lobby").query();
        my
    }

    @Test
    public void testCSSApplication() {
        assertEquals(myLobbyPane.getStylesheets().size(), 1);
        assertEquals(myLobbyPane.getStylesheets().get(0), "sunrise.css");
    }

    @Test
    public void testIconLoad() {
        assertEquals();
    }
}