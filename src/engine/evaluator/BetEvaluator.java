package engine.evaluator;

import engine.bet.Bet;
import engine.hand.Hand;
import engine.hand.HandOutcome;

import java.util.Collection;
import java.util.List;

public class BetEvaluator implements BetEvaluatorInterface {

    private final HandEvaluator myHandEvaluator;

    public BetEvaluator(HandEvaluator handEvaluator) {
        this.myHandEvaluator = handEvaluator;
    }

    @Override
    public void evaluateBets(List<Bet> bets) {

    }

    @Override
    public void updateWagers(List<Bet> bets) {

    }

    // TODO - algorithm to handle larger groups
    @Override
    public void evaluateHands(Hand h1, Hand h2) {
        int compare = this.myHandEvaluator.compare(h1, h2);
        if (compare > 0) {
            assignOutcome(h1, HandOutcome.WIN);
            assignOutcome(h2, HandOutcome.LOSS);
        } else if (compare < 0) {
            assignOutcome(h1, HandOutcome.LOSS);
            assignOutcome(h2, HandOutcome.WIN);
        } else {
            assignOutcome(h1, HandOutcome.TIE);
            assignOutcome(h2, HandOutcome.TIE);
        }
    }

    private void assignOutcome(Hand hand, HandOutcome outcome) {
        hand.setOutcome(outcome);
    }

}
