package engine.dealer;

/**
 * Interface implemented by call card objects with appropriate getters
 * Used for hand classification and card triplet for view generation
 */
public interface CardInterface {

    /**
     * Called by evaluator package in classification of hands
     * @return the suit of the card
     */
    String getSuit();

    /**
     * Called by evaluator package in classification of hands
     * @return the numerical value of the card
     */
    double getValue();

    /**
     * Get hashcode (ID) of card
     * @return s id of card
     */
    int getID();
}
