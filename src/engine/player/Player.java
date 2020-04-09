package engine.player;

import engine.bet.Bet;

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
        this.myID = 0;
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
}
