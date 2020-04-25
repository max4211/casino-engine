package UI.Validation;

import UI.Utilities.LanguageBundle;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class FileDisplayTester extends Application {

    // INTEGRATED WITH LANGUAGES, XMLTYPES, AND ICONS
    @Override
    public void start(Stage primaryStage) {
        Pane startingPane = new Pane();
        startingPane.setId("starting-pane");
        LanguageBundle myLanguageBundle = new LanguageBundle("English");

        FileDisplay standardDisplay = new FileDisplay("StandardStatuses", myLanguageBundle, XMLFileType.DECK, "whiteDeck.png", "blueEquals.png");
        standardDisplay.getView().setId("standard");

        FileDisplay languageChangeDisplay = new FileDisplay("StandardStatuses", myLanguageBundle, XMLFileType.DECK, "whiteDeck.png", "blueEquals.png");
        languageChangeDisplay.getView().setId("language-change");

        FileDisplay invalidDisplay = new FileDisplay("StandardStatuses", myLanguageBundle, XMLFileType.GAME, "whiteDeck.png", "blueEquals.png");
        invalidDisplay.getView().setId("invalid");
        invalidDisplay.updateStatusIcon(FileStatus.INVALID);

        startingPane.getChildren().addAll(standardDisplay.getView(), languageChangeDisplay.getView(), invalidDisplay.getView());
        myLanguageBundle.setLanguage("Spanish");
        languageChangeDisplay.updateLanguage();

        primaryStage.setScene(new Scene(startingPane));
        primaryStage.show();
    }
}
