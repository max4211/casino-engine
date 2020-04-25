package engine.deck;

import Utility.StringPair;
import engine.dealer.Card;

import java.util.List;

public class RemoveDeck extends Deck {
    public RemoveDeck(List<StringPair> cards) {
        super(cards);
    }

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
