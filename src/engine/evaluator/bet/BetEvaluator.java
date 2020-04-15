package engine.evaluator.bet;

import engine.bet.Bet;
import engine.evaluator.handevaluator.HandEvaluator;
import engine.hand.PlayerHand;
import engine.hand.HandOutcome;

import java.util.ArrayList;
import java.util.List;

public class BetEvaluator implements BetEvaluatorInterface {

    private final HandEvaluator myHandEvaluator;

    public BetEvaluator(HandEvaluator handEvaluator) {
        this.myHandEvaluator = handEvaluator;
    }

    // TODO - finish implementing evaluate bets
    @Override
    public void evaluateBets(List<Bet> bets) {
        List<Bet> winnerCandidate = new ArrayList<>(bets);
        boolean foundWinner = false;
        while (!foundWinner) {
            for (int index = 0; index < bets.size(); index ++) {
                Bet myBet = bets.get(index);
                boolean myWinner = true;
                for (Bet otherBet: bets) {
                    if (notSameBet(myBet, otherBet)) {
                        PlayerHand myHand = myBet.getHand();
                        PlayerHand otherHand = otherBet.getHand();
                        int myCompare = this.myHandEvaluator.compare(myHand, otherHand);
                        if (myCompare > 0)
                            removeFromWinners(winnerCandidate, otherBet);
                        else if (myCompare < 0)
                            removeFromWinners(winnerCandidate, myBet);
                    }
                }
            }
        }
    }

    private void removeFromWinners(List<Bet> winnerCandidate, Bet bet) {
        try {
            winnerCandidate.remove(bet);
        } catch (Exception e) {
            System.out.println("already removed bet from list");
        }
    }

    @Override
    public void updateWagers(List<Bet> bets) {

    }

    // TODO - algorithm to handle larger groups
    @Override
    public void evaluateHands(PlayerHand h1, PlayerHand h2) {
        int compare = this.myHandEvaluator.compare(h1, h2);
        if (compare > 0)
            compareOverZero(h1, h2);
        else if (compare < 0)
            compareUnderZero(h1, h2);
        else
            compareEqualsZero(h1, h2);
    }

    private boolean notSameBet(Bet b1, Bet b2) {
        return !(b1.getID() == b2.getID());
    }

    private void compareOverZero(PlayerHand h1, PlayerHand h2) {
        assignOutcome(h1, HandOutcome.WIN);
        assignOutcome(h2, HandOutcome.LOSS);
    }

    private void compareUnderZero(PlayerHand h1, PlayerHand h2) {
        assignOutcome(h1, HandOutcome.LOSS);
        assignOutcome(h2, HandOutcome.WIN);
    }

    private void compareEqualsZero(PlayerHand h1, PlayerHand h2) {
        assignOutcome(h1, HandOutcome.TIE);
        assignOutcome(h2, HandOutcome.TIE);
    }

    private void assignOutcome(PlayerHand playerHand, HandOutcome outcome) {
        playerHand.setOutcome(outcome);
    }

}
