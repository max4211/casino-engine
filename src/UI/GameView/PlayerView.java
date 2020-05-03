package UI.GameView;

import UI.Interfaces.LanguageResponder;
import UI.Interfaces.StylizedNode;
import UI.Interfaces.TaggableNode;
import UI.Utilities.CardTriplet;
import UI.Utilities.Formatter;
import UI.Utilities.LanguageBundle;
import javafx.scene.layout.HBox;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that represents a single player in the UI using a JavaFX HBox.
 * Contains information relating to the name, bankroll, and bets that a player has.
 * Composed with a PlayerInfo object and a list of BetViews which are all shown in one HBox.
 * Implements the StylizedNode interface, returning an HBox containing this information with a CSS ID of PlayerView on a getView() method call.
 * Implements the TaggableNode interface, with the method hasSameId() that returns true if the PlayerView has the given tracking ID.
 * Implements the LanguageResponder interface, which updates the text displayed in the PlayerInfo object.
 */
public class PlayerView implements StylizedNode, TaggableNode, LanguageResponder {

    private List<BetView> myBets;
    private PlayerInfo myInfo;
    private HBox myPlayerView;
    private int myID;

    /**
     * Constructor for a PlayerView that takes in a name, ID (for future reference), bankroll, and LanguageBundle.
     * The LanguageBundle passes through the PlayerView and is only given in the PlayerInfo object held via composition.
     * The ID has no connection to the player information or functionality and is only used to make the PlayerView easily identifiable within the GameView class.
     * @param name is the name of the player given as a String
     * @param ID is the tracking ID of the player given as an int
     * @param bankroll is the bankroll, or amount of money a player has, given as a double
     * @param languageBundle is the LanguageBundle that the PlayerInfo object should reference
     */
    public PlayerView(String name, int ID, double bankroll, LanguageBundle languageBundle) {
        myPlayerView = new HBox();
        Formatter.formatPlayerView(myPlayerView);
        StylizedNode.setStyleID(myPlayerView, this.getClass());
        myID = ID;
        myBets = new ArrayList<>();
        myInfo = new PlayerInfo(name, bankroll, languageBundle);
        myPlayerView.getChildren().add(myInfo.getView());
    }

    /**
     * Method that returns an HBox which contains the PlayerInfo and a series of BetView objects.
     * PlayerInfo always exists. It is possible for there to be no BetView objects.
     * @return HBox containing all information pertinent to a single player.
     */
    @Override
    public HBox getView() {
        return myPlayerView;
    }

    /**
     * Method that returns a boolean indicating whether or not the PlayerView object has a tracking ID equal to the parameter.
     * @param ID is the traccking ID to compare this object's with.
     * @return true if the PlayerView has the same ID as the parameter, and false otherwise.
     */
    @Override
    public boolean hasSameID(int ID) {
        return myID == ID;
    }

    /**
     * Method which operates on objects this PlayerView is composed with.
     * Tells the PlayerInfo to update its language.
     * Tells every Bet held for this one player to update its language.
     */
    @Override
    public void updateLanguage() {
        myInfo.updateLanguage();
        for (BetView tempBetView : myBets) tempBetView.updateLanguage();
    }

    /**
     * Method which adds a BetView to this PlayerView and is shown in the displayed HBox.
     * The BetView is constructed in the method call and is assumed to hold some number of cards already.
     * @param hand is the list of CardTriplets representing the hand that the bet holds at initialization.
     * @param wager is the amount placed on the bet.
     * @param classification is the type of hand held.
     * @param betID is the tracking ID for the bet.
     * @param languageBundle is the LanguageBundle for the bet to reference.
     * @param cardImage is the image do be displayed by the cards currently in the bet who are face down (hidden).
     */
    public void addBet(List<CardTriplet> hand, double wager, String classification, int betID, LanguageBundle languageBundle, String cardImage) {
        displayBetView(new BetView(hand, wager, classification, betID, languageBundle, cardImage));
    }

    /**
     * Method which adds a BetView to this PlayerView and is shown in the displayed HBox.
     * The BetView is constructed in the method call and is assumed to have no cards to display yet.
     * @param wager is the amount placed on the bet.
     * @param betID is the tracking ID for the bet.
     * @param languageBundle is the LanguageBundle for the bet to reference.
     */
    public void addBet(double wager, int betID, LanguageBundle languageBundle) {
        displayBetView(new BetView(wager, betID, languageBundle));
    }

    /**
     * This removes a bet from the displayed PlayerView.
     * It is assumed the bet that the ID refers to is held.
     * @param betID is the tracking ID of the bet to remove.
     */
    public void removeBet(int betID) {
        BetView removedBet = getBet(betID);
        myBets.remove(removedBet);
        myPlayerView.getChildren().remove(removedBet.getView());
    }

    /**
     * Method which tells a specific bet to show a specific card, based off of tracking IDs.
     * Implementation of the method is held in the BetView object.
     * @param betID is the id of the bet holding the card to be removed.
     * @param cardID is the id of the card to be removed, within the bet.
     */
    public void showCard(int betID, int cardID) {
        getBet(betID).showCard(cardID);
    }

    /**
     * Method which adds a card to a given bet if the bet does not already have the card.
     * Implementation to add the card is held in the BetView object.
     * @param cardInfo is a CardTriplet containing all the information regarding the card (suit, value, id).
     * @param betID is the tracking ID of the bet to which the card should be added to
     * @param cardImage is the name of the image that should be showed if the card is face down (hidden). This is assumed to be in the gameIcons package.
     */
    public void addCardIfAbsent(CardTriplet cardInfo, int betID, String cardImage) {
        getBet(betID).addCardIfAbsent(cardInfo, cardImage);
    }

    /**
     * Sets the wager for a given bet, specified by ID, to a new value.
     * Implementation is held in BetView object.
     * @param betID is the tracking ID of the bet who needs an update to its wager
     * @param newWager is the new amount of money placed on the bet.
     */
    public void updateWager(int betID, double newWager) {getBet(betID).updateWager(newWager);}

    /**
     * Updates the player's bankroll by calling the method of the same name on the BetInfo object that the PlayerView is composed with.
     * @param newBankroll is the new amount left in the bankroll.
     */
    public void updateBankRoll(double newBankroll) {
        myInfo.updateBankroll(newBankroll);
    }

    /**
     * Method which clears all the bets held by a player. This is usually called after a round of a card game ends.
     * Nothing happens if the player holds no bets.
     */
    public void clearBets() {
        for (BetView deletedBetView : myBets) myPlayerView.getChildren().remove(deletedBetView.getView());
        myBets.clear();
    }

    /**
     * Hides a card within a specified bet by turning it face down.
     * Implementation is held in the BetView object.
     * @param betID is the tracking ID of the bet who holds the card.
     * @param cardID is the tracking ID of the card to hide within the bet.
     */
    public void hideCard(int betID, int cardID) {
        getBet(betID).hideCard(cardID);
    }

    /**
     * Updates the classification of a bet, which describes the hand type it holds.
     * Implementation is held in the BetView object, whose method of the same name is called.
     * @param betID is the tracking ID of the BetView who needs the classification update.
     * @param newClassification is the new classification of the hand which is a String.
     */
    public void updateClassification(int betID, String newClassification) {
        getBet(betID).updateClassification(newClassification);
    }

    /**
     * Sets the specified bet to a losing status.
     * Implementation is held in the BetView object, whose method of the same name is called.
     * @param betID is the tracking ID of the bet to set to a losing status.
     */
    public void setLoser(int betID) {
        getBet(betID).setLoser();
    }

    /**
     * Sets the specified bet to a winning status.
     * Implementation is held in the BetView object, whose method of the same name is called.
     * @param betID is the tracking ID of the bet to set to a winning status.
     */
    public void setWinner(int betID) {
        getBet(betID).setWinner();
    }

    /**
     * Hides all classifications that are attached to every bet held by the player.
     * This is called when a player is moved out of the Main Player into the Other Players.
     * Implementation to hide classifications is held in the BetView object, whose method is called.
     */
    public void hideAllClassifications() {
        for (BetView tempBetView : myBets) tempBetView.hideClassification();
    }

    /**
     * Shows all classifications that are attached to every bet held by the player.
     * This is called when a player is moved out of the Other Players into the Main Player position.
     * Implementation to show classifications is held in the BetView object, whose method is called.
     */
    public void showAllClassifications() {
        for (BetView tempBetView : myBets) tempBetView.showClassification();
    }

    private void displayBetView(BetView addedBetView) {
        myBets.add(addedBetView);
        myPlayerView.getChildren().add(addedBetView.getView());
    }

    private BetView getBet(int ID) {
        for (BetView tempBetView : myBets) {
            if (tempBetView.hasSameID(ID)) return tempBetView;
        }
        return null;
    }
}
