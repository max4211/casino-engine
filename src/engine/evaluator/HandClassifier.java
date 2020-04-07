package engine.evaluator;

import engine.hand.Hand;

import java.util.List;

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
