package engine.adversary;

import engine.dealer.Card;
import engine.hand.Hand;

public class Adversary implements AdversaryInterface {

    private Hand myHand;

    public Adversary() {
        this.myHand = new Hand();
    }

    @Override
    public void acceptCard(Card c) {
        this.myHand.acceptCard(c);
    }

    @Override
    public Hand getHand() {
        return this.myHand;
    }
}
