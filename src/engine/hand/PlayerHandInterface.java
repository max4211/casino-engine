package engine.hand;

import engine.dealer.Card;

import java.util.List;

public interface PlayerHandInterface {

    /**
     * Method called by the Dealer to enable hand to accept card
     * @param c is the card to be accepted
     */
    void acceptCard(Card c);

    /**
     * Called by evaluators and GUI to determine cards inside hand
     * @return all cards in the hand
     */
    List<Card> getCards();

    /**
     * Called by the HandClssifier to add a classification tag to a hand
     * @param type is type of hand
     */
    void classifyHand(ClassifiedHand type);

    /**
     * Called by the Bet to determine what type of hand it holds
     * @return the classification (String)
     */
    ClassifiedHand getClassification();

    /**
     * Checks if the hand is a loser for garbage collection
     * @return loser value
     */
    boolean isLoser();

    /**
     * toggles loser when a losing hand is encountered
     * @param state set the state of losing (always false)
     */
    void setLoser(boolean state);

    /**
     * Set outcome of hand inside of bet evaluator
     * @param outcome is the result of the hand
     */
    void setOutcome(HandOutcome outcome);

    /**
     * Return the hand outcome for bet evaluator to help update bets
     * @return hand outcome
     */
    HandOutcome getOutcome();

}
