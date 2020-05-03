package UI.Selectors;

import UI.Interfaces.StylizedNode;
import UI.Utilities.LanguageBundle;
import UI.Utilities.Formatter;
import Utility.HashNoise;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

/**
 * Class that represents a button which can "pause" a game until the user inputs that he/she is ready to continue.
 * Button has ONE KNOWN ASSUMPTION/BUG, which is that the user is one playing ONE game at a time.
 * If multiple ReadyButtons are rendered at once, the program will not crash, but the more outdated game will no longer respond to input.
 * Class uses a LanguageBundle but does not implement LanguageResponder interface, since the dialogues fetch text when they are called, keeping them up-to-date.
 * @author Eric Doppelt
 */
public class ReadyButton {

    private static final String ACTION_TEXT_KEY = "ActionReadyButton";
    private static final String WAGER_TEXT_KEY = "WagerReadyButton";
    private static final String NEW_GAME_TEXT_KEY = "NewGameReadyButton";

    private Button myButton;
    private LanguageBundle myLanguageBundle;

    /**
     * Basic constructor that initializes the ReadyButton but does not implement any functionality to it for a click.
     * Sets the LanguageBundle to be referenced in future pauseUntilReady() calls.
     * Calls a Formatter to set the formatting and CSS ID of the Button to ReadyButton
     * @param languageBundle
     */
    public ReadyButton(LanguageBundle languageBundle) {
        myLanguageBundle = languageBundle;
        myButton = new Button();
        Formatter.formatSelectorButton(myButton);
        StylizedNode.setStyleID(myButton, this.getClass());
    }

    /**
     * Method that encodes the majority of the functionality for the Ready Button.
     * This method latches the button stored as an instance variable onto a pane and pauses the program until it is clicked.
     * This is done using a Platform.enterNestedLoop call, as found here: https://stackoverflow.com/questions/30765018/secondaryloop-in-javafx-like-swing
     * HashNoise is used to create individual keys for the Nested Loops
     * @param parent is the pane to add the button to it. It must know of its parent, so it can remove itself when pressed.
     * @param type is the type of action that the UI is waiting on user input for. This is either Action, Wager or NewGame input, and the only change is the text displayed in the button.
     */
    public void pauseUntilReady(Pane parent, SelectorType type) {
        int nestedLoopKey = HashNoise.addNoise(this);
        String displayedKey = getDisplayKey(type);
        myButton.setText(myLanguageBundle.getBundle().getString(displayedKey));
        myButton.setOnAction(e -> {
            parent.getChildren().remove(myButton);
            Platform.exitNestedEventLoop(nestedLoopKey, null);
        });
        parent.getChildren().add(myButton);
        Platform.enterNestedEventLoop(nestedLoopKey);
    }

    private String getDisplayKey(SelectorType type) {
        if (type.equals(SelectorType.ACTION)) return ACTION_TEXT_KEY;
        else if (type.equals(SelectorType.WAGER)) return WAGER_TEXT_KEY;
        else if (type.equals(SelectorType.NEWGAME)) return NEW_GAME_TEXT_KEY;
        return null;
    }
}
