package UI.LobbyView;

import UI.IconFactories.Icon;
import UI.IconFactories.IconSize;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import UI.Validation.AllFilesDisplay;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import xml.xmlvalidator.MasterValidator;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class GameStarter implements StylizedNode, LanguageResponder {

    private VBox myGameBox;
    private List<File> myFiles;
    private Label myGameLabel;
    private LanguageBundle myLanguageBundle;
    private final String myGameKey;

    private static final IconSize GAME_ICON_SIZE = IconSize.LARGE;
    private static final String PATH_TO_ICON = "iconImages/runnableGameIcons/";

    public GameStarter(String imageFile, String gameKey, List<File> files, Consumer<Exception> showException, LanguageBundle languageBundle, String filesIconBundle, String statusIconsBundle) {
        myGameBox = new VBox();
        Formatter.formatGameStarter(myGameBox);
        StylizedNode.setStyleID(myGameBox, this.getClass());

        myGameKey = gameKey;
        myFiles = files;
        myLanguageBundle = languageBundle;

        createGameIcon(imageFile);
        createGameLabel();
        setClickableGameBox(filesIconBundle, showException, statusIconsBundle);
    }

    private void createGameIcon(String imageFile) {
        String iconFilePath = formatIconFilePath(imageFile);
        Icon myIconButton = new Icon(iconFilePath, GAME_ICON_SIZE);
        myGameBox.getChildren().add(myIconButton.getView());
    }

    private void createGameLabel() {
        myGameLabel = new Label();
        myGameBox.getChildren().add(myGameLabel);
        updateLanguage();
    }

    private void setClickableGameBox(String filesIconBundle, Consumer<Exception> showException, String statusIconsBundle) {
        myGameBox.setOnMouseClicked(e -> {
            AllFilesDisplay display = new AllFilesDisplay(myLanguageBundle, filesIconBundle, statusIconsBundle);

            new MasterValidator(myFiles,
                    (file, status) -> display.updateStatus(file, status),
                    (initializer) -> display.enableGameButton(initializer),
                    showException);
        });
    }

    private String formatIconFilePath(String iconFileName) {
        return PATH_TO_ICON.concat(iconFileName);
    }

    @Override
    public VBox getView() {
        return myGameBox;
    }

    public List<File> getFiles() {
        return myFiles;
    }

    @Override
    public void updateLanguage() {
        String translatedGame = myLanguageBundle.getBundle().getString(myGameKey);
        myGameLabel.setText(translatedGame);
    }
}
