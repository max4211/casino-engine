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

}
