package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;
import java.util.Map;

/**
 * Conrete extension of hand objects
 * Needs parameters to determine if it is over a value
 * POssible for arbirary number of card sof a kind (e.g. 2,3, 4, 5, 6, of a kind)
 * @author Max Smith
 */
public class XCardPair extends Hand {

    public XCardPair(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    /**
     * Evalute if the total cards in the pair are achieved
     * @param cardsInHand total cards needed to make a hand
     * @return true if the parameter count matches duplicate cards in hand
     */
    @Override
    public boolean evaluate(int cardsInHand) {
        Map<Double, Integer> map = countOccurences();
        double count = this.myParams.get(ZERO);
        for (double d: map.keySet()) {
            if (map.get(d) >= count) {
                this.myPower = d;
                return true;
            }
        }
        return false;
    }

    /**
     * Gets power of the hand
     * @return power (duplicated pair)
     */
    @Override
    public double getPower() {
        return this.myPower;
    }
}
