package UI.GameView;

import UI.DukeApplicationTest;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testfx.api.FxRobot;

import static javafx.application.Application.launch;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerInfoViewTest extends DukeApplicationTest {

    private FxRobot myRobot;


    @BeforeEach
    public void setUp() throws Exception {
        launch(Main.class);
        myRobot = new FxRobot();
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