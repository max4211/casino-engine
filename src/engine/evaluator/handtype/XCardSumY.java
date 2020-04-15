package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

public class XCardSumY extends Hand {

    public XCardSumY(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate() {
        int count = 0;
        for (Card c: this.myCards) {
            count += c.getValue();
        }
        return (this.myCards.size() == this.myParams.get(0)) && (count == this.myParams.get(1));
    }
}
