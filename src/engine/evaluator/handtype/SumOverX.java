package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

public class SumOverX extends Hand {

    private static final double DEFAULT_VALUE = 21;

    public SumOverX(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate(int cardsInHand) {
        int count = 0;
        for (Card c: this.myCards) {
            count += c.getValue();
        }
        double over = parseOver();
        return count > over;
    }

    @Override
    public double getPower() {
        return sumCards();
    }

    private double parseOver() {
        try {
            return this.myParams.get(ZERO);
        } catch (Exception e) {
            return DEFAULT_VALUE;
        }
    }
}
