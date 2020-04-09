package engine.bet;

import engine.dealer.Card;
import engine.hand.Hand;

public class Bet implements BetInterface {

    private int myID;
    private Hand myHand;
    private double myWager;

    public Bet(double wager) {
        this.myWager = wager;
    }

    public Bet(double wager, Hand hand) {
        this.myWager = wager;
        this.myHand = hand;
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
}
