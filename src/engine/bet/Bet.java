package engine.bet;

import engine.hand.Hand;

public class Bet {

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

}
