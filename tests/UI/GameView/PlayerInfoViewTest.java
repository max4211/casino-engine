package UI.GameView;

import UI.DukeApplicationTest;
import UI.Utilities.LanguageBundle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerInfoViewTest extends DukeApplicationTest {

    // NOTE: THIS IS DEPENDENT ON LANGUAGE BUNDLE, SO IT IS INTEGRATED!

    private FxRobot myRobot;
    private LanguageBundle myLanguageBundle;
    private PlayerInfoView myPlayerInfoView;

    private static final String NAME_HBOX_TEST_ID = "#name-label";
    private static final String BANK_HBOX_TEST_ID = "#bank-value";

    @BeforeEach
    public void setUp() {
        myRobot = new FxRobot();
        LanguageBundle myBundle = new LanguageBundle("English");
    }

    // VERY HAPPY PATH :(
    @Test
    private void testSimplePIV() {
        PlayerInfoView testPlayerInfoView = new PlayerInfoView("Eric", 1000000, myLanguageBundle);
        HBox nameBox = myRobot.lookup(NAME_HBOX_TEST_ID).query();
        HBox bankBox = myRobot.lookup(BANK_HBOX_TEST_ID).query();
    }

    private void runBlackJack() throws Exception {
        launch(Main.class);
        Button myBlackJackGameIcon = myRobot.lookup("#blackJack-icon").query();
        myRobot.clickOn(myBlackJackGameIcon);
    }

    @Test
    public void testLabels() {
        VBox myInfo = myRobot.lookup("#player-info").query();
        HBox nameBox = (HBox) myInfo.getChildren().get(0);
        Label nameLabel = (Label) nameBox.getChildren().get(0);
        assertEquals("Name: ", nameLabel.getText());
        Label inputedName = (Label) nameBox.getChildren().get(1);
        assertEquals("Eric", inputedName.getText());
        HBox bankBox = (HBox) myInfo.getChildren().get(1);
        Label bankLabel = (Label)bankBox.getChildren().get(0);
        assertEquals("Bank: ", bankLabel.getText());
        Label inputedBank = (Label) bankBox.getChildren().get(1);
        assertEquals("500.0", inputedBank.getText());
    }
}