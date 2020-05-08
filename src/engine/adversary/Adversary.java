package engine.adversary;

import UI.Utilities.CardTriplet;
import Utility.Generator;
import engine.dealer.Card;
import engine.hand.PlayerHand;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Encapsulates a player hand and a strategy (play to a certain sum is reached)
 * @author Max Smith
 */
public class Adversary implements AdversaryInterface {

    private PlayerHand myPlayerHand;
    private final double myMinSum;

    /**
     * Consrutor for adversary with the minimum sum they will play until
     * @param min is the smallest hand the advesary is satisfied with
     */
    public Adversary(double min) {
        this.myPlayerHand = new PlayerHand();
        this.myMinSum = min;
    }

    /**
     * Accept cards from the controller, adds to hand
     * @param c a specific card (from dealer) to accept
     */
    @Override
    public void acceptCard(Card c) {
        this.myPlayerHand.acceptCard(c);
    }

    /**
     * Get a single card from the adversary
     * @return a single card
     */
    @Override
    public Card getCard() {
        return this.myPlayerHand.getCards().get(0);
    }

    /**
     * Fetch the hand internal to the adversary
     * @return the PlayerHand encapsulated in the adversary
     */
    @Override
    public PlayerHand getHand() {
        return this.myPlayerHand;
    }

    /**
     * Called by controller in end game to increase adversary cards until a point
     * @return the sum of all cards in adversary hand
     */
    @Override
    public int handSum() {
        int total = 0;
        for (Card c: this.myPlayerHand.getCards()) {
            total += c.getValue();
        }
        return total;
    }

    /**
     * Gives the adversary method calls it needs to process a hand
     * @param showCard is a consumer to show card in the view
     * @param addCard consumer to add card to the view
     * @param getCard supplier to get card from dealer
     */
    @Override
    public void playHand(Consumer<Integer> showCard, Consumer<CardTriplet> addCard, Supplier<Card> getCard) {
        showMyCards(showCard);
        while (this.wantsCards()) {
            Card c = getCard.get();
            this.myPlayerHand.acceptCard(c);
            CardTriplet ct = Generator.createCardTriplet(c);
            addCard.accept(ct);
            showCard.accept(c.getID());
        }
    }

    private void showMyCards(Consumer<Integer> showCard) {
        for (Card c: this.myPlayerHand.getCards())
            showCard.accept(c.getID());
    }

    /**
     * Called by adversary (should be private but in code freeze)
     * Determined that adversary still wants cards in hand
     * @return boolean if sum is less than min sum
     */
    public boolean wantsCards() {
        int sum = this.handSum();
        return (sum <= this.myMinSum);
    }
}
