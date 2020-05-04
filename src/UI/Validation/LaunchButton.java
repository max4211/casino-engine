/**
 * First, this class was added to show capability in creating small, single-purposed classes. Before, the AllFilesDisplay had two functions: controlling the VBox display
 * and managing the Launch Button. Now, it is better split, creating a less God-like class. This shows how composition can be managed better than it was in the GameView class.
 * Second, this class shows the use of two lambda expressions: one that is a custom GameLauncher and one that is an EventHandler.
 * Third, this class shows how my previous design supported the extension well by means of the interfaces it implements. The LanguageResponder and StylizedNode forced most of the functionality
 * that the Node needed, aside from enableLaunchButton() method. Note that this Button could now be styled via CSS with an ID of LaunchButton.
 * Fourth, this code is readable by means of a small helper method and well-named variables.
 */
package UI.Validation;

import UI.Interfaces.GameLauncher;
import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * Class that handles the launching of a New Game by means of a Button click.
 * This is composed within the AllFilesDisplay and placed at the bottom of its VBox.
 * Enabled once all five files have passed through the Master Validator.
 * Implements StylizedNode and returns a Button on a getView() method call.
 * Implements LanguageResponder and updates the Button's text on a language change.
 */
public class LaunchButton implements LanguageResponder, StylizedNode {

    private Button myLaunchButton;

    private static final String LAUNCH_GAME_KEY = "LaunchGame";
    private static final boolean LAUNCH_BUTTON_DISABLE = true;
    private static final boolean LAUNCH_BUTTON_ENABLE = false;

    private LanguageBundle myLanguageBundle;

    /**
     * Constructor that initializes the Launch Button to be disabled.
     * Dependent on the Formatter class and StylizedNode interface, which is called to format and set the CSS ID of the Button.
     * @param sharedLanguageBundle is the LanguageBundle referenced by all JavaFX Nodes within the AllFilesDisplay ecosystem.
     */
    public LaunchButton(LanguageBundle sharedLanguageBundle) {
        myLaunchButton = new Button();
        StylizedNode.setStyleID(myLaunchButton, myLaunchButton.getClass());
        myLaunchButton.setDisable(LAUNCH_BUTTON_DISABLE);
        myLanguageBundle = sharedLanguageBundle;
    }

    /**
     * Method that implements the LanguageResponder interface.
     * Updates the text displayed in the LaunchButton by fetching a translation from the LanguageBundle given at construction.
     */
    @Override
    public void updateLanguage() {
        myLaunchButton.setText(getTranslation(LAUNCH_GAME_KEY));
    }

    /**
     * Method that implements the StylizedNode interface.
     * Returns the JavaFX Button that can launch a new game when it is enabled.
     * @return
     */
    @Override
    public Button getView() {
        return myLaunchButton;
    }

    /**
     * Enables the Launch Button which when clicked is suppoed to start a new game.
     * On click, the method provided via the GameLauncher functional interface is run, and the stage is closed.
     * @param gameLauncher provides a method via means of a lambda expression that is assumes to run a new game.
     * @param closedStage is the stage that AllFilesDisplay is assumed to be placed on, such that closing it ends the Validation process.
     */
    public void enableLaunchButton(GameLauncher gameLauncher, Stage closedStage) {
        myLaunchButton.setDisable(LAUNCH_BUTTON_ENABLE);
        myLaunchButton.setOnAction(e -> {
            closedStage.close();
            gameLauncher.startNewGame();
        });
    }

    // helper method to make LanguageBundle access more readable
    private String getTranslation(String key) {
        return myLanguageBundle.getBundle().getString(LAUNCH_GAME_KEY);
    }
}
