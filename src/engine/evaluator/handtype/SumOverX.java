package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

/**
 * Conrete extension of hand objects
 * Needs parameters to determine if it is over a value
 * Default configured if validation breaks
 * @author Max Smith
 */
public class SumOverX extends Hand {

    private static final double DEFAULT_VALUE = 21;

    public SumOverX(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    /**
     * Calculates sum, evalutes how it compares to arguments
     * @param cardsInHand total cards needed to make a hand
     * @return true if sumi s over parameter (or default vaule)
     */
    @Override
    public boolean evaluate(int cardsInHand) {
        int count = 0;
        for (Card c: this.myCards) {
            count += c.getValue();
        }
        double over = parseOver();
        return count > over;
    }

    /**
     * Gets power of the hand
     * @return sum of hand
     */
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
