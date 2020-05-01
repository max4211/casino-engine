package engine.table;

import Utility.StringPair;
import controller.enums.EntryBet;
import engine.adversary.Adversary;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.player.Player;

import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

/**
 * Interface implemented by Table object
 * Publicly available services to the controller (and funcitonal interfaces to other parts of the game (utilities)
 */
public interface TableInterface {

    /**
     * Producer for the view to accept strings in response to bet creation
     * @param s is the string that corresponds to the action
     */
    void acceptString(String s);

    /**
     * Place the bet accordingly with the front end selection
     * @return id of the bet that has been placed
     * @param playerHash is the identification number of the appropriate player
     * @param betType is the bet type that was placed
     * @param wager is the wager size for that bet
     * @return bet that was just placed
     */
    Bet placeEntryBet(int playerHash, String betType, double wager);

    /**
     * called by controller for dealer to distribute cards to players
     * @param dealerAction dictates what type of action is conducted
     */
    void performDealerAction(StringPair dealerAction);

    /**
     * called by the controller to identify players
     * @return all players unique hash codes
     */
    List<Integer> getPlayerHashCodes();

    /**
     * Get all of the current players at the table
     * @return list of players
     */
    Collection<Player> getPlayers();

    /**
     * Determine if any players are still active in the round/game
     * @return true if any players are still alive
     */
    boolean hasActivePlayers();

    /**
     * fetch table limits of betting to determine slider ranges for user display
     * @return table min/max as specified in data file
     */
    double getTableMin();
    double getTableMax();

    /**
     * Finds next active player in the list who has the action
     * @return the appopriate player whose action it is
     */
    Player getNextPlayer();

    /**
     * Create an adversary
     * @param min are parameters to adversary gameplay
     * @return the adversary inside of the game
     */
    Adversary createAdversary(double min);

    /**
     * Returns card that was just given to adversary
     */
    Card giveAdversaryCard();


    /**
     * Gives functional interface within dealer
     * Dealer only exists in player, gives actions way to get cards without knowledge of dealer
     * @return supplier from dealer
     */
    Supplier<Card> getDealCardMethod();

    /**
     * Get a list of commmunal cards at the table
     * @return list of communal cards
     */
    List<Card> getCommunalCards();

    /**
     * Called to reset the game to its appropriate state
     */
    void restartGame();

    /**
     * Get the current bet value that needs to be set
     * @param bet sets the bet to where others must match to stay in the round
     */
    void setCurrentBet(double bet);

    /**
     * Fetch the current bet value that needs to be matched
     * @return current bet that needs to be matched (after a BetAction0
     */
    double getCurrentBet();

    /**
     * Count toatl number of active players at the table
     * @return total active players at the table
     */
    int getTotalActivePlayers();
}
