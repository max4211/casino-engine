package engine.evaluator.handclassifier;

import Utility.handbundle.HandBundle;
import engine.dealer.Card;
import engine.evaluator.handtype.Hand;
import engine.evaluator.handtype.HandFactory;
import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;
import exceptions.ReflectionException;

import java.util.*;

/**
 * Hand Classification module leveraged by the controller to continuously classify hands after actions
 * Uses bundles of hands (winners and losers) to classify hands, using reflection, and assign
 * ClassifiedHand object of hands appropriately
 * @author Max Smith
 */
public class HandClassifier implements HandClassifierInterface {

    private final List<HandBundle> myWinningHands;
    private final List<HandBundle> myLosingHands;
    private final HandFactory myHandFactory;
    private final int myCardsInHand;

    /**
     * Construct handCalssifier module from winners and losers and parameter for cards in hand
     * @param winners hand bundle representing winning hands
     * @param losers hand bundle representing losing hands
     * @param cardsInHand total cards in hand to evaluate (e.g. 5 in poker)
     */
    public HandClassifier(List<HandBundle> winners, List<HandBundle> losers, int cardsInHand) {
        this.myWinningHands = winners;
        this.myLosingHands = losers;
        this.myHandFactory = new HandFactory();
        this.myCardsInHand = cardsInHand;
    }

    /**
     * Method takes the Hand given to it and classifies it as the winningest hand that it can be based on the winning heirarcy given in the XML.
     * unclassfiedHand its then determinted classified form in an instance variable in the Hand object.
     *
     * @param cards is the Hand to classify and save its classification in itself
     */
    @Override
    public void classifyHand(List<Card> cards, PlayerHand h) {
        if (checkLosingHand(cards, h))
            return;
        else
            checkWinningHand(cards, h);
    }

    private boolean checkLosingHand(List<Card> cards, PlayerHand h) {
        for (HandBundle bundle: this.myLosingHands) {
            String s = bundle.getName();
            try {
                Hand hand = this.myHandFactory.createHand(bundle, cards);
                if (hand.evaluate(this.myCardsInHand)) {
                    h.setLoser(true);
                    h.classifyHand(new ClassifiedHand(bundle, Integer.MAX_VALUE, Integer.MIN_VALUE));
                    return true;
                }
            } catch (ReflectionException e) {
                this.myLosingHands.remove(bundle);
            }
        }
        return false;
    }

    private void checkWinningHand(List<Card> cards, PlayerHand h) {
        for (int rank = 0; rank < this.myWinningHands.size(); rank ++) {
            HandBundle bundle =  this.myWinningHands.get(rank);
            if (checkAllCombinations(cards, bundle, h, rank))
                return;
        }
    }

    // 7 choose 5 possible hands (21 cases, not too bad)
    private boolean checkAllCombinations(List<Card> cards, HandBundle bundle, PlayerHand h, int rank) {
        Combinations combination = new Combinations(cards, this.myCardsInHand);
        while (combination.hasNext()) {
            List<Card> list = combination.next();
            try {
                Hand hand = this.myHandFactory.createHand(bundle, list);
                if (hand.evaluate(this.myCardsInHand)) {
                    h.classifyHand(new ClassifiedHand(bundle, rank, hand.getPower()));
                    return true;
                }
            } catch (ReflectionException e) {
                this.myWinningHands.remove(bundle);
                return false;
            }
        }
        return false;
    }

}
