package engine.hand;

import engine.dealer.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Encapsulates a list of cards, flag for loser, classificationo bject, and HandOutcome
 */
public class PlayerHand implements PlayerHandInterface {

    private List<Card> myCards;
    private ClassifiedHand myClassification = new ClassifiedHand("Default", Integer.MAX_VALUE, Integer.MIN_VALUE);
    private boolean isLoser = false;
    private HandOutcome myOutcome = HandOutcome.TIE;

    /**
     * Construct a hand from a collection of cards
     * @param cards cards to add to list of myCards
     */
    public PlayerHand(List<Card> cards) {
        this.myCards = new ArrayList<Card>();
        this.myCards.addAll(cards);
    }

    /**
     * Construct a player hand from a single card
     * @param card single card to add to list
     */
    public PlayerHand(Card card) {
        this.myCards = new ArrayList<Card>();
        this.myCards.add(card);
    }

    /**
     * Construct a player hand with nothing inside
     */
    public PlayerHand() {
        this.myCards = new ArrayList<Card>();
    }

    /**
     * Method called by the Dealer to enable hand to accept card
     * @param c is the card to be accepted
     */
    @Override
    public void acceptCard(Card c) {
        this.myCards.add(c);
    }

    /**
     * Called by evaluators and GUI to determine cards inside hand
     * @return all cards in the hand
     */
    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.myCards);
    }

    /**
     * Called by the HandClssifier to add a classification tag to a hand
     * @param type is type of hand
     */
    @Override
    public void classifyHand(ClassifiedHand type) {
        this.myClassification = type;
    }

    /**
     * toggles loser when a losing hand is encountered
     * @param state set the state of losing (always false)
     */
    @Override
    public void setLoser(boolean state) {
        this.isLoser = state;
        this.myOutcome = HandOutcome.LOSS;
    }

    /**
     * Set outcome of hand inside of bet evaluator
     * @param outcome is the result of the hand
     */
    @Override
    public void setOutcome(HandOutcome outcome) {
        this.myOutcome = outcome;
    }

    /**
     * Return the hand outcome for bet evaluator to help update bets
     * @return hand outcome
     */
    @Override
    public HandOutcome getOutcome() {
        return this.myOutcome;
    }

    /**
     * Called by the Bet to determine what type of hand it holds
     * @return the classification (String)
     */
    @Override
    public ClassifiedHand getClassification() {
        return this.myClassification;
    }

    /**
     * Checks if the hand is a loser for garbage collection
     * @return loser value
     */
    @Override
    public boolean isLoser() {
        return this.isLoser;
    }

}
