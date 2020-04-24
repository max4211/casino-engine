package UI.LobbyView;

import UI.IconFactories.Icon;
import UI.IconFactories.IconSize;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import UI.Validation.AllFilesDisplay;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import xml.xmlvalidator.MasterValidator;

import java.io.File;
import java.util.List;
import java.util.function.Consumer;

public class GameStarter implements StylizedNode, LanguageResponder {

    private VBox myGameStarter;
    private List<File> myFiles;
    private Label myGameLabel;
    private LanguageBundle myLanguageBundle;
    private final String myGameKey;

    private static final IconSize GAME_ICON_SIZE = IconSize.LARGE;
    private static final String PATH_TO_ICON = "iconImages/runnableGameIcons/";

    public GameStarter(String imageFile, String gameKey, List<File> files, Consumer<Exception> showException, LanguageBundle languageBundle,
                       String filesDisplayIcon, String filesDisplayStatus) {
        myGameStarter = new VBox();
        Formatter.formatGameStarter(myGameStarter);
        StylizedNode.setStyleID(myGameStarter, this.getClass());

        String iconFilePath = formatIconFilePath(imageFile);
        Icon myIconButton = new Icon(iconFilePath, GAME_ICON_SIZE);

        myFiles = files;
        myGameStarter.setOnMouseClicked(e -> {
            AllFilesDisplay display = new AllFilesDisplay(languageBundle, filesDisplayStatus, filesDisplayIcon);
            new MasterValidator(myFiles,
                    (file, status) -> display.updateStatus(file, status),
                    (initializer) -> display.enableGameButton(initializer),
                    showException);
        });

        myLanguageBundle = languageBundle;
        myGameKey = gameKey;
        myGameLabel = new Label();
        myGameLabel.setAlignment(Pos.CENTER);
        updateLanguage();
        myGameStarter.getChildren().addAll(myIconButton.getView(), myGameLabel);
    }

    private String formatIconFilePath(String iconFileName) {
        return PATH_TO_ICON.concat(iconFileName);
    }

    @Override
    public VBox getView() {
        return myGameStarter;
    }

    public List<File> getFiles() {
        return myFiles;
    }

    @Override
    public void updateLanguage() {
        myGameLabel.setText(myLanguageBundle.getBundle().getString(myGameKey));
    }
}
