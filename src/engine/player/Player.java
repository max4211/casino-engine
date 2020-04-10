package engine.player;

import engine.bet.Bet;
import engine.hand.HandOutcome;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface {

    private final int myID;
    private final String myName;
    private double myBankroll;
    private List<Bet> myActiveBets;

    public Player(String name, double bankroll) {
        this.myName = name;
        this.myBankroll = bankroll;
        this.myActiveBets = new ArrayList<Bet>();
        this.myID = this.hashCode();
    }

    @Override
    public String getName() {
        return this.myName;
    }

    @Override
    public double getBankroll() {
        return this.myBankroll;
    }

    @Override
    public int getID() {
        return this.myID;
    }

    @Override
    public List<Bet> getBets() {
        return this.myActiveBets;
    }

    @Override
    public int placeBet(double wager) {
        Bet bet = new Bet(wager);
        this.myActiveBets.add(bet);
        return bet.getID();
    }

    @Override
    public Bet getNextBet() {
        for (Bet b: this.myActiveBets) {
            if (b.isActive()) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void cashBets() {
        for (Bet b: this.myActiveBets) {
            HandOutcome outcome = b.getHand().getOutcome();
            if (outcome.equals(HandOutcome.WIN)) {
                this.myBankroll += b.getWager();
            } else if (outcome.equals(HandOutcome.LOSS)) {
                this.myBankroll -= b.getWager();
            } else if (outcome.equals(HandOutcome.TIE)) {
                ;
            }
        }
        this.myActiveBets = new ArrayList<>();
    }
}
