package engine.hand;

import engine.dealer.Card;

import java.util.ArrayList;
import java.util.List;

public class Hand implements HandInterface {

    private List<Card> myCards;

    public Hand(List<Card> cards) {
        this.myCards = new ArrayList<Card>();
        this.myCards.addAll(cards);
    }

    public Hand(Card card) {
        this.myCards = new ArrayList<Card>();
        this.myCards.add(card);
    }

    @Override
    public void acceptCard(Card c) {
        this.myCards.add(c);
    }
}
