package engine.evaluator.handclassifier;

import Utility.handbundle.HandBundle;
import engine.dealer.Card;
import engine.evaluator.handtype.Hand;
import engine.evaluator.handtype.HandFactory;
import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;
import exceptions.ReflectionException;

import java.lang.reflect.Method;
import java.util.*;

public class HandClassifier implements HandClassifierInterface {

    private final List<HandBundle> myWinningHands;
    private final List<HandBundle> myLosingHands;
    private final HandFactory myHandFactory;
    private final int myCardsInHand;

    public HandClassifier(List<HandBundle> winners, List<HandBundle> losers, int cardsInHand) {
        this.myWinningHands = winners;
        this.myLosingHands = losers;
        this.myHandFactory = new HandFactory();
        this.myCardsInHand = cardsInHand;
    }

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
                if (hand.evaluate()) {
                    h.setLoser(true);
                    // TODO - classify hand? unnecessary
                    return true;
                }
            } catch (ReflectionException e) {
                // TODO - remove hand from list (with schema, will never happen)
                System.out.println("could not reflect on losing hand");
            }
        }
        return false;
    }

    private void checkWinningHand(List<Card> cards, PlayerHand h) {
        for (int rank = 0; rank < this.myWinningHands.size(); rank ++) {
            HandBundle bundle =  this.myWinningHands.get(rank);
            String s = bundle.getName();
            try {
                checkAllCombinations(cards, bundle);
                Hand hand = this.myHandFactory.createHand(bundle, cards);
                if (hand.evaluate()) {
                    // TODO - update power
                    h.classifyHand(new ClassifiedHand(bundle, rank, hand.getPower()));
                    return;
                }
            } catch (ReflectionException e) {
                System.out.println("could not reflect on winning hand");
            }
        }
    }

    // TODO - try to make best hand out of best cards
    // 7 choose 5 possible hands (21 cases, not too bad)
    private void checkAllCombinations(List<Card> cards, HandBundle bundle) {

    }

}
