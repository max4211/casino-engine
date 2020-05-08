package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Conrete extension of hand objects
 * Needs parameters to determine if hand evalution
 * Default configured if validation breaks
 * Could have straights of every other card
 * @author Max Smith
 */
public class Straight extends Hand {

    private static final double DEFAULT_DIFFERENCE = 1;

    public Straight(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    /**
     * Evaluates if all cards in in ascending order
     * @param cardsInHand total cards required to makeup a hand
     * @return true if the cards in the hand go in a specific order
     */
    @Override
    public boolean evaluate(int cardsInHand) {
        PriorityQueue<Card> pq = sortCards(new ValueComparator());
        double difference = parseDifference();
        double lastValue = pq.poll().getValue();
        while (!(pq.isEmpty())) {
            double nextValue = pq.poll().getValue();
            if (!(Math.abs(nextValue - lastValue) == difference))
                return false;
            lastValue = nextValue;
        }
        return this.myCards.size() == cardsInHand;
    }

    private double parseDifference() {
        try {
            return this.myParams.get(ZERO);
        } catch (Exception e) {
            return DEFAULT_DIFFERENCE;
        }
    }

    /**
     * Gets power of the hand
     * @return highest card in the hand
     */
    @Override
    public double getPower() {
        return getHighestCard();
    }

}
