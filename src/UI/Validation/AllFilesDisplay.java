package UI.Validation;

import UI.Interfaces.GameCaller;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import UI.Utilities.ScreenPosition;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

// TODO: add path to bundles
public class AllFilesDisplay implements AllFilesDisplayInterface, LanguageResponder {

    private VBox myVBox;
    private Button myLaunchButton;
    private Map<XMLFile, FileDisplay> myFileDisplays;

    private static final int VBOX_SPACING = 8;

    private Stage myStage;

    private LanguageBundle myLanguageBundle;
    private static final String LAUNCH_GAME_KEY = "LaunchGame";
    private static final boolean LAUNCH_BUTTON_DISABLE = true;
    private static final boolean LAUNCH_BUTTON_ENABLE = false;

    private static final String EQUAL_KEY = "EQUALS";

    private static final int HEIGHT = 375;
    private static final int WIDTH = 175;
    private static final double HALF_WIDTH = WIDTH / 2;

    private static final String PATH_TO_ICON_BUNDLE = "iconBundles/fileTypes/";



    public AllFilesDisplay(LanguageBundle languageBundle, String statusIconBundle, String fileIconProperties) {
        myVBox = new VBox();
        StylizedNode.setStyleID(myVBox, this.getClass());
        myVBox.setAlignment(Pos.CENTER);
        myVBox.setSpacing(VBOX_SPACING);
        myLanguageBundle = languageBundle;
        ResourceBundle myFileIconBundle = ResourceBundle.getBundle(PATH_TO_ICON_BUNDLE.concat(fileIconProperties));
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

        myStage = new Stage();
        Scene filesScene = new Scene(myVBox, WIDTH, HEIGHT);
        myStage.setScene(filesScene);
        myStage.setX(ScreenPosition.LEFT.getX() - HALF_WIDTH);
        myStage.setY(ScreenPosition.LEFT.getY());
        myStage.show();
    }

    @Override
    public void updateStatus(XMLFile type, FileStatus newStatus) {
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
        for (XMLFile tempFileType : XMLFile.values()) myFileDisplays.get(tempFileType).updateLanguage();
        myLaunchButton.setText(myLanguageBundle.getBundle().getString(LAUNCH_GAME_KEY));
    }
}
