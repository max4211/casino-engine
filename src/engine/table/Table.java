package engine.table;

import data.xmlreader.Pair;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.evaluator.BetEvaluator;
import engine.evaluator.HandClassifier;
import engine.player.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

public class Table implements TableInterface {

    private static final String BET_ACTION = "place";
    private static final String BET_SUFFIX = "Bet";

    private static final String DEAL_ACTION = "deal";
    private static final String DEAL_SUFFIX = "Card";

    private List<Player> myPlayers;
    private List<Integer> myPlayerHashCodes;
    private Dealer myDealer;
    private BetEvaluator myBetEvaluator;
    private HandClassifier myHandClassifier;

    private int myTableMin = 5;
    private int myTableMax = 100;

    public Table(List<Player> players, Dealer dealer, BetEvaluator betEvaluator, HandClassifier handClassifier) {
        this.myPlayers = players;
        this.myDealer = dealer;
        this.myBetEvaluator = betEvaluator;
        this.myHandClassifier = handClassifier;
        this.myPlayerHashCodes = recordPlayerHashCodes();
    }

    private List<Integer> recordPlayerHashCodes() {
        List<Integer> list = new ArrayList<>();
        for (Player p: myPlayers) {
            list.add(p.getID());
        }
        return list;
    }

    @Override
    public void acceptString(String s) {
        System.out.println(s);
    }

    @Override
    public void placeEntryBet(int playerHash, String betType, double wager) {
        Player p = findPlayer(playerHash);
        System.out.printf("player: %s \n", p.getName());
        p.placeBet(wager);
//        String methodName = BET_ACTION + betType + BET_SUFFIX;
//        System.out.printf("reflection on method: %s\n", methodName);
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
    public List<Integer> getPlayerHashCodes() {
        return this.myPlayerHashCodes;
    }

    @Override
    public List<Player> getPlayers() {
        return this.myPlayers;
    }

    @Override
    public boolean hasActivePlayers() {
        return false;
    }

    @Override
    public int getTableMin() {
        return this.myTableMin;
    }

    @Override
    public int getTableMax() {
        return this.myTableMax;
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

    // TODO - throw error instead of null when can't find player (shouldn't happen)
    private Player findPlayer(int hashCode) {
        for (Player p: this.myPlayers) {
            if (p.getID() == hashCode) {
                return p;
            }
        }
        return null;
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
