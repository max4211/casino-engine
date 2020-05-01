package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

/**
 * Conrete extension of hand objects
 * Needs parameters to determine if it is over a value
 * Default configured if validation breaks
 * @author Max Smith
 */
public class SumUnderX extends Hand {

    private static final double DEFAULT_UNDER = 22;

    public SumUnderX(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    /**
     * Sums cards in hand, evalutes if it is under the target value
     * @param cardsInHand total cards needed to makeup a hand
     * @return true if the sum is under the threshold
     */
    @Override
    public boolean evaluate(int cardsInHand) {
        int count = 0;
        for (Card c: this.myCards) {
            count += c.getValue();
        }
        double under = parseUnder();
        return count < under;
    }

    /**
     * Gets power of the hand
     * @return sum hand
     */
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
