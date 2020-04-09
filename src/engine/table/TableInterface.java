package engine.table;

import Utility.CardTriplet;
import data.xmlreader.Pair;
import engine.bet.Bet;
import engine.player.Player;

import java.util.List;
import java.util.function.Consumer;

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
     * @param s is the bet type that was placed
     * @param wager is the wager size for that bet
     */
    int placeEntryBet(int playerHash, String s, double wager);

    /**
     * called by controller for dealer to distribute cards to players
     * @param dealerAction dictates what type of action is conducted
     */
    void performDealerAction(Pair dealerAction);

    /**
     * called by controller to perform player action (as selected)
     * @param action to perform
     */
    void performPlayerAction(String action);

    /**
     * called by the controller to identify players
     * @return all players unique hash codes
     */
    List<Integer> getPlayerHashCodes();

    /**
     * Get all of the current players at the table
     * @return list of players
     */
    List<Player> getPlayers();

    /**
     * Determine if any players are still active in the round/game
     * @return true if any players are still alive
     */
    boolean hasActivePlayers();

    /**
     * fetch table limits of betting to determine slider ranges for user display
     * @return table min/max as specified in data file
     */
    int getTableMin();
    int getTableMax();

}
