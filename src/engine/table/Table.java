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

/**
 * Encapsulates players, dealer, commmunal cards, and metadata for the table
 * Essentially the "backend", publicly available methods all used by controller
 */
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

    /**
     * Construct a table from players, dealer, and metadata
     * @param players list of players (as read in from XML)
     * @param dealer encapsulation of deck (as created in XML)
     * @param min table minimum (parsed in XML)
     * @param max table maximum (parsed in XML)
     */
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

    /**
     * Producer for the view to accept strings in response to bet creation
     * @param s is the string that corresponds to the action
     */
    @Override
    public void acceptString(String s) {
        System.out.println(s);
    }

    /**
     * Place the bet accordingly with the front end selection
     * @return id of the bet that has been placed
     * @param playerHash is the identification number of the appropriate player
     * @param betType is the bet type that was placed
     * @param wager is the wager size for that bet
     * @return bet that was just placed
     */
    @Override
    public Bet placeEntryBet(int playerHash, String betType, double wager) {
        Player p = findPlayer(playerHash);
        return p.placeBet(wager);
    }

    /**
     * called by controller for dealer to distribute cards to players
     * @param dealerAction dictates what type of action is conducted
     */
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

    /**
     * called by the controller to identify players
     * @return all players unique hash codes
     */
    @Override
    public List<Integer> getPlayerHashCodes() {
        return this.myPlayerHashCodes;
    }

    /**
     * Get all of the current players at the table
     * @return list of players
     */
    @Override
    public Collection<Player> getPlayers() {
        return this.myPlayers;
    }

    /**
     * Determine if any players are still active in the round/game
     * @return true if any players are still alive
     */
    @Override
    public boolean hasActivePlayers() {
        return getNextPlayer() != null && getTotalActivePlayers() >= 1;
    }

    /**
     * fetch table limits of betting to determine slider ranges for user display
     * @return table min/max as specified in data file
     */
    @Override
    public double getTableMin() {
        return this.myTableMin;
    }

    @Override
    public double getTableMax() {
        return this.myTableMax;
    }

    /**
     * Finds next active player in the list who has the action
     * @return the appopriate player whose action it is
     */
    @Override
    public Player getNextPlayer() {
        for (Player p: this.myPlayers) {
            for (Bet b: p.getActiveBets()) {
                if (b.isRoundActive() && b.isGameActive() && p.getBankroll() > 0) {
                    return p;
                }
            }
        }
        return null;
    }

    /**
     * Create an adversary
     * @param min are parameters to adversary gameplay
     * @return the adversary inside of the game
     */
    @Override
    public Adversary createAdversary(double min) {
        this.myAdversary = new Adversary(min);
        giveAdversaryCard();
        giveAdversaryCard();
        return this.myAdversary;
    }

    /**
     * Returns card that was just given to adversary
     */
    @Override
    public Card giveAdversaryCard() {
        Card c = this.myDealer.getCard();
        this.myAdversary.acceptCard(c);
        return c;
    }

    /**
     * Gives functional interface within dealer
     * Dealer only exists in player, gives actions way to get cards without knowledge of dealer
     * @return supplier from dealer
     */
    @Override
    public Supplier<Card> getDealCardMethod() {
        return () -> this.myDealer.getCard();
    }

    /**
     * Get a list of commmunal cards at the table
     * @return list of communal cards
     */
    @Override
    public List<Card> getCommunalCards() {
        return this.myCommunalCards;
    }

    /**
     * Called to reset the game to its appropriate state
     */
    @Override
    public void restartGame() {
        this.myCommunalCards = new ArrayList<>();
        this.myDealer.shuffle();
    }

    /**
     * Get the current bet value that needs to be set
     * @param bet sets the bet to where others must match to stay in the round
     */
    @Override
    public void setCurrentBet(double bet) {
        this.myCurrentBet = bet;
    }

    /**
     * Fetch the current bet value that needs to be matched
     * @return current bet that needs to be matched (after a BetAction0
     */
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

    /**
     * Count toatl number of active players at the table
     * @return total active players at the table
     */
    @Override
    public int getTotalActivePlayers() {
        int count = 0;
        for (Player p: this.myPlayers) {
            for (Bet b: p.getActiveBets())
                if (b.isGameActive())
                    count ++;
        }
        return count;
    }

}
