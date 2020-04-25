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
