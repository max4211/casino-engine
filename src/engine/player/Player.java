package engine.player;

import engine.bet.Bet;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private final String myName;
    private double myBankroll;
    private List<Bet> myActiveBets;

    public Player(String name, double bankroll) {
        this.myName = name;
        this.myBankroll = bankroll;
        this.myActiveBets = new ArrayList<Bet>();
    }
}
