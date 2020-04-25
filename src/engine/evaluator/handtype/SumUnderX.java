package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

public class SumUnderX extends Hand {

    private static final double DEFAULT_UNDER = 22;

    public SumUnderX(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate(int cardsInHand) {
        int count = 0;
        for (Card c: this.myCards) {
            count += c.getValue();
        }
        double under = parseUnder();
        return count < under;
    }

    @Override
    public double getPower() {
        return sumCards();
    }

    private double parseUnder() {
        try {
            return this.myParams.get(ZERO);
        } catch (Exception e) {
            return DEFAULT_UNDER;
        }
    }
}
