package engine.evaluator;

import engine.bet.Bet;
import engine.hand.Hand;

import java.util.Collection;
import java.util.List;

public interface BetEvaluatorInterface {

    /**
     * Iterates over all of the bets in the collection parameter and assigns each one a winning or losing value stores in a boolean instance variable
     * Multiple bets can be winners given the nature of a push
     * Calls the HandEvaluator compare method often to determine the winner of two hands
     * @param bets is a collection of all bets to iterate over from a turn to assign winning and losing values
     */
    public void evaluateBets(List<Bet> bets);

    /**
     * Updates the wager of a bet given its payout odds, such that bets with large payout odds become worth more value
     * The wager of the bet is then added to a player's bankroll, so this allows the cashing of the ticket to reflect the payout value
     * @param bets is a collection of winning bets from a turn to iterate over and update payout odds
     */
    public void updateWagers(List<Bet> bets);

    /**
     * Evaluate hands against eachother (assume all are classified
     * @param h1 that have been classified in the same competitive pool
     */
    void evaluateHands(Hand h1, Hand h2);
}
