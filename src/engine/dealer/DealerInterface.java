package engine.dealer;

public interface DealerInterface {

    /**
     * Shuffles the deck to reset it back to its original starting value, essentially reverting the Deck back to its original state.
     */
    void shuffle();

    /**
     * Gets a random card from the deck and then removes that card from said deck.
     */
    Card getCard();

    /**
     * Gets a specified card from the deck and does not remove it from the deck.
     * This will be used in a potential Roulette extension to get Cards for Bet Slips, since
     * in Roulette, you need to Request the Red18 card to have it then pair with Red18, if it is randomly generated.
     * A CardNotFoundException is exactly it sounds: the card is not in the deck.
     * @param requestedCard is a Card that is requested to still be in the deck
     */
    Card getCard(Card requestedCard) throws CardNotFoundException;
}
