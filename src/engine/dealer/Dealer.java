package engine.dealer;

import engine.deck.Deck;
import engine.deck.DeckInterface;

/**
 * Every table has a dealer
 * Dealers encapsulate a deck (colelction of cards)
 * Two publicly available services
 * Get cards (specific or random) and shuffle the deck (replace with original)
 */
public class Dealer implements DeckInterface {

    private Deck myDeck;

    public Dealer(Deck deck) {
        this.myDeck = deck;
    }

    /**
     * Shuffles the deck to reset it back to its original starting value, essentially reverting the Deck back to its original state.
     */
    @Override
    public void shuffle() {
        myDeck.shuffle();
    }

    /**
     * Gets a random card from the deck and then removes that card from said deck.
     */
    @Override
    public Card getCard() {
        return myDeck.getCard();
    }

    /**
     * Gets a specified card from the deck and does not remove it from the deck.
     * This will be used in a potential Roulette extension to get Cards for Bet Slips, since
     * in Roulette, you need to Request the Red18 card to have it then pair with Red18, if it is randomly generated.
     * A CardNotFoundException is exactly it sounds: the card is not in the deck.
     * @param requestedCard is a Card that is requested to still be in the deck
     */
    @Override
    public Card getCard(Card requestedCard) throws CardNotFoundException {
        return myDeck.getCard(requestedCard);
    }
}
