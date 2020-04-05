package engine.hand;

import engine.dealer.Card;

public interface HandInterface {

    /**
     * Method called by the Dealer to enable hand to accept card
     * @param c is the card to be accepted
     */
    void acceptCard(Card c);

}
