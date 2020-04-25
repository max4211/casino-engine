package UI.GameView;

import UI.DukeApplicationTest;
import UI.Utilities.LanguageBundle;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerInfoTest extends DukeApplicationTest {

    // NOTE: THIS IS DEPENDENT ON LANGUAGE BUNDLE, SO IT IS INTEGRATED!

    private FxRobot myRobot;
    private LanguageBundle myLanguageBundle;
    private PlayerInfo myPlayerInfo;

    private static final String TESTING_BLACKJACK_STARTER = "#blackjack-starter";
    private static final String NAME_DESCRIPTION_TEST_ID = "#name-label";
    private static final String NAME_VALUE_TEST_ID = "#name-value";
    private static final String BANK_DESCRIPTION_TEST_ID = "#bank-label";
    private static final String BANK_VALUE_TEST_ID = "#bank-value";


    @BeforeEach
    public void setUp() {
        myRobot = new FxRobot();
        myLanguageBundle = new LanguageBundle("English");
    }

    private void createPVs() {
    }

    // VERY HAPPY PATH :(
    @Test
    void testSimplePIV() throws Exception {
        runBlackJack();
        Label nameDescriptionLabel = lookup(NAME_DESCRIPTION_TEST_ID).query();
        Label nameValueLabel = lookup(NAME_VALUE_TEST_ID).query();
        Label bankDescriptionLabel = lookup(BANK_DESCRIPTION_TEST_ID).query();
        Label bankValueLabel = lookup(BANK_VALUE_TEST_ID).query();

        assertEquals("Name: ", nameDescriptionLabel.getText());
        assertEquals("Eric", nameValueLabel.getText());
        assertEquals("Bank: ", bankDescriptionLabel.getText());
        assertEquals("100", bankValueLabel);
    }

    private void runBlackJack() throws Exception {

        myRobot.clickOn(725,300);
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