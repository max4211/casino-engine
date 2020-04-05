package engine.dealer;

import java.util.List;

public class Deck implements DealerInterface {

    private List<Card> myCards;

    public Deck(List<Card> cards) {
        this.myCards = cards;
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
