package engine.bet;

import engine.dealer.Card;
import engine.hand.Hand;

public class Bet implements BetInterface {

    private int myID;
    private Hand myHand;
    private double myWager;
    private boolean myActive;
    private boolean needsCard;

    public Bet(double wager) {
        this.myHand = new Hand();
        this.myWager = wager;
        this.myID = this.hashCode();
        this.myActive = true;
        this.needsCard = false;
    }

    public Bet(double wager, Hand hand) {
        this.myHand = hand;
        this.myWager = wager;
        this.myID = this.hashCode();
        this.myActive = true;
        this.needsCard = false;
    }

    @Override
    public Hand getHand() {
        return this.myHand;
    }

    @Override
    public double getWager() {
        return this.myWager;
    }

    @Override
    public void acceptCard(Card c) {
        System.out.printf("bet accepting card: %s\n", c);
        this.myHand.acceptCard(c);
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
    public boolean needsCard() {
        return this.needsCard;
    }

    @Override
    public void setActive(boolean state) {
        this.myActive = state;
    }

    @Override
    public void setNeedsCard(boolean state) {
        this.needsCard = state;
    }


}
