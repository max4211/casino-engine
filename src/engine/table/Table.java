package engine.table;

import Utility.StringPair;
import controller.enums.EntryBet;
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

    private static final String DEAL_ACTION = "deal";
    private static final String DEAL_SUFFIX = "Card";

    private Collection<Player> myPlayers;
    private List<Integer> myPlayerHashCodes;
    private List<Card> myCommunalCards;
    private Dealer myDealer;

    private double myTableMin;
    private double myTableMax;

    private double myCurrentBet;

    private Adversary myAdversary;

    public Table(Collection<Player> players, Dealer dealer, double min, double max) {
        this.myPlayers = players;
        this.myDealer = dealer;
        this.myPlayerHashCodes = recordPlayerHashCodes();
        this.myTableMin = min;
        this.myTableMax = max;
        this.myCommunalCards = new ArrayList<>();
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
    public Bet placeEntryBet(int playerHash, String betType, double wager) {
        Player p = findPlayer(playerHash);
        return p.placeBet(wager);
    }

    @Override
    public void performDealerAction(StringPair dealerAction) {
        String actionType = dealerAction.getKey();
        int actionQuantity = Integer.parseInt(dealerAction.getValue());
        String methodName = DEAL_ACTION + actionType + DEAL_SUFFIX;
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
                if (b.isRoundActive() && b.isGameActive()) {
                    return p;
                }
            }
        }
        return null;
    }

    @Override
    public Adversary createAdversary(double min) {
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

    @Override
    public List<Card> getCommunalCards() {
        return this.myCommunalCards;
    }

    @Override
    public void restartGame() {
        this.myCommunalCards = new ArrayList<>();
        this.myDealer.shuffle();
    }

    @Override
    public void setCurrentBet(double bet) {
        this.myCurrentBet = bet;
    }

    @Override
    public double getCurrentBet() {
        return this.myCurrentBet;
    }

    private void dealIndividualCard(int quantity) {
        for (int i = 1; i <= quantity; i ++) {
            for (Player p: this.myPlayers) {
                List<Bet> activeBets = p.getBets();
                for (Bet b: activeBets) {
                    Card c = this.myDealer.getCard();
                    b.acceptCard(c);
                }
            }
        }
    }

    private void dealCommunalCard(int quantity) {
        for (int i = 1; i <= quantity; i ++) {
            Card c = this.myDealer.getCard();
            this.myCommunalCards.add(this.myDealer.getCard());
        }
    }

    private Player findPlayer(int hashCode) {
        for (Player p: this.myPlayers) {
            if (p.getID() == hashCode) {
                return p;
            }
        }
        return null;
    }

    public int totalActivePlayers() {
        int count = 0;
        for (Player p: this.myPlayers) {
            for (Bet b: p.getActiveBets())
                if (b.isGameActive())
                    count ++;
        }
        return count;
    }

}
