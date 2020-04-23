package engine.pot;

import engine.bet.Bet;
import engine.hand.HandOutcome;

import java.util.List;

public class Pot implements PotInterface {

    private double myPot;

    public Pot() {
        this.myPot = 0;
    }

    public Pot(double wager) {
        this.myPot = wager;
    }

    @Override
    public void add(double wager) {
        this.myPot += wager;
    }

    @Override
    public double getPot() {
        return this.myPot;
    }

    @Override
    public void distributePot(List<Bet> bets) {
        checkSingleWinner(bets);
        checkForTies(bets);
    }

    @Override
    public void reset() {
        this.myPot = 0;
    }

    private void checkForTies(List<Bet> bets) {
        int ties = countTies(bets);
        if (ties > 0) {
            double split = this.myPot / ties;
            for (Bet b: bets) {
                if (b.getHand().getOutcome().equals(HandOutcome.TIE))
                    b.setProfit(split);
            }
        }

    }

    private int countTies(List<Bet> bets) {
        int count = 0;
        for (Bet b: bets) {
            if (b.getHand().getOutcome().equals(HandOutcome.TIE))
                count ++;
        }
        return count;
    }

    private void checkSingleWinner(List<Bet> bets) {
        for (Bet b: bets) {
            if (b.getHand().getOutcome().equals(HandOutcome.WIN))
                b.setProfit(this.myPot - b.getWager());
        }
    }


}
