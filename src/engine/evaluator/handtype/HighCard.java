package engine.evaluator.handtype;

import engine.dealer.Card;

import java.util.List;

/**
 * Conrete extension of hand objects
 */
public class HighCard extends Hand {

    public HighCard(List<Card> cards, List<Double> params) {
        super(cards, params);
    }

    /**
     * All hands are always high card
     * @param cardsInHand total cards in the hand
     * @return always returns true
     */
    @Override
    public boolean evaluate(int cardsInHand) {
        return true;
    }

    /**
     * Gets power of the hand
     * @return highest card in the hand
     */
    @Override
    public double getPower() {
        return getHighestCard();
    }
}
