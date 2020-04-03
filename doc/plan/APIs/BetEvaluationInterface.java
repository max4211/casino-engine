/**
 * Module that allows Bets to be updated based on their winning or losing status and allows payout odds to be applied to wagers
 * Collections of Bets are fed in and their internal statuses are then updated according to the logic of the game
 */
public interface BetEvaluationInterface {

    /**
     * Iterates over all of the bets in the collection parameter and assigns each one a winning or losing value stores in a boolean instance variable
     * Multiple bets can be winners given the nature of a push
     * Calls the HandEvaluator compare method often to determine the winner of two hands
     * @param Bets is a collection of all bets to iterate over from a turn to assign winning and losing values
     */
    public void evaluateBets(Collection Bets);

    /**
     * Updates the wager of a bet given its payout odds, such that bets with large payout odds become worth more value
     * The wager of the bet is then added to a player's bankroll, so this allows the cashing of the ticket to reflect the payout value
     * @param Bets is a collection of winning bets from a turn to iterate over and update payout odds
     */
    public void updateWagers(Collection Bets);

}