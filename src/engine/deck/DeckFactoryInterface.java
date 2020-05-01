package engine.deck;

import Utility.StringPair;

import java.util.List;

/**
 * Implemented by deck factory, single purpose to rest of applicaiton
 * Generate deck objects from appropriate metadata
 */
public interface DeckFactoryInterface {

    /**
     * Create a deck from appropariate metadata
     * @param deckList are suit, value string pairs
     * @param deckType type of deck (reflection facilitator)
     * @return the abstractly casted deck object
     */
    Deck createDeck(List<StringPair> deckList, String deckType);
}
