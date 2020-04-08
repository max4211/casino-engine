package engine.evaluator;

import java.util.Collection;

public class BetEvaluator implements BetEvaluatorInterface {

    private final HandEvaluator myHandEvaluator;

    public BetEvaluator(HandEvaluator handEvaluator) {
        this.myHandEvaluator = handEvaluator;

    }

    @Override
    public void evaluateBets(Collection Bets) {

    }

    @Override
    public void updateWagers(Collection Bets) {

    }
}
