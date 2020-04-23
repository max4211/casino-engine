package UI.Validation;

import UI.LanguageBundle;
import javafx.application.Application;
import javafx.stage.Stage;

public class AllFilesTester extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        LanguageBundle testBundle = new LanguageBundle("English");
        String statusBundle= "StandardStatuses";
        String iconBundle = "StandardXMLs";
        AllFilesDisplay allFileDisplay = new AllFilesDisplay(testBundle, statusBundle, iconBundle);

        allFileDisplay.updateStatus(XMLFile.DECK, FileStatus.VALID);
        allFileDisplay.updateStatus(XMLFile.GAME, FileStatus.INVALID);

        testBundle.setLanguage("Spanish");
        allFileDisplay.updateLanguage();

        allFileDisplay.enableGameButton(() -> {});
    }
}
