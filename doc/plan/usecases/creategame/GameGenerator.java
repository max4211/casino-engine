public class GameGenerator {

    private static final XMLParser myXMLParser;

    /**
     * Constructor call to make a new GameGenerator object
     * @param file is the XML file selected by the user to use for generation
     */
    public GameGenerator(File file) {
        this.myXMLParser = new XMLParser(file);
        createGame();
    }

    /**
     * Method to create the entire game ecosystem through a table
     */
    private static void createGame() {
        Table myTable = createTable();
    }

    /**
     * First step in creating the game is creating the table (all logic runs through this)
     * @return
     */
    private static Table createTable() {
        Dealer myDealer = createDealer();
        PlayerCollection myPlayers = createPlayers();
        BetEvaluator myBetEval = createBetEvaluator();
    }

    /**
     * Tables need a dealer, whose functionality is driven by the Deck they hold
     * @return the dealer with a collection of decks
     */
    private Dealer createDealer() {
        Deck myDeck = createDeck(myPairs);
        return new Dealer(myDeck);
    }

    /**
     * Decks are created as a functino of the XML contents in the Deck data file
     * @param myPairs
     * @return
     */
    private Deck createDeck() {
        List<Pair> myPairs = this.myXMLParser.getDeck();
        return new Deck(myPairs);
    }

    /**
     * Players are also parsed in the same way from the XML file
     * @return
     */
    private PlayerCollection createPlayers() {
        Map<String, Double> myPlayers = this.myXMLParser.getPlayers()
        return new PlayerCollection(myPlayers);
    }

}