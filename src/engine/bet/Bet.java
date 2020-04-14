package engine.bet;

import Utility.HashNoise;
import engine.dealer.Card;
import engine.hand.PlayerHand;

public class Bet implements BetInterface {

    private int myID;
    private PlayerHand myPlayerHand;
    private double myWager;
    private boolean myActive;

    public Bet(double wager) {
        this.myPlayerHand = new PlayerHand();
        this.myWager = wager;
        this.myID = HashNoise.addNoise(this);
        this.myActive = true;
    }

    public Bet(double wager, PlayerHand playerHand) {
        this.myPlayerHand = playerHand;
        this.myWager = wager;
        this.myID = HashNoise.addNoise(this);
        this.myActive = true;
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
        System.out.printf("bet accepting card: %s\n", c);
        this.myPlayerHand.acceptCard(c);
    }

    @Override
    public int getID() {
        return this.myID;
    }

    @Override
    public boolean isActive() {
        return this.myActive;
    }

    @Override
    public void setActive(boolean state) {
        this.myActive = state;
    }

    @Override
    public void setWager(double wager) {
        this.myWager = wager;
    }


}
