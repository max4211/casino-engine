package engine.evaluator.bet;

import engine.bet.Bet;
import engine.hand.PlayerHand;

import java.util.List;

/**
 * Implemented by bet evaluator, offers service to controller to evaluate bets at the end of a round
 */
public interface BetEvaluatorInterface {

    /**
     * Iterates over all of the bets in the collection parameter and assigns each one a winning or losing value stores in a boolean instance variable
     * Multiple bets can be winners given the nature of a push
     * Calls the HandEvaluator compare method often to determine the winner of two hands
     * @param bets is a collection of all bets to iterate over from a turn to assign winning and losing values
     */
    public void evaluateBets(List<Bet> bets);

    /**
     * Evaluate hands against eachother (assume all are classified
     * @param h1 that have been classified in the same competitive pool
     */
    void evaluateHands(PlayerHand h1, PlayerHand h2);
}
