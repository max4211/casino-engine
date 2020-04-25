package engine.deck;

import Utility.StringPair;
import engine.dealer.Card;

import java.util.ArrayList;
import java.util.List;

public abstract class Deck implements DeckInterface {

    protected List<Card> myCurrentCards;
    protected List<Card> myBaseCards;

    public Deck(List<StringPair> cards) {
        this.myBaseCards = new ArrayList<>();
        this.myCurrentCards = new ArrayList<>();
        for (StringPair p: cards)
            this.myBaseCards.add(new Card(p));
        this.myCurrentCards = this.myBaseCards;
    }

    @Override
    public void shuffle() {
        this.myCurrentCards = this.myBaseCards;
    }

}
