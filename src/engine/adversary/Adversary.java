package engine.adversary;

import engine.dealer.Card;
import engine.hand.Hand;

public class Adversary implements AdversaryInterface {

    private Hand myHand;
    private final int myMinSum;

    public Adversary(int min) {
        this.myHand = new Hand();
        this.myMinSum = min;
    }

    @Override
    public void acceptCard(Card c) {
        this.myHand.acceptCard(c);
    }

    @Override
    public Card getCard() {
        return this.myHand.getCards().get(0);
    }

    @Override
    public Hand getHand() {
        return this.myHand;
    }

    @Override
    public int handSum() {
        int total = 0;
        for (Card c: this.myHand.getCards()) {
            total += c.getValue();
        }
        return total;
    }

    public boolean wantsCards() {
        int sum = this.handSum();
        return (sum < this.myMinSum);
    }
}
