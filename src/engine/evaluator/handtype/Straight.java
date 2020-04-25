package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;
import java.util.PriorityQueue;

public class Straight extends Hand {

    private static final double DEFAULT_DIFFERENCE = 1;

    public Straight(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

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

    @Override
    public double getPower() {
        return getHighestCard();
    }

}
