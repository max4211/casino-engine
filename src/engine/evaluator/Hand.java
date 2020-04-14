package engine.evaluator;

import engine.dealer.Card;

import java.util.Collection;
import java.util.List;

public abstract class Hand implements HandInterface {

    private final List<Card> myCards;
    private final List<String> myParams;

    public Hand(List<Card> cards, List<String> params) {
        this.myCards = cards;
        this.myParams = params;
    }

}
