package engine.dealer;

import data.xmlreader.Pair;

import java.util.ArrayList;
import java.util.List;

public class Deck implements DealerInterface {

    private List<Card> myCards;

    public Deck(List<Pair> cards) {
        this.myCards = new ArrayList<>();
        for (Pair p: cards) {
            this.myCards.add(new Card(p));
        }
    }

    @Override
    public void shuffle() {

    }

    @Override
    public Card getCard() {
        return null;
    }

    @Override
    public Card getCard(Card c) {
        return null;
    }
}
