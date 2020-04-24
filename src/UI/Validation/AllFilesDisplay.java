package UI.Validation;

import UI.Interfaces.GameCaller;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import UI.Utilities.ScreenPosition;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class AllFilesDisplay implements AllFilesDisplayInterface, LanguageResponder {

    private VBox myVBox;
    private Stage myStage;
    private Button myLaunchButton;
    private Map<XMLFileType, FileDisplay> myFileDisplays;

    private static final String PATH_TO_ICON_BUNDLE = "iconBundles/fileTypes/";

    private LanguageBundle myLanguageBundle;
    private static final String LAUNCH_GAME_KEY = "LaunchGame";
    private static final String EQUAL_KEY = "Equals";

    private static final boolean LAUNCH_BUTTON_DISABLE = true;
    private static final boolean LAUNCH_BUTTON_ENABLE = false;

    private static final int HEIGHT = 375;
    private static final int WIDTH = 175;
    private static final double HALF_WIDTH = WIDTH / 2;


    public AllFilesDisplay(LanguageBundle languageBundle, String fileIconBundleName, String statusIconBundleName) {
        myVBox = new VBox();
        StylizedNode.setStyleID(myVBox, this.getClass());
        Formatter.formatAllFilesDisplay(myVBox);
        myLanguageBundle = languageBundle;

        createFileIcons(fileIconBundleName, statusIconBundleName);

        myLaunchButton = new Button();
        myLaunchButton.setDisable(LAUNCH_BUTTON_DISABLE);
        updateLanguage();
        myVBox.getChildren().add(myLaunchButton);

        myStage = new Stage();
        Scene filesScene = new Scene(myVBox, WIDTH, HEIGHT);
        myStage.setScene(filesScene);
        myStage.setX(ScreenPosition.LEFT.getX() - HALF_WIDTH);
        myStage.setY(ScreenPosition.LEFT.getY());
        myStage.show();
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

    private void createFileIcons(String fileIconBundle, String statusIconBundle) {
        String iconBundlePath = formatIconBundlePath(fileIconBundle);
        ResourceBundle myFileIconBundle = ResourceBundle.getBundle(iconBundlePath);
        String equalImageName = myFileIconBundle.getString(EQUAL_KEY);

        myFileDisplays = new HashMap<>();
        for (XMLFileType fileType : XMLFileType.values()) {
            String fileImageName = myFileIconBundle.getString(fileType.toString());
            FileDisplay addedFileDisplay = new FileDisplay(statusIconBundle,
                    myLanguageBundle,
                    fileType,
                    fileImageName,
                    equalImageName);
            myFileDisplays.put(fileType, addedFileDisplay);
            myVBox.getChildren().add(addedFileDisplay.getView());
        }
    }
}
