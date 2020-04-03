/**
 * Module that translates XML-defined preferences into Java-friendly structures such as Maps and Lists.
 * Also saves preferences of a game including color scheme, XML rules, and number of players.
 */
public interface XMLParsing {

    /**
     * Method that translates the XML Deck tag to a List of Pair objects holding the Suit (String) and Value (Integer) for each Card.
     * Called in CardDistribution module to assemble the deck.
     * @return a List with each Pair entry containing the suit and value of a card in the deck.
     */
    public List<Pair> getDeck();

    /**
     * Method that translates the XML Payout Odds tag to a Map
     * Each entry in the Map is a String detailing the Hand Type and a Double indicating its payoff odds
     * If key is not found, assume 1:1 payout odds.
     * @return a Map containing the Payout Odds for every type of winning bet that is not 1:1 odds
     */
    public Map<String, Double> getPayoutOdds();

    /**
     * Translates the XML tag for Winning Java hands into a List of String objects naming each Hand
     * The first entry is the best hand (trumps all) and the last entry is the worst hand (can still win, but loses to every entry before it)
     * @return a List of Strings containing the names of all Hands that can win in a given game
     */
    public List<String> getWinningHands();

    /**
     * Translates the XML tag for Losing Java jands into naming each Hand
     * Any Hand that is classified as an entry in this list is automatically removed from the game and Garbage Collected by the Table
     * @return a List of Strings containing the names of all Hands that can lose in a given game
     */
    public List<String> getLosingHands();
    
    public Map<String, List> getActions()

    public Map<String, Double> getPlayers()

    public Map<String, Double> savePreferences();