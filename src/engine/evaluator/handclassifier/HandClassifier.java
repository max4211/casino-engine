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

    private List<HandBundle> myWinningHands;
    private List<HandBundle> myLosingHands;
    private HandFactory myHandFactory;

    public HandClassifier(List<HandBundle> winners, List<HandBundle> losers) {
        this.myWinningHands = winners;
        this.myLosingHands = losers;
        this.myHandFactory = new HandFactory();
    }

    @Override
    public void classifyHand(PlayerHand h) {
        if (checkLosingHand(h)) {
            return;
        } else {
            checkWinningHand(h);
        }
    }

    private void printHand(PlayerHand h) {
        System.out.printf("\nprinting hand: ");
        for (Card c: h.getCards()) {
            System.out.printf("%s, ", c);
        }
    }

    private boolean checkLosingHand(PlayerHand h) {
        for (HandBundle bundle: this.myLosingHands) {
            String s = bundle.getName();
            try {
                Hand hand = this.myHandFactory.createHand(bundle, h.getCards());
                if (hand.evaluate()) {
                    h.setLoser(true);
                    // TODO update rank and power
                    h.classifyHand(new ClassifiedHand(bundle, 0, 0));
                    return true;
                }
            } catch (ReflectionException e) {
                System.out.println("could not reflect on losing hand");
            }
        }
        return false;
    }

    private boolean checkWinningHand(PlayerHand h) {
        for (HandBundle bundle: this.myWinningHands) {
            String s = bundle.getName();
            try {
                Hand hand = this.myHandFactory.createHand(bundle, h.getCards());
                if (hand.evaluate()) {
                    // TODO - update rank and power
                    h.classifyHand(new ClassifiedHand(bundle, 0, 0));
                    return true;
                }
            } catch (ReflectionException e) {
                System.out.println("could not reflect on winning hand");
            }
        }
        return true;
    }

    private int indexOf(Collection<String> collection, String s) {
        int count = 0;
        Iterator iter = collection.iterator();
        while (iter.hasNext()) {
            if (iter.next().equals(s))
                return count;
            count ++;
        }
        return 0;
    }

    private double sumCards(PlayerHand h) {
        List<Card> handCards = h.getCards();
        double sum = 0;
        for (Card card: handCards) {
            sum += card.getValue();
        }
        return sum;
    }

}
