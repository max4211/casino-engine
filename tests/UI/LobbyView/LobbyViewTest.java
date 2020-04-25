/*
package UI.LobbyView;

import UI.DukeApplicationTest;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Deprecated
class LobbyViewTest extends DukeApplicationTest {

    private FxRobot myRobot;
    private FlowPane myLobbyPane;
    private BorderPane myGamePane;
    private Button myBlackJackGameIcon;
    private Button myCustomGameIcon;


    @BeforeEach
    public void setUp() throws Exception {
        launch(Main.class);
        myRobot = new FxRobot();
        myLobbyPane = myRobot.lookup("#full-lobby").query();
        myBlackJackGameIcon = myRobot.lookup("#blackJack-icon").query();
        myCustomGameIcon = myRobot.lookup("#custom-icon").query();
    }

    @Test
    public void testCSSApplication() {
        assertEquals(myLobbyPane.getStylesheets().size(), 1);
        assertEquals(myLobbyPane.getStylesheets().get(0), "Sunrise.css");
    }

    // Happy Path :)
    @Test
    public void createCustomGame() {
        myRobot.clickOn(myCustomGameIcon);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140,600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140, 600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140, 600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140, 600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140, 600);
        myGamePane = myRobot.lookup("#game-border-pane").query();
        assertNotNull(myGamePane);
    }

    // Happy Path :)
    @Test
    public void runBlackJack() {
        myRobot.clickOn(myBlackJackGameIcon);
        myGamePane = myRobot.lookup("#game-border-pane").query();
        assertNotNull(myGamePane);
    }

    // Sad Path :(
    @Test
    public void failCustomGame() {
        missGameXML();
        DialogPane myExceptionDialogue = myRobot.lookup("#exception-dialogue").query();
        assertEquals(myExceptionDialogue.getButtonTypes().get(0).getText(), "OK");
        assertEquals(myExceptionDialogue.getHeaderText(), "Invalid Game XML file. Please pick a valid one!");
        assertEquals(myExceptionDialogue.getStylesheets().size(), 1);
        assertEquals(myExceptionDialogue.getStylesheets().get(0), "fire.css");
    }

    // Sad Path :( (that works out in the end)
    @Test
    public void failThenCreateGame() {
        missGameXML();
        myRobot.clickOn(1160,560);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140,600);
        myGamePane = myRobot.lookup("#game-border-pane").query();
        assertNotNull(myGamePane);
    }

    private void missGameXML() {
        myRobot.clickOn(myCustomGameIcon);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140,600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1070, 600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140, 600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140,600);
        myRobot.clickOn(1110, 195);
        myRobot.clickOn(1140,600);
    }
}*/
