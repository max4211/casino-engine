package engine.evaluator.handtype;

/**
 * Impelmented by all hands, evalutes whether or not the hand is truely classified
 * @author Max Smith
 */
public interface HandInterface {

    /**
     * Evaluate whether the hand is accurate or not
     * @return true or false for if hand is correct
     * @param cardsInHand
     */
    boolean evaluate(int cardsInHand);

    /**
     * In classifying hand, a power score is given based on its relative strength within the hand class
     * @return the power of the hand
     */
    double getPower();
}
