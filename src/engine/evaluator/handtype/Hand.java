package engine.evaluator.handtype;

import engine.bet.Bet;
import engine.dealer.Card;
import engine.evaluator.handevaluator.HandEvaluator;

import java.util.*;

public abstract class Hand implements HandInterface {

    protected static final int ZERO = 0;
    protected static final int ONE = 1;
    protected static final int TWO = 2;

    protected final List<Card> myCards;
    protected final List<Double> myParams;

    public Hand(List<Card> cards, List<Double> params) {
        this.myCards = cards;
        this.myParams = params;
    }

    protected Map<Double, Integer> countOccurences() {
        Map<Double, Integer> map = new HashMap<>();
        for (Card c: this.myCards) {
            double value = c.getValue();
            map.putIfAbsent(value, ZERO);
            map.put(value, map.get(value) + ONE);
        }
        return map;
    }

    protected PriorityQueue<Card> sortCards(Comparator comparator) {
        PriorityQueue<Card> pq = new PriorityQueue<>(this.myCards.size(), comparator);
        pq.addAll(this.myCards);
        return pq;
    }

    protected class SuitComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            return c1.getSuit().compareTo(c2.getSuit());
        }
    }

    protected class ValueComparator implements Comparator<Card> {
        @Override
        public int compare(Card c1, Card c2) {
            double diff = c1.getValue() - c2.getValue();
            if (diff > 0)
                return 1;
            else if (diff < 0)
                return -1;
            else
                return 0;
        }
    }

}
