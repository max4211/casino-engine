package engine.evaluator.bet;

import engine.bet.Bet;
import engine.evaluator.handevaluator.HandEvaluator;
import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;
import engine.hand.HandOutcome;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class BetEvaluator implements BetEvaluatorInterface {

    private final HandEvaluator myHandEvaluator;

    public BetEvaluator(HandEvaluator handEvaluator) {
        this.myHandEvaluator = handEvaluator;
    }

    @Override
    public void evaluateBets(@NotNull List<Bet> bets) {
        PriorityQueue<Bet> pq = new PriorityQueue<>(bets.size(), new BetComparator());
        pq.addAll(bets);
        assignSortedBets(pq);
    }

    private void assignSortedBets(PriorityQueue<Bet> pq) {
        Bet first = pq.poll();
        List<Bet> winners = new ArrayList<Bet>(List.of(first));
        while (traversePQ(pq, first)) {
            winners.add(pq.poll());
        }
        assignWinningBets(winners);
        assignLosingBets(pq);
    }

    private boolean traversePQ(PriorityQueue<Bet> pq, Bet first) {
        BetComparator bc = new BetComparator();
        boolean notEmpty = !pq.isEmpty();
        boolean stillEqual = bc.compare(first, pq.peek()) == 0;
        return notEmpty && stillEqual;
    }

    private void assignWinningBets(List<Bet> winners) {
        HandOutcome outcome = HandOutcome.WIN;
        if (winners.size() > 1)
            outcome = HandOutcome.TIE;
        for (Bet b: winners)
            assignOutcome(b.getHand(), outcome);
    }

    private void assignLosingBets(PriorityQueue<Bet> pq) {
        while (!(pq.isEmpty()))
            assignLoser(pq.poll().getHand());
    }

    class BetComparator implements Comparator<Bet>{

        @Override
        public int compare(Bet b1, Bet b2) {
            ClassifiedHand c1 = b1.getHand().getClassification();
            ClassifiedHand c2 = b2.getHand().getClassification();
            int rankDiff = c1.getRank() - c2.getRank();
            double powDiff = c1.getPower() - c2.getPower();

            if (rankDiff != 0)
                return rankDiff;
            else
                return doubleToInt(powDiff);
        }

        private int doubleToInt(double d) {
            if (d < 0)
                return -1;
            else if (d > 0)
                return 1;
            else
                return 0;
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
        assignWinner(h1);
        assignLoser(h2);
    }

    private void compareUnderZero(PlayerHand h1, PlayerHand h2) {
        assignLoser(h1);
        assignWinner(h2);
    }

    private void compareEqualsZero(PlayerHand h1, PlayerHand h2) {
        assignTie(h1);
        assignTie(h2);
    }

    private void assignLoser(PlayerHand h) {
        assignOutcome(h, HandOutcome.LOSS);
    }

    private void assignWinner(PlayerHand h) {
        assignOutcome(h, HandOutcome.WIN);
    }

    private void assignTie(PlayerHand h) {
        assignOutcome(h, HandOutcome.TIE);
    }

    private void assignOutcome(PlayerHand playerHand, HandOutcome outcome) {
        playerHand.setOutcome(outcome);
    }

}
