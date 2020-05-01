package engine.deck;

import Utility.StringPair;
import engine.dealer.Card;

import java.util.List;

/**
 * Specific type of deck which replaces cards as they are taken (never removed)
 */
public class ReplaceDeck extends Deck {

    public ReplaceDeck(List<StringPair> cards) {
        super(cards);
    }

    /**
     * Generate a random index and return the approrpriate card @ that index
     * @returns the appropriate card (randomly generated)
     */
    @Override
    public Card getCard() {
        int index = (int)(Math.random() * this.myCurrentCards.size());
        return this.myCurrentCards.get(index);
    }

    @Override
    public Card getCard(Card c) {
        return null;
    }
}
