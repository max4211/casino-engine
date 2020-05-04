/**
 * This class was included in my Code Masterpiece since it shows an example of the Composite Design Pattern adhering to an Interface via Contract By Design.
 * In this way, it shows how a more successful version of the Design Pattern that overwhelmed GameView, since a FileDisplay is a Composite and a ReadyButton is a LeafNode. Note that FileDisplay did not fit within the 300 line requirement to include, but it is still implemented successfully.
 * Further, this shows other concepts learned in class. First, it uses the GameCaller Functional Interface to launch the new game without needing to know about the Controller (through use of lambdas).
 * Second, it shows overloaded constructors. This was something that my design could have benefited from in GameView as well, so this shows that capability.
 * There is a bug where the AllFilesDisplay was covered by the MultipleXMLChooser when a file was marked invalid, so the backend was supposed to call this second constructor if need be (but I do not believe Max got around to it).
 * Third, it shows the use of multiple custom interfaces, which is not something I have demonstrated a capability with in previous masterpieces.
 * Fourth, it shows successful composition, wherein the AllFilesDisplay serves as a mini-controller. Launch Button functionality was offloaded to its own class.
 * This demonstrates a class with a single-purpose, instead of a God Class (once again paralleling how improvements could be made to GameView).
 * Last, the code is generally more readable by means of helper methods and better-named variables.
 */
package UI.Validation;

import UI.Interfaces.GameLauncher;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Class that controls the UI during the Validation process, managing a VBox with five FileDisplays and a LaunchButton.
 * Class manages its own stage which displays the VBox Node, and this is closed when the Game is launched via the LaunchButton.
 * Implements AllFilesDisplayInterface due to Contract by Design.
 * Implements the LanguageResponder Interface, prompting all of the individual FileDisplays and LaunchButton to update when called on a language change.
 * @author Eric Doppelt
 */
public class AllFilesDisplay implements AllFilesDisplayInterface, LanguageResponder {

    private VBox myDisplayedBox;
    private Map<XMLFileType, FileDisplay> myFileDisplayMap;
    private LaunchButton myLaunchButton;
    private Stage myStage;

    private static final String PATH_TO_ICON_BUNDLE = "iconBundles/fileTypes/";
    private static final String EQUAL_KEY = "Equals";
    private static final double ORIGIN = 0;

    /**
     * Full constructor that creates the Display with all individual file statuses set to Empty and the Launch Button disabled.
     * Calls other overloaded constructor which does not take in an X coordinate and sets the specified coordinate afterwards.
     * Dependent on the Formatter class and StylizedNode interface, which is called to format and set the CSS ID of the DisplayedBox.
     * @param sharedLanguageBundle is the LanguageBundle used by all composite and leaf nodes for displayed UI text.
     * @param fileIconBundleName is the name of the iconBundle for the images of the XML file types.
     * @param statusIconBundleName is the name of the iconBundle for the images the XML file statuses.
     * @param initialXCoordinate is the X coordinate of the displayed stage upon construction.
     */
    public AllFilesDisplay(LanguageBundle sharedLanguageBundle, String fileIconBundleName, String statusIconBundleName, double initialXCoordinate) {
        this(sharedLanguageBundle, fileIconBundleName, statusIconBundleName);
        myStage.setX(initialXCoordinate);
    }

    /**
     * Constructor that does not specify an X coordinate and renders the Display in the center of the screen.
     * Dependent on the Formatter class and StylizedNode interface, which is called to format and set the CSS ID of the DisplayedBox.
     * @param sharedLanguageBundle is the LanguageBundle used by all composite and leaf nodes for displayed UI text.
     * @param fileIconBundleName is the name of the iconBundle for the images of the XML file types.
     * @param statusIconBundleName is the name of the iconBundle for the images the XML file statuses.
     */
    public AllFilesDisplay(LanguageBundle sharedLanguageBundle, String fileIconBundleName, String statusIconBundleName) {
        initializeDisplayedBox();
        createFileDisplays(sharedLanguageBundle, fileIconBundleName, statusIconBundleName);
        initializeLaunchButton(sharedLanguageBundle);
        updateLanguage();
        renderDisplay();
    }

    /**
     * Updates the language in the AllFilesDisplay UI by telling each Individual FileDisplay to update while also updating the text in the Launch Button.
     * Implements the LanguageResponder interface.
     */
    @Override
    public void updateLanguage() {
        myLaunchButton.updateLanguage();
        for (XMLFileType tempFileType : XMLFileType.values()) {
            myFileDisplayMap.get(tempFileType).updateLanguage();
        }
    }

    /**
     * Updates the status of a single file type by calling the method of the same name on the specified File Display.
     * @param fileType is the type of file that has changed (such as Deck or Hands).
     * @param newFileStatus is the new status of the file (such as Valid or Invalid).
     */
    @Override
    public void updateStatus(XMLFileType fileType, FileStatus newFileStatus) {
        myFileDisplayMap.get(fileType).updateStatusIcon(newFileStatus);
    }

    /**
     * Enables the game button, signifying that all five files as valid.
     * On a button click, the method provided for the GameLauncher is run, which is assumed to start a new game.
     * @param gameLauncher is a Functional Interface that takes in the method needed to create a new game.
     */
    @Override
    public void enableLaunchButton(GameLauncher gameLauncher) {
        myLaunchButton.enableLaunchButton(gameLauncher, myStage);
    }

    /**
     * Method that allows a user to set the X coordinate of the AllFilesDisplay. This is needed if the MultipleXMLChooser is also rendered, since it blocks the center of the screen.
     * @param newX is the new X coordinate to set the display to, which must be in the screen. Otherwise, it ignores the command.
     */
    @Override
    public void setX(double newX) {
        if (newX > ORIGIN && newX < getScreenWidth()) {
            myStage.setX(newX);
        }
    }

    // creates the VBox, formats it, and sets its CSS ID by means of static methods in the Formatter class and StylizedNode interface.
    private void initializeDisplayedBox() {
        myDisplayedBox = new VBox();
        StylizedNode.setStyleID(myDisplayedBox, this.getClass());
        Formatter.formatAllFilesBox(myDisplayedBox);
    }

    // initializes and populates the Map which connects an XMLFileType to its FileDisplay
    private void createFileDisplays(LanguageBundle sharedLanguageBundle, String fileIconBundleName, String statusIconBundleName) {
        String pathToFileIcons = formatIconBundlePath(fileIconBundleName);
        ResourceBundle tempFileIconsBundle = ResourceBundle.getBundle(pathToFileIcons);
        String nameOfEqualIcon = tempFileIconsBundle.getString(EQUAL_KEY);

        myFileDisplayMap = new HashMap<>();
        for (XMLFileType tempFileType : XMLFileType.values()) {
            String nameOfFileIcon = tempFileIconsBundle.getString(tempFileType.toString());
            FileDisplay addedFileDisplay = new FileDisplay(statusIconBundleName, sharedLanguageBundle, tempFileType, nameOfFileIcon, nameOfEqualIcon);
            myFileDisplayMap.put(tempFileType, addedFileDisplay);
            myDisplayedBox.getChildren().add(addedFileDisplay.getView());
        }
    }

    // initializes the launch button and adds to to the display
    private void initializeLaunchButton(LanguageBundle sharedLanguageBundle) {
        myLaunchButton = new LaunchButton(sharedLanguageBundle);
        myDisplayedBox.getChildren().add(myLaunchButton.getView());
    }

    // creates a Stage, adds the Display to it, and shows itself
    private void renderDisplay() {
        myStage = new Stage();
        Formatter.formatAllFilesStage(myStage);
        Scene displayedScene = new Scene(myDisplayedBox);
        myStage.setScene(displayedScene);
        myStage.show();
    }

    // translates the simple name of an icon bundle to its relative path
    private String formatIconBundlePath(String iconName) {
        return PATH_TO_ICON_BUNDLE.concat(iconName);
    }

    // returns the width of the users screen
    private double getScreenWidth() {
        return Screen.getPrimary().getBounds().getMaxX();
    }
}
