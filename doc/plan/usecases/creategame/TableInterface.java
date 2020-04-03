public interface TableInterface {

    /**
     * Analyzes the players
     * @param players is the list of all players
     */
    void setPlayers(PlayerCollection players);

    /**
     * Assigns the dealer to the table
     * @param dealer
     */
    void setDealer(Dealer dealer);

    /**
     * Assigns the betEvaluator the the table
     * @param eval
     */
    void setBetEvaluator(BetEvaluator eval);

    /**
     * Assigns the handEvaluator to the table
     * @param eval
     */
    void setHandEvaluator(HandEvaluator eval);

}