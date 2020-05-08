package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;
import java.util.PriorityQueue;

/**
 * Conrete extension of hand objects
 * @author Max Smith
 */
public class Flush extends Hand {

    public Flush(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    /**
     * Evalute that all cards are the same suit
     * @param cardsInHand total cards that are needed for a hand
     * @return true if all suits are the same
     */
    @Override
    public boolean evaluate(int cardsInHand) {
        PriorityQueue<Card> pq = sortCards(new SuitComparator());
        String suit = pq.peek().getSuit();
        while (!(pq.isEmpty())) {
            if (!suit.equals(pq.poll().getSuit()))
                return false;
        }
        return this.myCards.size() == cardsInHand;
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
