package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;
import java.util.Map;

public class XCardPair extends Hand {

    public XCardPair(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate() {
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

    @Override
    public double getPower() {
        return this.myPower;
    }
}
