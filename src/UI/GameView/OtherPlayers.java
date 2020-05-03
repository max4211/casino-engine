package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Visual representation of all players who are not the Main Player and therefore not currently looking at the screen.
 * Implements the StylizedNode interface, wherein all players are held in a VBox which stores PlayerView objects and is accessed via getView()
 * Implements the LanguageResponder interface, wherein an updateLanguage() call updates the text in all displayed players.
 */
public class OtherPlayers implements StylizedNode, LanguageResponder {

    private VBox myPlayers;
    private List<PlayerView> allPlayers;

    /**
     * General constructor that takes in no parameters. This simply constructs the VBox which has players added to it later on.
     * Formatter object is called to format the VBox and set its CSS ID to MainPlayer.
     * The List holding allPlayers currently displayed is initialized to be empty.
     */
    public OtherPlayers() {
        myPlayers = new VBox();
        Formatter.formatOtherPlayers(myPlayers);
        StylizedNode.setStyleID(myPlayers, this.getClass());
        allPlayers = new ArrayList<>();
    }

    /**
     * Method that returns the VBox which represents the Other Players not currently looking at the screen.
     * @return VBox containing all the PlayerViews that are categorized as not the Main Player.
     */
    @Override
    public VBox getView() {
        return myPlayers;
    }

    /**
     * Method that tells every player displayed in the VBox to update their language based off the LanguageBundle's new language.
     */
    @Override
    public void updateLanguage() {
        for (PlayerView tempPlayer : allPlayers) {
            tempPlayer.updateLanguage();
        }
    }

    /**
     * Adds a PlayerView to the VBox displayed and stores it in the allPlayers instance variable.
     * The PlayerView is constructed in the method. Only java primitives and the players LanguageBundle are given.
     * @param name is the name of the player to be added.
     * @param playerID is the tracking ID of the player to be added.
     * @param bankroll is the bankroll of the player to be added.
     * @param languageBundle is the language bundle that the PlayerView created will reference.
     */
    public void addPlayer(String name, int playerID, double bankroll, LanguageBundle languageBundle) {
        PlayerView addedPlayerView = new PlayerView(name, playerID, bankroll, languageBundle);
        addPlayer(addedPlayerView);
    }

    /**
     * Adds a PlayerView to the VBox displayed and stores it in the allPlayers instance variable.
     * @param addedPlayer is the PlayerView to display.
     */
    public void addPlayer(PlayerView addedPlayer) {
        myPlayers.getChildren().add(addedPlayer.getView());
        allPlayers.add(addedPlayer);
    }

    /**
     * Removes a PlayerView from the VBox display, who is assumed to be transferring into the Main Player position.
     * Throws a null pointer exception if the player does not exist. This could be fixed by calling hasPlayerView() first.
     * @param playerID is the ID of the player to remove.
     */
    public void removePlayer(int playerID) {
        PlayerView removedPlayerView = getPlayerView(playerID);
        myPlayers.getChildren().remove(removedPlayerView.getView());
        allPlayers.remove(removedPlayerView);
    }

    /**
     * Basic getter method to return the PlayerView with the ID given as a parameter.
     * The player is assumed to be moving into the MainPlayer position.
     * @param playerID the ID of the player to access.
     * @return the PlayerView with the associated parameter ID, or null if no player stored has it.
     */
    public PlayerView getPlayerView(int playerID) {
        for (PlayerView tempPlayerView : allPlayers)
            if (tempPlayerView.hasSameID(playerID)) return tempPlayerView;
        return null;
    }

    /**
     * Basic method to check if a player is held in OtherPlayers.
     * @param playerID the ID of the player to check if it is being held.
     * @return true if OtherPlayers is displaying a PlayerView with this ID, false otherwise.
     */
    public boolean hasPlayerView(int playerID) {
        return getPlayerView(playerID) != null;
    }

    /**
     * Basic method that clears all bets for all players held in OtherPlayers.
     * This calls the clearBets() method for every player held.
     * This is likely called at the end of a round of a card game.
     */
    public void clearBets() {
        for (PlayerView tempPlayerView : allPlayers) tempPlayerView.clearBets();
    }
}
