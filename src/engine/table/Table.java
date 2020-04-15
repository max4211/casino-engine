package engine.table;

import Utility.StringPair;
import controller.EntryBet;
import engine.adversary.Adversary;
import engine.bet.Bet;
import engine.dealer.Card;
import engine.dealer.Dealer;
import engine.player.Player;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

public class Table implements TableInterface {

    private static final String BET_ACTION = "place";
    private static final String BET_SUFFIX = "Bet";

    private static final String DEAL_ACTION = "deal";
    private static final String DEAL_SUFFIX = "Card";

    private Collection<Player> myPlayers;
    private List<Integer> myPlayerHashCodes;
    private Dealer myDealer;

    private double myTableMin = 5;
    private double myTableMax = 100;

    private Adversary myAdversary;

    @Deprecated
    public Table(List<Player> players, Dealer dealer) {
        this.myPlayers = players;
        this.myDealer = dealer;
        this.myPlayerHashCodes = recordPlayerHashCodes();
    }

    public Table(Collection<Player> players, Dealer dealer) {
        this.myPlayers = players;
        this.myDealer = dealer;
        this.myPlayerHashCodes = recordPlayerHashCodes();
    }

    public Table(Collection<Player> players, Dealer dealer, double min, double max) {
        this.myPlayers = players;
        this.myDealer = dealer;
        this.myPlayerHashCodes = recordPlayerHashCodes();
        this.myTableMin = min;
        this.myTableMax = max;
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
    public Bet placeEntryBet(int playerHash, EntryBet betType, double wager) {
        Player p = findPlayer(playerHash);
        System.out.printf("player: %s \n", p.getName());
        return p.placeBet(wager);
    }

    @Override
    public void performDealerAction(StringPair dealerAction) {
        String actionType = dealerAction.getKey();
        int actionQuantity = Integer.parseInt(dealerAction.getValue());
        String methodName = DEAL_ACTION + actionType + DEAL_SUFFIX;
        reflectOnMethod(methodName, int.class);
        try {
            Method method = this.getClass().getDeclaredMethod(methodName, int.class);
            method.invoke(this, actionQuantity);
        } catch (Exception e) {
            System.out.println("could not apply reflection at this time");
        }

    }

    @Override
    public List<Integer> getPlayerHashCodes() {
        return this.myPlayerHashCodes;
    }

    @Override
    public Collection<Player> getPlayers() {
        return this.myPlayers;
    }

    @Override
    public boolean hasActivePlayers() {
        return getNextPlayer() != null;
    }

    @Override
    public double getTableMin() {
        return this.myTableMin;
    }

    @Override
    public double getTableMax() {
        return this.myTableMax;
    }

    @Override
    public Player getNextPlayer() {
        for (Player p: this.myPlayers) {
            for (Bet b: p.getBets()) {
                if (b.isActive()) {
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Adversary createAdversary(int min) {
        this.myAdversary = new Adversary(min);
        giveAdversaryCard();
        giveAdversaryCard();
        return this.myAdversary;
    }

    @Override
    public Card giveAdversaryCard() {
        Card c = this.myDealer.getCard();
        this.myAdversary.acceptCard(c);
        return c;
    }

    @Override
    public Supplier<Card> getDealCardMethod() {
        return () -> this.myDealer.getCard();
    }

    // TODO - slower individual card dealing with animation (Sprint 3 task)
    private void dealIndividualCard(int quantity) {
        for (int i = 1; i <= quantity; i ++) {
            for (Player p: this.myPlayers) {
                List<Bet> activeBets = p.getBets();
                for (Bet b: activeBets) {
                    Card c = this.myDealer.getCard();
                    System.out.printf("generating individual card #%d to player %s (%s)\n", i, p.getName(), c);
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

}
