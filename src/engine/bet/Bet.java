package engine.bet;

import engine.dealer.Card;
import engine.hand.Hand;

public class Bet implements BetInterface {

    private int myID;
    private Hand myHand;
    private double myWager;
    private boolean handWon;
    private String myHandClassifier;

    public Bet(double wager) {
        this.myHand = new Hand();
        this.myWager = wager;
        this.myID = this.hashCode();
    }

    public Bet(double wager, Hand hand) {
        this.myWager = wager;
        this.myHand = hand;
        this.myID = this.hashCode();
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
        this.myHand.acceptCard(c);
    }

    @Override
    public int getID() {
        return this.myID;
    }
}
