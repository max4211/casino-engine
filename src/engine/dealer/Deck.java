package engine.dealer;

import data.xmlreader.Pair;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Deck implements DealerInterface {

    private List<Card> myCurrentCards;
    private List<Card> myBaseCards;

    public Deck(List<Pair> cards) {
        this.myBaseCards = new ArrayList<>();
        this.myCurrentCards = new ArrayList<>();
        for (Pair p: cards) {
            this.myBaseCards.add(new Card(p));
        }
        this.myCurrentCards = this.myBaseCards;
    }

    @Override
    public void shuffle() {
        this.myCurrentCards = this.myBaseCards;
    }

    @Override
    public Card getCard() {
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
