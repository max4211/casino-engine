package engine.evaluator;

import engine.bet.Bet;
import engine.hand.Hand;

import java.util.Collection;

public class BetEvaluator implements BetEvaluatorInterface {

    private final HandEvaluator myHandEvaluator;

    public BetEvaluator(HandEvaluator handEvaluator) {
        this.myHandEvaluator = handEvaluator;
    }

    @Override
    public void evaluateBets(Collection<Bet> bets) {

    }

    @Override
    public void updateWagers(Collection<Bet> bets) {

    }

    // TODO - algorithm to handle larger groups
    @Override
    public void evaluateHands(Collection<Hand> hands) {

    }
}
