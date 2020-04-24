package UI.Validation;

import UI.Utilities.LanguageBundle;
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

        allFileDisplay.updateStatus(XMLFileType.DECK, FileStatus.VALID);
        allFileDisplay.updateStatus(XMLFileType.GAME, FileStatus.INVALID);

        testBundle.setLanguage("Spanish");
        allFileDisplay.updateLanguage();

        allFileDisplay.enableGameButton(() -> {});
    }
}
