package engine.dealer;

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
     * Assign a card to be communal for all members
     */
    void assignCommunal();

    /**
     * Get hashcode (ID) of card
     * @return
     */
    int getID();
}
