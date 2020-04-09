package engine.table;

import data.xmlreader.Pair;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.evaluator.BetEvaluator;
import engine.evaluator.HandClassifier;
import engine.player.Player;

import java.lang.reflect.Method;
import java.util.List;
import java.util.function.Consumer;

public class Table implements TableInterface {

    private static final String BET_ACTION = "place";
    private static final String BET_SUFFIX = "Bet";

    private static final String DEAL_ACTION = "deal";
    private static final String DEAL_SUFFIX = "Card";

    private List<Player> myPlayers;
    private Dealer myDealer;
    private BetEvaluator myBetEvaluator;
    private HandClassifier myHandClassifier;

    public Table(List<Player> players, Dealer dealer, BetEvaluator betEvaluator, HandClassifier handClassifier) {
        this.myPlayers = players;
        this.myDealer = dealer;
        this.myBetEvaluator = betEvaluator;
        this.myHandClassifier = handClassifier;
    }

    @Override
    public void acceptString(String s) {
        System.out.println(s);
    }

    // TODO - place entry bet inside of the view, register inside the model
    @Override
    public void placeEntryBet(String betType, Consumer<Bet> betConsumer) {
        String methodName = BET_ACTION + betType + BET_SUFFIX;
        for (Player p: myPlayers) {
            System.out.printf("player: %s ", p.getName());
            System.out.printf("reflection on method: %s\n", methodName);
        }
    }

    @Override
    public void performDealerAction(Pair dealerAction) {
        String actionType = dealerAction.getKey();
        int actionQuantity = Integer.parseInt(dealerAction.getValue());
        Class clazz = int.class;
        String methodName = DEAL_ACTION + actionType + DEAL_SUFFIX;
        reflectOnMethod(methodName, clazz);
        try {
            Method method = this.getClass().getDeclaredMethod(methodName, clazz);
            method.invoke(this, actionQuantity);
        } catch (Exception e) {
            System.out.println("could not apply reflection at this time");
        }

    }

    @Override
    public void performPlayerAction(String action) {

    }

    @Override
    public int totalPlayers() {
        return this.myPlayers.size();
    }

    private void dealIndividualCard(int quantity) {
        for (int i = 1; i <= quantity; i ++) {
            for (Player p: this.myPlayers) {
                System.out.printf("dealing individual card #%d to player %s\n", i, p.getName());
                List<Bet> activeBets = p.getBets();
                for (Bet b: activeBets) {
                    Card c = this.myDealer.getCard();
                    b.acceptCard(c);
                }
            }
        }
    }

    private void reflectOnMethod(String s, Class clazz) {
        System.out.printf("reflection on method: %s(%s)\n", s, clazz.getSimpleName());
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
