package engine.dealer;

import engine.deck.Deck;
import engine.deck.DeckInterface;

public class Dealer implements DeckInterface {

    private Deck myDeck;

    public Dealer(Deck deck) {
        this.myDeck = deck;
    }

    @Override
    public void shuffle() {
        myDeck.shuffle();
    }

    @Override
    public Card getCard() {
        return myDeck.getCard();
    }

    @Override
    public Card getCard(Card requestedCard) throws CardNotFoundException {
        return myDeck.getCard(requestedCard);
    }
}
