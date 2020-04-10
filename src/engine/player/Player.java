package engine.player;

import engine.bet.Bet;
import engine.hand.HandOutcome;

import java.util.ArrayList;
import java.util.List;

public class Player implements PlayerInterface {

    private final int myID;
    private final String myName;
    private double myBankroll;
    private List<Bet> myBets;

    public Player(String name, double bankroll) {
        this.myName = name;
        this.myBankroll = bankroll;
        this.myBets = new ArrayList<Bet>();
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
        List<Bet> activeBets = new ArrayList<Bet>();
        for (Bet b: this.myBets) {
            if (!b.getHand().isLoser()) {
                activeBets.add(b);
            }
        }
        return this.myBets;
    }

    @Override
    public int placeBet(double wager) {
        Bet bet = new Bet(wager);
        this.myBets.add(bet);
        return bet.getID();
    }

    @Override
    public Bet getNextBet() {
        for (Bet b: this.myBets) {
            if (b.isActive()) {
                return b;
            }
        }
        return null;
    }

    @Override
    public void cashBets() {
        for (Bet b: this.myBets) {
            HandOutcome outcome = b.getHand().getOutcome();
            if (outcome.equals(HandOutcome.WIN)) {
                this.myBankroll += b.getWager();
            } else if (outcome.equals(HandOutcome.LOSS)) {
                this.myBankroll -= b.getWager();
            } else if (outcome.equals(HandOutcome.TIE)) {
                ;
            }
        }
        this.myBets = new ArrayList<>();
    }
}
