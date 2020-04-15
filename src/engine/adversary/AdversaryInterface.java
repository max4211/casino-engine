package engine.adversary;

import Utility.CardTriplet;
import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.function.Consumer;
import java.util.function.Supplier;

public interface AdversaryInterface {

    /**
     * Fetch the hand internal to the adversary
     * @return
     */
    PlayerHand getHand();

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

    /**
     * Gives the adversary method calls it needs to process a hand
     * @param showCard
     * @param addCard
     * @param getCard
     */
    void playHand(Consumer<Integer> showCard, Consumer<CardTriplet> addCard, Supplier<Card> getCard);
}
