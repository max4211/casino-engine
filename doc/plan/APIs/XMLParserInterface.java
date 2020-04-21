import javafx.util.Pair;

import java.util.List;
import java.util.Map;

/**
 * Module that translates XML-defined preferences into Java-friendly structures such as Maps and Lists.
 * Also saves preferences of a game including color scheme, XML rules, and number of players.
 */
interface XMLParserInterface {

    /**
     * Method that translates the XML Deck tag to a List of Pair objects holding the Suit (String) and Value (Integer) for each Card.
     * Called in CardDistribution module to assemble the deck.
     *
     * @return a List with each Pair entry containing the suit and value of a card in the deck.
     */
    public List<Pair> getDeck();

    /**
     * Method that translates the XML Payout Odds tag to a Map
     * Each entry in the Map is a String detailing the Hand Type and a Double indicating its payoff odds
     * If key is not found, assume 1:1 payout odds.
     *
     * @return a Map containing the Payout Odds for every type of winning bet that is not 1:1 odds
     */
    public Map<String, Double> getPayoutOdds();

    /**
     * Translates the XML tag for Winning Java hands into a List of String objects naming each Hand
     * The first entry is the best hand (trumps all) and the last entry is the worst hand (can still win, but loses to every entry before it)
     *
     * @return a List of Strings containing the names of all Hands that can win in a given game
     */
    public List<String> getWinningHands();

    /**
     * Translates the XML tag for Losing Java jands into naming each Hand
     * Any Hand that is classified as an entry in this list is automatically removed from the game and Garbage Collected by the Table
     *
     * @return a List of Strings containing the names of all Hands that can lose in a given game
     */
    public List<String> getLosingHands();

    /**
     * Translates the XML tag for possible Actions in a game into a Map
     * The String keys are EntryBet, DealerAction, and MidGameAction which correspond to Actions taken to get in the game, dealer actions in a turn, and actions a user can take in the middle of a turn.
     * Reflection is used in the ActionGenerator and Table to create these actions and execute them on Bet and Dealer Objects.
     *
     * @return a Map listing all Actions that can be taken to enter a game with a bet, that the dealer can take, and that a player can take in a turn
     */
    public Map<String, List> getActions();

    /**
     * Translates the XML tag for players in a game into a Map
     * The String keys are the names for the players, and the Doubles are their initial bankroll
     *
     * @return a Map containing all the information for players mapping their names to their bankrolls
     */
    public Map<String, Double> getPlayers();

    /**
     * Takes the current settings in a game and saves it to an XML file.
     * Properties saved include color scheme for the game, number of players, and current deck.
     * Basic implementation will not support changing in-game actions or bet heirarchy, but extensions past basic visual customizations can be implemented later.
     *
     * @param fileName is the name of the XML file to save the preferences to
     */
    public void savePreferences(String fileName);
}