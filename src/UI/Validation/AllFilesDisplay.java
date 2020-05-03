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

/**
 * Class that is used to represent all five files that are checked in the Validation process via five individual FileDisplays.
 * FileDisplays are held in a Map which matches the type of file to its display.
 * The individual Files are joined with a button that launches the GameView when enabled by the backend via means of a public method call.
 * Class creates its stage and places the AllFilesDisplay on it.
 * Implements AllFilesDisplayInterface due to the Contract by Design process.
 * Implements the LanguageResponderInterface, prompting all of the individual FileDisplays and Launch Button to update when called.
 * @author Eric Doppelt
 */
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

    private static final double ORIGIN = 0;

    /**
     * Full constructor that creates the File Display with all individual statuses set to Empty.
     * Icons are specified through iconBundles for fileTypes and statusTypes.
     * ScreenPosition is given as an enumerated type and the X value is used.
     * Calls the constructor that does not take in ScreenPosition, which sets the formatting and styling of the Node with a CSS ID of AllFilesDisplay. This is done via a Formatter static call.
     * @param languageBundle is the LanguageBundle used in future language updates.
     * @param fileIconBundleName is the iconBundle for the file icons (such as for the Deck) which is passed to the Individual Files.
     * @param statusIconBundleName is the iconBundle for the status icons (such as Valid) which is passed to the Individual Fies.
     * @param screenPosition is the position to display the ALlFilesDisplay in (either Far Left, Left, Middle, or Right).
     */
    public AllFilesDisplay(LanguageBundle languageBundle, String fileIconBundleName, String statusIconBundleName, ScreenPosition screenPosition) {
        this(languageBundle, fileIconBundleName, statusIconBundleName);
        myStage.setX(screenPosition.getX());
    }

    /**
     * Constructor that does not specify a ScreenPosition to use but takes in all other needed information
     * AllFilesDisplay is rendered in the middle of the screen as such.
     * Icons are specified through iconBundles for fileTypes and statusTypes.
     * Sets the formatting and styling of the Node with a CSS ID of AllFilesDisplay. This is done via a Formatter static call.
     * @param languageBundle is the LanguageBundle used in future language updates.
     * @param fileIconBundleName is the iconBundle for the file icons (such as for the Deck) which is passed to the Individual Files.
     * @param statusIconBundleName is the iconBundle for the status icons (such as Valid) which is passed to the Individual Fies.
     */
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

    /**
     * Updates the status of a single file type by calling the method of the same name on an individual File Display.
     * The FileDisplay holds the iconBundles and can extract the new status image from them.
     * @param type is the type of file that has changed (such as Deck or Hands).
     * @param newStatus is the new status of the file (such as Valid or Invalid).
     */
    @Override
    public void updateStatus(XMLFileType type, FileStatus newStatus) {
        myFileDisplays.get(type).updateStatusIcon(newStatus);
    }

    /**
     * Enables the game button once the Controller tells it to after marking all five files as valid.
     * On a button click, the method given via a lambda is run, which is assumed to start the game (as it is the responsibility of the Controller to do this).
     * @param initializer is a GameCaller Functional Interface which allows the AllFilesDisplay to alert the Controller to run the GameView.
     */
    @Override
    public void enableGameButton(GameCaller initializer) {
        myLaunchButton.setDisable(LAUNCH_BUTTON_ENABLE);
        myLaunchButton.setOnAction(e -> {
            myStage.close();
            initializer.startNewGame();
        });
    }

    /**
     * Updates the language in the AllFilesDisplay UI by telling each Individual FileDisplay to update while also updating the text in the launch button.
     * Implements the LanguageResponder interface.
     */
    @Override
    public void updateLanguage() {
        for (XMLFileType tempFileType : XMLFileType.values()) myFileDisplays.get(tempFileType).updateLanguage();
        myLaunchButton.setText(myLanguageBundle.getBundle().getString(LAUNCH_GAME_KEY));
    }

    /**
     * Method that was added towards the end of project, only to to allow the backend to move the AllFilesDisplay to the left if it is blocked by the FileChooser.
     * Called from the Master Validator.
     * Some error checking to ensure that the x value is positive.
     * @param newX is the new X coordinate to set the display at.
     */
    public void setX(double newX) {
        if (newX > ORIGIN) {
            myStage.setX(newX);
        }
    }

    private void initializeFilesBox() {
        myFilesBox = new VBox();
        StylizedNode.setStyleID(myFilesBox, this.getClass());
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
        StylizedNode.setStyleID(myLaunchButton, myLaunchButton.getClass());
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
