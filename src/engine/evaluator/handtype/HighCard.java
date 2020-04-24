package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

public class HighCard extends Hand {

    public HighCard(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate(int cardsInHand) {
        return true;
    }

    @Override
    public double getPower() {
        return getHighestCard();
    }
}
