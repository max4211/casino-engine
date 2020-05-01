package UI.GameView;

import UI.DukeApplicationTest;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import ooga.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlayerInfoTest extends DukeApplicationTest {

    // NOTE: THIS IS DEPENDENT ON LANGUAGE BUNDLE, SO IT IS INTEGRATED!
    // NOTE - BE IN LOBBYVIEW V1 @FUTURE MAX :)

    // HAPPY PATH :)
    @Test
    void testEricThroughMain() throws Exception {
        launch(Main.class);
        VBox blackJackStarter = lookup("#LobbyView #GameStarter").query();
        clickOn(blackJackStarter);

        Button launchButton = lookup("#AllFilesDisplay #Button").query();
        clickOn(launchButton);

        VBox PlayerView = lookup("#OtherPlayers #PlayerView #PlayerInfo").query();
        HBox nameLabels = (HBox) PlayerView.getChildren().get(0);
        HBox bankLabels = (HBox) PlayerView.getChildren().get(1);

        Label nameDescriptor = (Label) nameLabels.getChildren().get(0);
        Label nameValue = (Label) nameLabels.getChildren().get(1);

        Label bankDescriptor = (Label) bankLabels.getChildren().get(0);
        Label bankValue = (Label) bankLabels.getChildren().get(1);

        assertEquals("Name: ", nameDescriptor.getText());
        assertEquals("Eric", nameValue.getText());
        assertEquals("Bank: ", bankDescriptor.getText());
        assertEquals("475.0", bankValue.getText());
    }

    // HAPPY PATH :)
    @Test
    void testDoubleFormatting() throws Exception {
        launch(PlayerInfoTester.class);
        VBox maxPlayerInfo = lookup("#starting-pane #double-input").query();

        HBox nameLabels = (HBox) maxPlayerInfo.getChildren().get(0);
        HBox bankLabels = (HBox) maxPlayerInfo.getChildren().get(1);

        Label nameDescriptor = (Label) nameLabels.getChildren().get(0);
        Label nameValue = (Label) nameLabels.getChildren().get(1);

        Label bankDescriptor = (Label) bankLabels.getChildren().get(0);
        Label bankValue = (Label) bankLabels.getChildren().get(1);

        assertEquals("Name: ", nameDescriptor.getText());
        assertEquals("Max", nameValue.getText());
        assertEquals("Bank: ", bankDescriptor.getText());
        assertEquals("200.0", bankValue.getText());
    }

    // SAD PATH :(
    @Test
    void testEmptyName() throws Exception {
        launch(PlayerInfoTester.class);
        VBox maxPlayerInfo = lookup("#starting-pane #empty-name").query();

        HBox nameLabels = (HBox) maxPlayerInfo.getChildren().get(0);
        HBox bankLabels = (HBox) maxPlayerInfo.getChildren().get(1);

        Label nameDescriptor = (Label) nameLabels.getChildren().get(0);
        Label nameValue = (Label) nameLabels.getChildren().get(1);

        Label bankDescriptor = (Label) bankLabels.getChildren().get(0);
        Label bankValue = (Label) bankLabels.getChildren().get(1);

        assertEquals("Name: ", nameDescriptor.getText());
        assertEquals("", nameValue.getText());
        assertEquals("Bank: ", bankDescriptor.getText());
        assertEquals("150.0", bankValue.getText());
    }

    // SAD PATH :(
    @Test
    void testNegativeBank() throws Exception {
        launch(PlayerInfoTester.class);
        VBox maxPlayerInfo = lookup("#starting-pane #negative-bank").query();

        HBox nameLabels = (HBox) maxPlayerInfo.getChildren().get(0);
        HBox bankLabels = (HBox) maxPlayerInfo.getChildren().get(1);

        Label nameDescriptor = (Label) nameLabels.getChildren().get(0);
        Label nameValue = (Label) nameLabels.getChildren().get(1);

        Label bankDescriptor = (Label) bankLabels.getChildren().get(0);
        Label bankValue = (Label) bankLabels.getChildren().get(1);

        assertEquals("Name: ", nameDescriptor.getText());
        assertEquals("Duvall", nameValue.getText());
        assertEquals("Bank: ", bankDescriptor.getText());
        assertEquals("-100.0", bankValue.getText());
    }

    // HAPPY PATH :)
    @Test
    void testLanguageChange() throws Exception {
        launch(PlayerInfoTester.class);
        VBox maxPlayerInfo = lookup("#starting-pane #language-change").query();

        HBox nameLabels = (HBox) maxPlayerInfo.getChildren().get(0);
        HBox bankLabels = (HBox) maxPlayerInfo.getChildren().get(1);

        Label nameDescriptor = (Label) nameLabels.getChildren().get(0);
        Label nameValue = (Label) nameLabels.getChildren().get(1);

        Label bankDescriptor = (Label) bankLabels.getChildren().get(0);
        Label bankValue = (Label) bankLabels.getChildren().get(1);

        assertEquals("Nombre: ", nameDescriptor.getText());
        assertEquals("toSpanish", nameValue.getText());
        assertEquals("Tarjeta: ", bankDescriptor.getText());
        assertEquals("0.0", bankValue.getText());
    }
}
