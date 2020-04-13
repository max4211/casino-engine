package engine.evaluator;

import engine.dealer.Card;
import engine.hand.ClassifiedHand;
import engine.hand.Hand;

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
    public void classifyHand(Hand h) {
        printHand(h);
        if (checkLosingHand(h)) {
            return;
        } else {
            checkWinningHand(h);
        }
    }

    private void printHand(Hand h) {
        System.out.printf("\nprinting hand: ");
        for (Card c: h.getCards()) {
            System.out.printf("%s, ", c);
        }
    }

    private boolean checkLosingHand(Hand h) {
        System.out.printf("\nchecking losing hands: \n");
        for (String s: myLosingHands) {
            System.out.printf("%s, ", s);
            if (reflectOnMethod(s, h)) {
                h.setLoser(true);
                return true;
            }
        }
        return false;
    }

    private boolean checkWinningHand(Hand h) {
        System.out.printf("\nchecking winning hands: ");
        for (String s: myWinningHands) {
            System.out.printf("%s, ", s);
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

    private boolean isBlackjack(Hand h) {
        return ((sumCards(h) == 21) && (h.getCards().size() == 2));
    }

    private boolean isSumUnder22(Hand h) {
        return sumCards(h) < 22;
    }

    private boolean isSumOver21(Hand h) {
        return sumCards(h) > 21;
    }

    private boolean isFlush(Hand h) {
        List<Card> handCards = h.getCards();
        Set<String> suits = new HashSet<>();
        for (Card card: handCards) {
            suits.add(card.getSuit());
        }
        return suits.size() == 1;
    }

    private double sumCards(Hand h) {
        List<Card> handCards = h.getCards();
        double sum = 0;
        for (Card card: handCards) {
            sum += card.getValue();
        }
        return sum;
    }

    private boolean reflectOnMethod(String name, Hand h) {
        try {
            String methodName = IS_PREFIX + name;
//            System.out.println("invoked method name: " + methodName);
            Method method = this.getClass().getDeclaredMethod(methodName, Hand.class);
//            System.out.println("method recieved: " + method);
            Object o = method.invoke(this, h);
//            System.out.println("invoked method and recieved object: " + o);
            System.out.printf("(%s)\n", o);
            return (boolean) o;
        } catch (Exception e) {
            e.printStackTrace();
            System.out.printf("Coder did not create hand type: %s, please returning false\n", name);
            return false;
        }

    }

    /** Max's team code for reflection example within their execution (shows how to invoke a method)
     *
     *     private List<String> executeCommand(Command command) {
     *         try {
     *             Class superclazz = command.getClass().getSuperclass();
     *             String name = EXECUTE + superclazz.getSimpleName();
     *             Method method = this.getClass().getDeclaredMethod(name, superclazz); //Command.class
     *             Object o = method.invoke(this, command);
     *             return (List<String>) o;
     *         } catch (InvocationTargetException | IllegalAccessException | NoSuchMethodException | NullPointerException e) {
     *             throw new ReflectionException("Unable to apply Reflection in parser");
     *         }
     *     }
     */

}
