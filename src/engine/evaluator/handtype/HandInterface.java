package engine.evaluator.handtype;

public interface HandInterface {

    /**
     * Evaluate whether the hand is accurate or not
     * @return true or false for if hand is correct
     */
    boolean evaluate();

    /**
     * In classifying hand, a power score is given based on its relative strength within the hand class
     * @return the power of the hand
     */
    double getPower();
}
