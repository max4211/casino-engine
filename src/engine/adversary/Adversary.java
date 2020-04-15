package engine.adversary;

import engine.dealer.Card;
import engine.hand.PlayerHand;

public class Adversary implements AdversaryInterface {

    private PlayerHand myPlayerHand;
    private final int myMinSum;

    public Adversary(int min) {
        this.myPlayerHand = new PlayerHand();
        this.myMinSum = min;
    }

    @Override
    public void acceptCard(Card c) {
        this.myPlayerHand.acceptCard(c);
    }

    @Override
    public Card getCard() {
        return this.myPlayerHand.getCards().get(0);
    }

    @Override
    public PlayerHand getHand() {
        return this.myPlayerHand;
    }

    @Override
    public int handSum() {
        int total = 0;
        for (Card c: this.myPlayerHand.getCards()) {
            total += c.getValue();
        }
        return total;
    }

    public boolean wantsCards() {
        int sum = this.handSum();
        return (sum < this.myMinSum);
    }
}
