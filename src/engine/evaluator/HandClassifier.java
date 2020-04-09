package engine.evaluator;

import engine.dealer.Card;
import engine.hand.Hand;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HandClassifier implements HandClassifierInterface {

    private List<String> myWinningHands;
    private List<String> myLosingHands;


    public HandClassifier(List<String> winners, List<String> losers) {
        this.myWinningHands = winners;
        this.myLosingHands = losers;
    }

    @Override
    public void classifyHand(Hand h) {
        boolean loser = checkLosingHand(h);
        boolean winner = checkWinningHand(h);
    }

    //TODO - implement reflection (see below)
    private boolean checkLosingHand(Hand h) {
        for (String s: myLosingHands) {
            ;
        }
        return false;
    }

    private boolean checkWinningHand(Hand h) {
        for (String s: myWinningHands) {
            ;
        }
        return true;
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
