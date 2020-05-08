package engine.deck;

import Utility.StringPair;
import actions.individual.IndividualAction;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * Abstract factory to construct decks based on the desired type
 * @author Max Smith
 */
public class DeckFactory implements DeckFactoryInterface{

    private static final String DECK_PATH = "engine.deck";
    private static final String DECK_SUFFIX = "Deck";

    /**
     * Create a deck from appropariate metadata
     * @param deckList are suit, value string pairs
     * @param deckType type of deck (reflection facilitator)
     * @return the abstractly casted deck object
     */
    @Override
    public Deck createDeck(List<StringPair> deckList, String deckType) {
        try {
            Class clazz = Class.forName(createDeckpath(deckType));
            Constructor ctor = clazz.getConstructor(List.class);
            return (Deck) ctor.newInstance(deckList);
        } catch (Exception e) {
            throw new ReflectionException(e);
        }
    }

    private String createDeckpath(String deckType) {
        return String.format("%s.%s%s", DECK_PATH, deckType, DECK_SUFFIX);
    }
}
