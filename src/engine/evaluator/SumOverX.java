package engine.evaluator;

import engine.dealer.Card;

import java.util.List;

public class SumOverX extends Hand {

    public SumOverX(List<Card> cards, List<String> params) {
        super(cards, params);
    }

    @Override
    public boolean evaluate() {

        return false;
    }
}
