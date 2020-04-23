package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;
import java.util.PriorityQueue;

public class Flush extends Hand {

    public Flush(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate() {
        PriorityQueue<Card> pq = sortCards(new SuitComparator());
        String suit = pq.peek().getSuit();
        while (!(pq.isEmpty())) {
            if (!suit.equals(pq.poll().getSuit()))
                return false;
        }
        return true;
    }

    @Override
    public double getPower() {
        return getHighestCard();
    }
}
