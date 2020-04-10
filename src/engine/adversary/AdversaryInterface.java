package engine.adversary;

import engine.dealer.Card;
import engine.hand.Hand;

public interface AdversaryInterface {

    /**
     * Fetch the hand internal to the adversary
     * @return
     */
    Hand getHand();

    /**
     * Accept cards from the controller
     * @param c
     */
    void acceptCard(Card c);

    /**
     * Get a single card from the adversary
     * @return a single card
     */
    Card getCard();

    /**
     * Called by controller in end game to increase adversary cards until a point
     * @return the sum of all cards in adversary hand
     */
    int handSum();
}
