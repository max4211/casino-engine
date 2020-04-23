package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

public class SumOverX extends Hand {

    public SumOverX(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate() {
        int count = 0;
        for (Card c: this.myCards) {
            count += c.getValue();
        }
        return count > this.myParams.get(0);
    }

    @Override
    public double getPower() {
        return sumCards();
    }
}
