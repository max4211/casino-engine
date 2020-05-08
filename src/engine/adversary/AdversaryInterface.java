package engine.adversary;

import UI.Utilities.CardTriplet;
import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Implemented by adversary, shows all publicly available method calls
 * @author Max Smith
 */
public interface AdversaryInterface {

    /**
     * Fetch the hand internal to the adversary
     * @return the PlayerHand encapsulated in the adversary
     */
    PlayerHand getHand();

    /**
     * Accept cards from the controller
     * @param c a specific card (from dealer) to accept
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
     * @param showCard is a consumer to show card in the view
     * @param addCard consumer to add card to the view
     * @param getCard supplier to get card from dealer
     */
    void playHand(Consumer<Integer> showCard, Consumer<CardTriplet> addCard, Supplier<Card> getCard);
}
