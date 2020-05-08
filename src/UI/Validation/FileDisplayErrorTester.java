package UI.Validation;

import UI.Utilities.LanguageBundle;
import javafx.application.Application;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * Basic tester used to test the FileDisplayError. This is NOT relevant to the implementation, design, or functionality of the application itself (only testing).
 * @author Eric Doppelt
 */
public class FileDisplayErrorTester extends Application {


    @Override
    public void start(Stage primaryStage) throws Exception {
        Pane startingPane = new Pane();
        startingPane.setId("starting-pane");
        LanguageBundle myLanguageBundle = new LanguageBundle("English");

        FileDisplay standardDisplay = new FileDisplay("X", myLanguageBundle, XMLFileType.DECK, "whiteDeck.png", "blueEquals.png");
    }
}
