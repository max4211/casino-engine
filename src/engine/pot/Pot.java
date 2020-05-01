package engine.pot;

import engine.bet.Bet;
import engine.hand.HandOutcome;

import java.util.List;

/**
 * Encapsulates a double representing the current pot
 */
public class Pot implements PotInterface {

    private double myPot;

    /**
     * Construct an empty pot
     */
    public Pot() {
        this.myPot = 0;
    }

    /**
     * Construct a pot from a starting wager
     * @param wager intiial amount inside of the pot
     */
    public Pot(double wager) {
        this.myPot = wager;
    }

    /**
     * Update pot to reflect wager
     * @param wager is the wager to be added to the pots
     */
    @Override
    public void add(double wager) {
        this.myPot += wager;
    }

    /**
     * Returns the total wager amount in the pot
     * @return total amount of monies in the pot
     */
    @Override
    public double getPot() {
        return this.myPot;
    }

    /**
     * At the end of a round with a pot, the pot is called to distrbute funds to bets
     * @param bets bet objects to distribute funding to
     */
    @Override
    public void distributePot(List<Bet> bets) {
        checkSingleWinner(bets);
        checkForTies(bets);
    }

    /**
     * Reset pot to zero
     */
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
