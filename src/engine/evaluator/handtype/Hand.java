package engine.evaluator.handtype;

import engine.dealer.Card;
import engine.evaluator.handevaluator.HandInterface;

import java.util.List;

public abstract class Hand implements HandInterface {

    protected final List<Card> myCards;
    protected final List<Double> myParams;

    public Hand(List<Card> cards, List<Double> params) {
        this.myCards = cards;
        this.myParams = params;
    }

}
