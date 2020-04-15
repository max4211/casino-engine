package engine.evaluator.handclassifier;

import engine.dealer.Card;
import engine.hand.ClassifiedHand;
import engine.hand.PlayerHand;

import java.lang.reflect.Method;
import java.util.*;

public class HandClassifier implements HandClassifierInterface {

    private Collection<String> myWinningHands;
    private Collection<String> myLosingHands;

    private static final String IS_PREFIX = "is";

    public HandClassifier(Collection<String> winners, Collection<String> losers) {
        this.myWinningHands = winners;
        this.myLosingHands = losers;
    }

    @Deprecated
    public HandClassifier(List<String> winners, List<String> losers) {
        this.myWinningHands = winners;
        this.myLosingHands = losers;
    }

    @Override
    public void classifyHand(PlayerHand h) {
//        printHand(h);
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
        for (String s: myLosingHands) {
            if (reflectOnMethod(s, h)) {
                h.setLoser(true);
                return true;
            }
        }
        return false;
    }

    private boolean checkWinningHand(PlayerHand h) {
        for (String s: myWinningHands) {
            if (reflectOnMethod(s, h)) {
                h.classifyHand(new ClassifiedHand(s, indexOf(this.myWinningHands, s), sumCards(h)));
                return true;
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

    private boolean isBlackjack(PlayerHand h) {
        return ((sumCards(h) == 21) && (h.getCards().size() == 2));
    }

    private boolean isSumUnder22(PlayerHand h) {
        return sumCards(h) < 22;
    }

    private boolean isSumOver21(PlayerHand h) {
        return sumCards(h) > 21;
    }

    private boolean isFlush(PlayerHand h) {
        List<Card> handCards = h.getCards();
        Set<String> suits = new HashSet<>();
        for (Card card: handCards) {
            suits.add(card.getSuit());
        }
        return suits.size() == 1;
    }

    private double sumCards(PlayerHand h) {
        List<Card> handCards = h.getCards();
        double sum = 0;
        for (Card card: handCards) {
            sum += card.getValue();
        }
        return sum;
    }

    private boolean reflectOnMethod(String name, PlayerHand h) {
        try {
            String methodName = IS_PREFIX + name;
            Method method = this.getClass().getDeclaredMethod(methodName, PlayerHand.class);
            Object o = method.invoke(this, h);
            return (boolean) o;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Coder did not create hand type: %s, please returning false\n", name);
            return false;
        }

    }

}
