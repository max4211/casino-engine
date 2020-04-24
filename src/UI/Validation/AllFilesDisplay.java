package UI.Validation;

import UI.Interfaces.GameCaller;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AllFilesDisplay implements AllFilesDisplayInterface, LanguageResponder {

    private VBox myFilesBox;
    private Stage myStage;
    private Map<XMLFileType, FileDisplay> myFileDisplays;

    private static final String PATH_TO_ICON_BUNDLE = "iconBundles/fileTypes/";
    private static final String EQUAL_KEY = "Equals";

    private LanguageBundle myLanguageBundle;

    private Button myLaunchButton;
    private static final String LAUNCH_GAME_KEY = "LaunchGame";
    private static final boolean LAUNCH_BUTTON_DISABLE = true;
    private static final boolean LAUNCH_BUTTON_ENABLE = false;

    public AllFilesDisplay(LanguageBundle languageBundle, String fileIconBundleName, String statusIconBundleName) {
        myFilesBox = new VBox();
        StylizedNode.setStyleID(myFilesBox, this.getClass());
        Formatter.formatAllFilesBox(myFilesBox);
        myLanguageBundle = languageBundle;

        createFileIcons(fileIconBundleName, statusIconBundleName);
        createLaunchButton();
        updateLanguage();
        renderDisplay();
    }

    @Override
    public void updateStatus(XMLFileType type, FileStatus newStatus) {
        myFileDisplays.get(type).updateStatusIcon(newStatus);
    }

    @Override
    public void enableGameButton(GameCaller initializer) {
        myLaunchButton.setDisable(LAUNCH_BUTTON_ENABLE);
        myLaunchButton.setOnAction(e -> {
            myStage.close();
            initializer.startNewGame();
        });
    }

    @Override
    public void updateLanguage() {
        for (XMLFileType tempFileType : XMLFileType.values()) myFileDisplays.get(tempFileType).updateLanguage();
        myLaunchButton.setText(myLanguageBundle.getBundle().getString(LAUNCH_GAME_KEY));
    }

    private String formatIconBundlePath(String iconName) {
        return PATH_TO_ICON_BUNDLE.concat(iconName);
    }

    private void createFileIcons(String fileIconBundleName, String statusIconBundleName) {
        String iconBundlePath = formatIconBundlePath(fileIconBundleName);
        ResourceBundle myFileIconBundle = ResourceBundle.getBundle(iconBundlePath);
        String equalImageName = myFileIconBundle.getString(EQUAL_KEY);

        myFileDisplays = new HashMap<>();
        for (XMLFileType fileType : XMLFileType.values()) {
            String fileImageName = myFileIconBundle.getString(fileType.toString());
            FileDisplay addedFileDisplay = new FileDisplay(statusIconBundleName,
                    myLanguageBundle,
                    fileType,
                    fileImageName,
                    equalImageName);
            myFileDisplays.put(fileType, addedFileDisplay);
            myFilesBox.getChildren().add(addedFileDisplay.getView());
        }
    }

    private void createLaunchButton() {
        myLaunchButton = new Button();
        myLaunchButton.setDisable(LAUNCH_BUTTON_DISABLE);
        myFilesBox.getChildren().add(myLaunchButton);
    }

    private void renderDisplay() {
        myStage = new Stage();
        Formatter.formatAllFilesStage(myStage);
        Scene filesScene = new Scene(myFilesBox);
        myStage.setScene(filesScene);
        myStage.show();
    }
}
