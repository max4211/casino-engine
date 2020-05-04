package UI.LobbyView;

import UI.Icons.Icon;
import UI.Icons.IconSize;
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

/**
 * Class that creates a special type of Icon which represents one game in the LobbyView.
 * Using composition, the class creates a ClickableIcon with text that can be updated on a Language Change.
 * On a click, files held in the GameStarter are sent to Validation.
 * Implements StylizedNode, returning a VBox containing the clickable icon and the text describing it.
 * Implements LanguageResponder, updating the names of the icons on a language change.
 * @author Eric Doppelt and Max Smith. Note that just exception handling and file transport to Validation was done by Max Smith.
 */
public class GameStarter implements StylizedNode, LanguageResponder {

    private VBox myGameBox;
    private List<File> myFiles;
    private Label myGameLabel;
    private LanguageBundle myLanguageBundle;
    private final String myGameKey;

    private static final String BLACKJACK_KEY = "Blackjack";
    private static final String TESTING_BLACKJACK_STARTER = "blackjack-starter";
    private static final IconSize GAME_ICON_SIZE = IconSize.LARGE;
    private static final String PATH_TO_ICON = "iconImages/runnableGameIcons/";

    /**
     * Basic constructor that takes in all information needed to hold XML files to run a game and launch Validation on a click.
     * Represented in the UI by means of an icon and the name of the game run by the files held.
     * @param imageFile is the image to associate the game with, displayed in the LobbyView.
     * @param gameKey is the key of the name of the game used in language ResourceBundles, which supports translations to new languages.
     * @param files is a List of Files that represent between 1 to 5 XML files that are given to the Validator on a click.
     * @param showException is a Consumer that takes in an exception and displays it via an ExceptionDisplayer.
     * @param languageBundle is a LanguageBundle used to update displayed text on a language change.
     * @param filesIconBundle is the ResourceBundle that holds the icons represented in Validation. This is passed through to the Validation module.
     * @param statusIconsBundle is the ResourceBundle that holds the icons for statuses in Validation. This is passed through to the Validation module.
     */
    public GameStarter(String imageFile, String gameKey, List<File> files, Consumer<Exception> showException, LanguageBundle languageBundle, String filesIconBundle, String statusIconsBundle) {
        myGameBox = new VBox();
        Formatter.formatGameStarter(myGameBox);
        StylizedNode.setStyleID(myGameBox, this.getClass());
        myGameKey = gameKey;

        // Used FOR PlayerInfo Testing
        if (gameKey.equals(BLACKJACK_KEY)) {
            myGameBox.setId(TESTING_BLACKJACK_STARTER);
        }

        myFiles = files;
        myLanguageBundle = languageBundle;

        createGameIcon(imageFile);
        createGameLabel();
        setClickableGameBox(filesIconBundle, showException, statusIconsBundle);
    }

    /**
     * Returns a VBox that contains the Icon of the game and its name.
     * @return VBox containing all the information relevant to the game. On a click, the files held in the GameStarter are sent to Validation.
     */
    @Override
    public VBox getView() {
        return myGameBox;
    }

    /**
     * Updates the language of the game name that is displayed in the UI via a new ResourceBundle.
     * Assumes new language has the key associated with the game name.
     */
    @Override
    public void updateLanguage() {
        String translatedGame = myLanguageBundle.getBundle().getString(myGameKey);
        myGameLabel.setText(translatedGame);
    }

    /**
     * Accesses all files held underneath the hood of the GameStarter.
     * This is no longer called, since files are passed to Validation instead.
     * Note that this was used in the Sprint 2 submission.
     * @return the list of the files held by the game starter.
     */
    @Deprecated
    public List<File> getFiles() {
        return myFiles;
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
                    (initializer) -> display.enableLaunchButton(initializer),
                    (newX) -> display.setX(newX),
                    showException);
        });
    }

    private String formatIconFilePath(String iconFileName) {
        return PATH_TO_ICON.concat(iconFileName);
    }
}
