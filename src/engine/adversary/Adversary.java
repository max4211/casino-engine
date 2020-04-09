package engine.adversary;

import engine.hand.Hand;

public class Adversary implements AdversaryInterface {

    private Hand myHand;

    public Adversary() {
        this.myHand = new Hand();
    }

    @Override
    public Hand getHand() {
        return this.myHand;
    }
}
