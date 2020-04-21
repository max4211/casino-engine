package engine.deck;

import Utility.StringPair;
import actions.individual.IndividualAction;
import exceptions.ReflectionException;

import java.lang.reflect.Constructor;
import java.util.List;

public class DeckFactory implements DeckFactoryInterface{

    private static final String DECK_PATH = "engine.deck";
    private static final String DECK_SUFFIX = "Deck";

    public DeckFactory() {

    }

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
