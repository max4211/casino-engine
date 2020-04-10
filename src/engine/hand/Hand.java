package engine.hand;

import engine.dealer.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Hand implements HandInterface {

    private List<Card> myCards;
    private ClassifiedHand myClassification;
    private boolean isLoser = false;

    public Hand(List<Card> cards) {
        this.myCards = new ArrayList<Card>();
        this.myCards.addAll(cards);
    }

    public Hand(Card card) {
        this.myCards = new ArrayList<Card>();
        this.myCards.add(card);
    }

    public Hand() {
        this.myCards = new ArrayList<Card>();
    }

    @Override
    public void acceptCard(Card c) {
        this.myCards.add(c);
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.myCards);
    }

    @Override
    public void classifyHand(ClassifiedHand type, boolean loser) {
        this.myClassification = type;
        this.isLoser = loser;
    }

    @Override
    public ClassifiedHand getClassification() {
        return this.myClassification;
    }

    @Override
    public boolean isLoser() {
        return this.isLoser;
    }
}
