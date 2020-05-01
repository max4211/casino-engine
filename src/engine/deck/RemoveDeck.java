package engine.deck;

import Utility.StringPair;
import engine.dealer.Card;

import java.util.List;

/**
 * Specific class of deck which removes cards as you generate tehm
 */
public class RemoveDeck extends Deck {
    public RemoveDeck(List<StringPair> cards) {
        super(cards);
    }

    /**
     * Removes card from deck that is returned
     * @return the specific type of card from the deck
     */
    @Override
    public Card getCard() {
        if (this.myCurrentCards.size() <= 1)
            this.myCurrentCards = this.myBaseCards;
        int index = (int)(Math.random() * this.myCurrentCards.size());
        Card c = this.myCurrentCards.get(index);
        this.myCurrentCards.remove(index);
        return c;
    }

    @Override
    public Card getCard(Card c) {
        return null;
    }
}
