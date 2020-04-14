package engine.hand;

import engine.dealer.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlayerPlayerHand implements PlayerHandInterface {

    private List<Card> myCards;
    private ClassifiedHand myClassification;
    // TODO refactor loser state to enumerated type of outcome
    private boolean isLoser = false;
    private HandOutcome myOutcome = HandOutcome.WIN;

    public PlayerPlayerHand(List<Card> cards) {
        this.myCards = new ArrayList<Card>();
        this.myCards.addAll(cards);
    }

    public PlayerPlayerHand(Card card) {
        this.myCards = new ArrayList<Card>();
        this.myCards.add(card);
    }

    public PlayerPlayerHand() {
        this.myCards = new ArrayList<Card>();
    }

    @Override
    public void acceptCard(Card c) {
        this.myCards.add(c);
    }

    @Override
    public List<Card> getCards() {
        return Collections.unmodifiableList(this.myCards);
    }

    @Override
    public void classifyHand(ClassifiedHand type) {
        this.myClassification = type;
    }

    @Override
    public void setLoser(boolean state) {
        this.isLoser = state;
        this.myOutcome = HandOutcome.LOSS;
    }

    @Override
    public void setOutcome(HandOutcome outcome) {
        this.myOutcome = outcome;
    }

    @Override
    public HandOutcome getOutcome() {
        return this.myOutcome;
    }

    @Override
    public ClassifiedHand getClassification() {
        return this.myClassification;
    }

    @Override
    public boolean isLoser() {
        return this.isLoser;
    }

}
