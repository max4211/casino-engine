package xml.xmlreader.interfaces;

import Utility.StringPair;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * Module that translates XML-defined preferences into Java-friendly structures such as Maps and Lists.
 * Also saves preferences of a game including color scheme, XML rules, and number of players.
 * @author Max Smith
 */
public interface GameReaderInterface {

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
    List<StringPair> getDealerAction();

    /**
     * Fetch ingame player actions possible
     * @return a List of strings containing all possible player actions
     */
    Collection<String> getPlayerAction();

    /**
     * Get the competition parameters for this type of game
     * @return
     */
    Map<String, String> getCompetition();

    /**
     * Get table limits to help organize bet structure
     * @return
     */
    double getTableMin();
    double getTableMax();

    /**
     * Fetch the card showing policy for players
     * @return card show policy
     */
    String getCardShow();

    /**
     * Fetch the goal of the game (per round)
     * @return goal of game
     */
    String getGoal();
}
