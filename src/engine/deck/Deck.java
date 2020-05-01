package engine.deck;

import Utility.StringPair;
import engine.dealer.Card;

import java.util.ArrayList;
import java.util.List;

/**
 * Held by dealer, encapsulates collection of cards
 */
public abstract class Deck implements DeckInterface {

    protected List<Card> myCurrentCards;
    protected List<Card> myBaseCards;

    /**
     * Constructor for a deck, establishes original cards and current "shoe"
     * @param cards sting pair objects encapsulating suit and double parseable value
     */
    public Deck(List<StringPair> cards) {
        this.myBaseCards = new ArrayList<>();
        this.myCurrentCards = new ArrayList<>();
        for (StringPair p: cards)
            this.myBaseCards.add(new Card(p));
        this.myCurrentCards = this.myBaseCards;
    }

    /**
     * Shuffle cards, replace current cards with base cards
     */
    @Override
    public void shuffle() {
        this.myCurrentCards = this.myBaseCards;
    }

}
