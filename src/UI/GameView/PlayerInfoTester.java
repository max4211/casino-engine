package UI.GameView;

import UI.Utilities.LanguageBundle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Basic class used to tests the PlayerInfo objects. This is not relevant to the functionality or design of the project. This is only called in the test class for PlayerInfo.
 */
public class PlayerInfoTester extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        LanguageBundle myBundle = new LanguageBundle("English");
        Pane startingPane = new Pane();
        startingPane.setId("starting-pane");

        PlayerInfo Max = new PlayerInfo("Max", 200, myBundle);
        Max.getView().setId("double-input");

        PlayerInfo emptyName = new PlayerInfo("", 150, myBundle);
        emptyName.getView().setId("empty-name");

        PlayerInfo emptyBankRoll = new PlayerInfo("Duvall", -100, myBundle);
        emptyBankRoll.getView().setId("negative-bank");

        PlayerInfo languageChange = new PlayerInfo("toSpanish", 0, myBundle);
        languageChange.getView().setId("language-change");

        startingPane.getChildren().addAll(Max.getView(), emptyName.getView(), emptyBankRoll.getView(), languageChange.getView());

        myBundle.setLanguage("Spanish");
        languageChange.updateLanguage();

        primaryStage.setScene(new Scene(startingPane));
        primaryStage.show();
    }
}
