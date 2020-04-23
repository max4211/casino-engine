package UI.Validation;

import UI.Interfaces.GameCaller;
import UI.Interfaces.LanguageUpdater;
import UI.LanguageBundle;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

// TODO: add path to bundles
public class AllFilesDisplay implements AllFilesDisplayInterface, LanguageUpdater {

    private VBox myVBox;
    private Button myLaunchButton;
    private Map<XMLFile, FileDisplay> myFileDisplays;

    private LanguageBundle myLanguageBundle;
    private static final String LAUNCH_GAME_KEY = "LaunchGame";
    private static final boolean LAUNCH_BUTTON_DISABLE = true;
    private static final boolean LAUNCH_BUTTON_ENABLE = false;


    public AllFilesDisplay(LanguageBundle languageBundle, String statusIconBundle, String FileIconProperties) {
        myVBox = new VBox();
        myLanguageBundle = languageBundle;
        ResourceBundle myFileIconBundle = ResourceBundle.getBundle(FileIconProperties);
        myFileDisplays = new HashMap<>();
        for (XMLFile fileType : XMLFile.values()) {
            String addedFileImageName = myFileIconBundle.getString(fileType.name());
            FileDisplay addedFileDisplay = new FileDisplay(statusIconBundle, languageBundle, fileType, addedFileImageName);
            myVBox.getChildren().add(addedFileDisplay.getView());
            myFileDisplays.put(fileType, addedFileDisplay);
        }
        myLaunchButton = new Button();
        myLaunchButton.setDisable(LAUNCH_BUTTON_DISABLE);
        updateLanguage();
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
        myLaunchButton.setText(myLanguageBundle.getBundle().getString(LAUNCH_GAME_KEY));
    }
}
