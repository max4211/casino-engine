package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

/**
 * Conrete extension of hand objects
 * Needs parameters to determine if it is over a value
 */
public class XCardSumY extends Hand {

    public XCardSumY(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    /**
     * Evalutes if the sum is equal to a value, and if there are a certain amount of cards
     * @param cardsInHand total cards needed to makeup  a hand
     * @return true if the parameters are achieved
     */
    @Override
    public boolean evaluate(int cardsInHand) {
        double count = 0;
        for (Card c: this.myCards) {
            count += c.getValue();
        }
        return (cardCount()) && (sumEquivalence(count));
    }

    private boolean sumEquivalence(double count) {
        return count == this.myParams.get(1);
    }

    private boolean cardCount() {
        return this.myCards.size() == this.myParams.get(0);
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
