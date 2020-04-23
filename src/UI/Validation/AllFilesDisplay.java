package UI.Validation;

import UI.Interfaces.GameCaller;
import UI.Interfaces.LanguageUpdater;
import UI.LanguageBundle;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

// TODO: add path to bundles
public class AllFilesDisplay implements AllFilesDisplayInterface, LanguageUpdater {

    private VBox myVBox;
    private Button myLaunchButton;
    private Map<XMLFile, FileDisplay> myFileDisplays;

    private static final int VBOX_SPACING = 8;

    private LanguageBundle myLanguageBundle;
    private static final String LAUNCH_GAME_KEY = "LaunchGame";
    private static final boolean LAUNCH_BUTTON_DISABLE = true;
    private static final boolean LAUNCH_BUTTON_ENABLE = false;

    private static final String EQUAL_KEY = "EQUALS";

    private static final int HEIGHT = 375;
    private static final int WIDTH = 175;
    private static final int STARTING_X = 300;
    private static final int STARTING_Y = 500;

    public AllFilesDisplay(LanguageBundle languageBundle, String statusIconBundle, String FileIconProperties) {
        myVBox = new VBox();
        myVBox.setAlignment(Pos.CENTER);
        myVBox.setSpacing(VBOX_SPACING);
        myLanguageBundle = languageBundle;
        ResourceBundle myFileIconBundle = ResourceBundle.getBundle(FileIconProperties);
        String equalIcon = myFileIconBundle.getString(EQUAL_KEY);
        myFileDisplays = new HashMap<>();
        for (XMLFile fileType : XMLFile.values()) {
            String addedFileImageName = myFileIconBundle.getString(fileType.name());
            FileDisplay addedFileDisplay = new FileDisplay(statusIconBundle, languageBundle, fileType, addedFileImageName, equalIcon);
            myFileDisplays.put(fileType, addedFileDisplay);
            myVBox.getChildren().add(addedFileDisplay.getView());
        }

        myLaunchButton = new Button();
        myLaunchButton.setDisable(LAUNCH_BUTTON_DISABLE);
        updateLanguage();
        myVBox.getChildren().add(myLaunchButton);

        Stage newStage = new Stage();
        Scene filesScene = new Scene(myVBox, WIDTH, HEIGHT);
        newStage.setScene(filesScene);
        newStage.setX(STARTING_X);
        newStage.setY(STARTING_Y);
        newStage.show();
    }

    @Override
    public void updateStatus(XMLFile type, FileStatus newStatus) {
        myFileDisplays.get(type).updateStatusIcon(newStatus);
    }

    @Override
    public void enableGameButton(GameCaller initializer) {
        myLaunchButton.setDisable(LAUNCH_BUTTON_ENABLE);
        myLaunchButton.setOnAction(e -> {
            initializer.startNewGame();
        });
    }

    @Override
    public void updateLanguage() {
        for (XMLFile tempFileType : XMLFile.values()) myFileDisplays.get(tempFileType).updateLanguage();
        myLaunchButton.setText(myLanguageBundle.getBundle().getString(LAUNCH_GAME_KEY));
    }
}
