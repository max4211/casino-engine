package engine.adversary;

import UI.Utilities.CardTriplet;
import Utility.Generator;
import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class Adversary implements AdversaryInterface {

    private PlayerHand myPlayerHand;
    private final double myMinSum;

    public Adversary(double min) {
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

    @Override
    public void playHand(Consumer<Integer> showCard, Consumer<CardTriplet> addCard, Supplier<Card> getCard) {
        showMyCards(showCard);
        while (this.wantsCards()) {
            Card c = getCard.get();
            this.acceptCard(c);
            CardTriplet ct = Generator.createCardTriplet(c);
            addCard.accept(ct);
            showCard.accept(c.getID());
        }
    }

    private void showMyCards(Consumer<Integer> showCard) {
        for (Card c: this.myPlayerHand.getCards())
            showCard.accept(c.getID());
    }

    public boolean wantsCards() {
        int sum = this.handSum();
        return (sum < this.myMinSum);
    }
}
