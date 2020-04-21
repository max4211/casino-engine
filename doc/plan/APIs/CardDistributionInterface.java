import engine.dealer.Card;
import engine.dealer.CardNotFoundException;

/**
 * Interface handling all Card distribution via a Dealer object. Card and Deck objects are interacted with internally.
 */
public interface CardDistributionInterface {

    /**
     * Shuffles the deck to reset it back to its original starting value, essentially reverting the Deck back to its original state.
     */
    public void shuffle();

    /**
     * Gets a random card from the deck and then removes that card from said deck.
     */
    public void dealCard();

    /**
     * Gets a specified card from the deck and does not remove it from the deck.
     * This will be used in a potential Roulette extension to get Cards for Bet Slips, since
     * in Roulette, you need to Request the Red18 card to have it then pair with Red18, if it is randomly generated.
     * A CardNotFoundException is exactly it sounds: the card is not in the deck.
     * @param requestedCard is a Card that is requested to still be in the deck
     */
    public void dealCard(Card requestedCard) throws CardNotFoundException;
}