package engine.evaluator;

import engine.dealer.Card;

import java.util.Collection;
import java.util.List;

public abstract class Hand implements HandInterface {

    protected final List<Card> myCards;
    protected final List<Double> myParams;

    public Hand(List<Card> cards, List<Double> params) {
        this.myCards = cards;
        this.myParams = params;
    }

}
