package engine.bet;

import Utility.HashNoise;
import engine.dealer.Card;
import engine.hand.PlayerHand;

public class Bet implements BetInterface {

    private int myID;
    private PlayerHand myPlayerHand;
    private double myWager;
    private boolean myRoundActive;
    private boolean myGameActive;
    private double myMultiplier = 2.0;

    public Bet(double wager) {
        this.myPlayerHand = new PlayerHand();
        this.myWager = wager;
        this.myID = HashNoise.addNoise(this);
        this.myRoundActive = true;
        this.myGameActive = true;
    }

    public Bet(double wager, PlayerHand playerHand) {
        this.myPlayerHand = playerHand;
        this.myWager = wager;
        this.myID = HashNoise.addNoise(this);
        this.myRoundActive = true;
        this.myGameActive = true;
    }

    @Override
    public PlayerHand getHand() {
        return this.myPlayerHand;
    }

    @Override
    public double getWager() {
        return this.myWager;
    }

    @Override
    public void acceptCard(Card c) {
        this.myPlayerHand.acceptCard(c);
    }

    @Override
    public int getID() {
        return this.myID;
    }

    @Override
    public boolean isGameActive() {
        return this.myGameActive;
    }

    @Override
    public void setGameActive(boolean state) {
        this.myGameActive = state;
    }

    @Override
    public boolean isRoundActive() {
        return this.myRoundActive;
    }

    @Override
    public void setRoundActive(boolean state) {
        this.myRoundActive = state;
    }

    @Override
    public void setWager(double wager) {
        this.myWager = wager;
    }

    @Override
    public double getPayoff() {
        return this.myWager * this.myMultiplier;
    }


}
