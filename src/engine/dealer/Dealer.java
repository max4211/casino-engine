package engine.dealer;

public class Dealer implements DealerInterface {

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
