package data.xmlreader;

import engine.dealer.Card;

import java.util.List;
import java.util.Map;

/**
 * Module that translates XML-defined preferences into Java-friendly structures such as Maps and Lists.
 * Also saves preferences of a game including color scheme, XML rules, and number of players.
 */
public interface GameReaderInterface {

    /**
     * Method that translates the XML Deck tag to a List of Pair objects holding the Suit (String) and Value (Integer) for each Card.
     * Called in CardDistribution module to assemble the deck.
     *
     * @return a List with each Pair entry containing the suit and value of a card in the deck.
     */
    List<Pair> getDeck();

    /**
     * Method that translates the XML Payout Odds tag to a Map
     * Each entry in the Map is a String detailing the Hand Type and a Double indicating its payoff odds
     * If key is not found, assume 1:1 payout odds.
     *
     * @return a Map containing the Payout Odds for every type of winning bet that is not 1:1 odds
     */
    Map<String, Double> getPayoutOdds();

    /**
     * Fetch entry bet, all casino games start with such a bet and this is a differentiating factor driven by data
     * @return a string containing the entry bet type (e.g. Generic or Specific)
     */
    String getEntryBet();

    /**
     * Fetch dealer opening action, all casino games start with dealer action
     * @return a pair containing action type and quantity
     */
    Pair getDealerAction();

    /**
     * Fetch ingame player actions possible
     * @return a List of strings containing all possible player actions
     */
    List<String> getPlayerAction();

    /**
     * Takes the current settings in a game and saves it to an XML file.
     * Properties saved include color scheme for the game, number of players, and current deck.
     * Basic implementation will not support changing in-game actions or bet heirarchy, but extensions past basic visual customizations can be implemented later.
     *
     * @param fileName is the name of the XML file to save the preferences to
     */
    void savePreferences(String fileName);

    /**
     * Get the type of competition for the game in raw string format
     * @return
     */
    String getCompetition();

}
