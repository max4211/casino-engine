package engine.hand;

import engine.dealer.Card;

import java.util.List;

public interface HandInterface {

    /**
     * Method called by the Dealer to enable hand to accept card
     * @param c is the card to be accepted
     */
    void acceptCard(Card c);

    /**
     * Called by evaluators and GUI to determine cards inside hand
     * @return
     */
    List<Card> getCards();

    /**
     * Called by the HandClssifier to add a classification tag to a hand
     * @param type
     * @param loser checks if the hand lost
     */
    void classifyHand(ClassifiedHand type, boolean loser);

    /**
     * Called by the Bet to determine what type of hand it holds
     * @return the classification (String, TODO enum)
     */
    ClassifiedHand getClassification();

    /**
     * Checks if the hand is a loser for garbage collection
     * @return
     */
    boolean isLoser();

}
