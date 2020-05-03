package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Interfaces.TaggableNode;
import UI.Utilities.LanguageBundle;
import UI.Selectors.ReadyButton;
import UI.Selectors.SelectorType;
import UI.Utilities.Formatter;
import javafx.scene.layout.HBox;

/**
 * Class used to display the player who is currently "playing the game", since the UI operates under the assumption that only one player views the screen at a time.
 * This combines a PlayerView with a ReadyButton object, allowing the user to give and prompt when they are ready based on information from their PlayerView.
 * Implements the StylizedNode interface, providing access to an HBox with a CSS ID of MainPlayer.
 * Implements the TaggableNode interface, with the implemented method hasSameID() returning true if the PlayerView object has the specified ID.
 */
public class MainPlayer implements StylizedNode, TaggableNode, LanguageResponder {

    private PlayerView myPlayer;
    private HBox myMainPlayerView;
    private ReadyButton myReadyButton;

    /**
     * Constructor that simply initializes and formats the HBox and provides a LanguageBundle to the ReadyButton stored as an instance variable.
     * @param languageBundle is the LanguageBundle that is given to the ReadyButton. This is not stored in the MainPlayer itself but only passes through it.
     */
    public MainPlayer(LanguageBundle languageBundle) {
        myMainPlayerView = new HBox();
        Formatter.formatMainPlayer(myMainPlayerView);
        myReadyButton = new ReadyButton(languageBundle);
    }

    /**
     * This returns the HBox myMainPlayerView, which wraps a PlayerView with a ReadyButton.
     * @return the HBox that represents the Main Player of the game, and his button to prompt when he is ready.
     */
    @Override
    public HBox getView() {
        return myMainPlayerView;
    }

    /**
     * This returns true if the parameter int is equal to the Main Player's tracking ID.
     * @param playerID is the ID to check if the player has.
     * @return true if the Main Player has an ID equal to the parameter, and false otherwise.
     */
    @Override
    public boolean hasSameID(int playerID) {
        return myPlayer.hasSameID(playerID);
    }

    /**
     * This tells the PlayerView object, stored as an instance variable, to update itself.
     * It first checks to see if a PlayerView object exists. If none do, nothing happens.
     */
    @Override
    public void updateLanguage() {
        if (myPlayer != null) myPlayer.updateLanguage();
    }

    /**
     * This simply updates the PlayerView who is in the Main Player position shown in the UI.
     * @param newMainPlayer is the new player to place in the Main Player position.
     */
    public void setMainPlayer(PlayerView newMainPlayer) {
        myPlayer = newMainPlayer;
        myMainPlayerView.getChildren().add(myPlayer.getView());
    }

    /**
     * Basic getter method allowing access to the held player. This is called in GameView to move the player to OtherPlayers, if need be.
     * @return the PlayerView currently being displayed.
     */
    public PlayerView getMainPlayer() {
        return myPlayer;
    }

    /**
     * This returns whether or not the Main Player view is empty or not.
     * @return whether a PlayerView is being displayed.
     */
    public boolean holdsAPlayer() {
        return myPlayer != null;
    }

    /**
     * This essentially "pauses" the application until the user presses a ready button which appears on screen on method call.
     * Note that this does not pause features of the UI. The language, for example, can still be updated.
     * @param mySelectionType is the type of selection that is occurring (either wager or action).
     */
    public void waitUntilReady(SelectorType mySelectionType) {
        myReadyButton.pauseUntilReady(myMainPlayerView, mySelectionType);
    }

    /**
     * This calls the method of the same name in the PlayerView instance variable, which prompts all BetViews held in it to show their classification.
     * This is driven by the CardShow tag in the Game XML file.
     */
    public void showAllClassification() {
        myPlayer.showAllClassifications();
    }

    /**
     * This calls the method of the same name in the PlayerView instance variable, which prompts all BetViews held in it to hide their classification.
     * This is driven by the CardShow tag in the Game XML file.
     */
    public void hideAllClassification() {
        myPlayer.hideAllClassifications();
    }
}
