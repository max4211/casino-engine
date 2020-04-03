/**
 *
 */
public interface BetEvaluationInterface {

    /**
     * Iterates over all of the bets in the collection parameter and assigns each one a winning or losing value stores in a boolean instance variable
     * Multiple bets can be winners given the nature of a push
     * Calls the HandEvaluator compare method often to determine the winner of two hands
     * @param Bets
     */
    public void evaluateBets(Collection Bets);
}