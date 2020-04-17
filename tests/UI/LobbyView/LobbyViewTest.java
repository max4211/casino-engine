package UI.LobbyView;

import javafx.scene.layout.FlowPane;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LobbyViewTest extends DukeApplicationTest {

    private FlowPane myLobbyView;
    @BeforeEach
    public void setUp() throws Exception {
        launch(Main.class);
        myLobbyView = new FxRobot().lookup("#full-lobby").query();

    }

    @Test
    public void testCSSApplication() {
        assertEquals(myLobbyView.getStylesheets().size(), 1);
        assertEquals(myLobbyView.getStylesheets().get(0), "sunrise.css");
    }

    @Test
    public void testIconLoad() {

    }
}