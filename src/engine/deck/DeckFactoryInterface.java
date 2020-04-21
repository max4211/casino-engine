package engine.deck;

import Utility.StringPair;

import java.util.List;

public interface DeckFactoryInterface {

    Deck createDeck(List<StringPair> deckList, String deckType);
}
