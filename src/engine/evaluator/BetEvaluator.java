package engine.evaluator;

import engine.bet.Bet;
import engine.hand.PlayerPlayerHand;
import engine.hand.HandOutcome;

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
    public void evaluateHands(PlayerPlayerHand h1, PlayerPlayerHand h2) {
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

    private void assignOutcome(PlayerPlayerHand playerHand, HandOutcome outcome) {
        playerHand.setOutcome(outcome);
    }

}
