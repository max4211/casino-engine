package UI.Interfaces;

import UI.GameView.BetView;
import UI.Utilities.CardTriplet;
import engine.dealer.Card;

import java.io.File;
import java.util.List;

/**
 * Interface that is implemented by the GameView class by means of design by contract.
 * Method calls from a Controller object that bridges the frontend to the backend.
 *  Class is predicated upon heavy composition including PlayerView, MainPlayerView, OtherPlayersView, BetView, PotView, and HandView objects.
 *  Most method calls are simply redirected to their proper object that is held via composition.
 * @author Eric Doppelt
 */
public interface GameViewInterface {
    /**
     * Creates the adversary, which is a HandView, and adds it to the screen. This method must be called before cards are added to the adversary.
     * @param hand is the list of information regarding the cards held by the adversary at initialization via CardTriplets
     */
    void renderAdversary(List<CardTriplet> hand);

    /**
     * Shows a card held by the adversary by calling the method of the same name on a HandView object.
     * @param cardId is the tracking ID of the card to be shown.
     */
    void showAdversaryCard(int cardId);

    /**
     * Adds a card to the adversary by calling the method of the same name on a HandView object.
     * @param cardID is a CardTriplet containing the information of the card to be added. A better name would be cardInfo.
     */
    void addAdversaryCard(CardTriplet cardID);

    /**
     * Clears the adversary, if it exists, by calling the method of clearHand() on the HandView object.
     */
    void clearAdversary();

    /**
     * Adds a card if it is not already contained by calling the method of the same name on the specified PlayerView object.
     * @param cardInfo is the information of the card to add, if need be, via a CardTriplet.
     * @param playerID is the tracking ID of the player to add the card to.
     * @param betID is the tracking ID of the bet to add the card to.
     */
    void addCardIfAbsent(CardTriplet cardInfo, int playerID, int betID);

    /**
     * Shows a card held in a player's hand by calling the method of the same name on the appropriate PlayerView object, as specified by the tracking ID.
     * @param playerID is the tracking ID of the player who holds the card
     * @param betID is the tracking ID of the bet which holds the card
     * @param cardID is the tracking ID of the card to be shown
     */
    void showCard(int playerID, int betID, int cardID);

    /**
     * Hides a card held in a player's hand by calling the method of the same name on the appropriate PlayerView object, as specified by the tracking ID.
     * @param playerID is the tracking ID of the player who holds the card
     * @param betID is the tracking ID of the bet which holds the card
     * @param cardID is the tracking ID of the card to be hidden
     */
    void hideCard(int playerID, int betID, int cardID);

    /**
     * Adds a bet with cards to a PlayerView object by calling the method of the same name on the specified PlayerView object.
     * @param handInfo is a list of CardTriplets specifying the cards already in the Bet's hand
     * @param wager is the double amount placed on the bet
     * @param classification is the String classification of the hand of cards
     * @param playerID is the tracking ID for the Player who places the bet
     * @param betID is the tracking ID for the bet placed
     */
    void addBet(List<CardTriplet> handInfo, double wager, String classification, int playerID, int betID);

    /**
     * Adds a bet without cards to a PlayerView object by calling the method of the same name on the specified PlayerView object.
     * @param wager is the double amount placed on the bet
     * @param playerID is the tracking ID for the Player who places the bet
     * @param betID is the tracking ID for the bet placed
     */
    void addBet(double wager, int playerID, int betID);

    /**
     * Removes a bet from a PlayerView object by calling the method of the same name on the specified PlayerView object.
     * @param playerId is the tracking ID of the player from which the bet is removed.
     * @param betId is the tracking ID of the bet to remove.
     */
    void removeBet(int playerId, int betId);

    /**
     * Creates a set of common cards displayed.
     * @param hand is the list of common cards to display in the hand via CardTriplets.
     */
    void renderCommonCards(List<CardTriplet> hand);

    /**
     * Shows a common card that is referenced by its tracking ID. It is assumed the card is already held in the Common hand.
     * @param cardID is the card to be shown.
     */
    void showCommonCard(int cardID);

    /**
     * Adds a player to the game and places him/her into the OtherPlayers category.
     * @param name is the name of the new player
     * @param playerId is the tracking ID of the new player
     * @param bankroll is the amount of money held by the new player
     */
    void addPlayer(String name, int playerId, double bankroll);

    /**
     * Method which sets the Main Player to the PlayerView specified by the tracking ID given. Change only occurs if OtherPlayers contains the specified PlayerView at first.
     * @param playerId is the tracking ID of the player to place into the MainPlayer position.
     */
    void setMainPlayer(int playerId);

    /**
     * Prompts the UI to wait for user input via a ReadyButton and then create a dialogue prompting for a bet after doing so.
     * The method does not return until it has a valid bet, but the UI does NOT pause while the Ready Button is waiting.
     * @param minBet is the minimum bet acceptable given via a double
     * @param maxBet is the maximum bet acceptable given via a double
     * @return a double that is the selected bet, assumed to be given by the Main Player.
     */
    double selectWager(double minBet, double maxBet);

    /**
     * Sets a new wager for a BetView object by calling the updateWager() method on the specified PlayerView object.
     * @param newWager is the new amount to be placed on the specified bet.
     * @param playerID is the tracking ID of the player who holds the bet which needs updating.
     * @param BetID is the tracking ID of the bet which needs updating.
     */
    void setWager(double newWager, int playerID, int BetID);

    /**
     * Sets a new bankroll for a PlayerView object by calling the updateBankRoll() method on the specified PlayerView object.
     * @param newBankroll is the new bankroll for the specified player.
     * @param playerID is the tracking ID of the player who has a new bankroll.
     */
    void setBankRoll(double newBankroll, int playerID);

    /**
     * Prompts the UI to wait for user input via a ReadyButton and then create a dialogue prompting for an action from a preset list of choices after doing so.
     * The method does not return until it has a valid action but the UI does NOT pause while the Ready Button is waiting.
     * @param allActions is a list of Strings representing all the actions the User can make at a given time.
     * @return the action selected by the user.
     */
    String selectAction(List<String> allActions);

    /**
     * Clears the bet from every player shown in the UI by calling the method of the same name on the MainPlayer and the OtherPlayers.
     */
    void clearAllBets();

    /**
     * Displays an exception by calling the method of the same name on the ExceptionDisplayer and passing it the exception.
     * @param ex is the exception to be displayed.
     */
    void displayException(Exception ex);

    /**
     * Prompts the user with a button that, when clicked, will launch a new game (as in a new round of the game he/she is playing).
     * @param startNewGame is a GameCaller which is a Functional Interface that defines one method which is assumed to prompt the controller to start a new game.
     */
    void promptNewGame(GameCaller startNewGame);

    /**
     * Creates a dialogue to be displayed to the user that simply shows a String of text given by the backend. This is used to show goal updates at the end of rounds.
     * @param s is a formatted String to be displayed to the user.
     */
    void displayText(String s);

    /**
     * Classifies the hand of a specific bet by calling updateClassification() on a specified PlayerView object.
     * @param classification is the new classification of the bet, given as a String.
     * @param playerID is the tracking ID of the player who owns the bet to up classified.
     * @param betID is the tracking ID of the bet to classify.
     */
    void classifyHand(String classification, int playerID, int betID);

    /**
     * Sets the specified bet given to a losing status by calling the method of the same name on the specified PlayerView object and passing it the bet ID.
     * @param playerID is the tracking ID of the player who owns the bet that is to be updated to a loser.
     * @param betID is the tracking ID of the bet that is to be updated to be a loser.
     */
    void setLoser(int playerID, int betID);

    /**
     * Sets the specified bet given to a winning status by calling the method of the same name on the specified PlayerView object and passing it the bet ID.
     * @param playerID is the tracking ID of the player who owns the bet that is to be updated to a winner.
     * @param betID is the tracking ID of the bet that is to be updated to be a winner.
     */
    void setWinner(int playerID, int betID);

    /**
     * Creates a pot which appears on screen. Does so by simply adding a PotView node.
     * @param initialPot is the amount of money put in the pot at its construction.
     */
    void renderPot(double initialPot);

    /**
     * Updates the amount of money in the pot by calling the method of the same name on the PotView object.
     * @param newPot is the new amount of money to be held in the pot.
     */
    void setPot(double newPot);

    /**
     * DEPRECTATED: unsupported method which was supposed to initialize the game, but is now outdated. Use the general constructor and getView() instead.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated
    void renderGame(File image) throws UnsupportedOperationException;

    /**
     * DEPRECTATED: unsupported method which was meant to deal a card, but is now outdated. Use addCard() instead.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated
    void giveCard(BetView b, Card c) throws UnsupportedOperationException;

    /**
     * DEPRECTATED: unsupported method which was supposed to show a card, but is now outdated. Use showCard() using tracking IDs instead.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated
    void showCard(BetView b, Card c) throws UnsupportedOperationException;

    /**
     * DEPRECTATED: unsupported method which was supposed to show all cards in a bet, but is now outdated. Use showCard() using tracking IDs instead on every card.
     * @throws UnsupportedOperationException on every method call.
     */
    @Deprecated
    void showCards(BetView b) throws UnsupportedOperationException;
}